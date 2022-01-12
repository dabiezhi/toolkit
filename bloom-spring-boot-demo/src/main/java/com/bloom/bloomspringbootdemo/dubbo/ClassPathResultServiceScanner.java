/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.dubbo;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashSet;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;

/**
 * @author taosy
 * Created by on 2022-01-12 下午4:35
 */
public class ClassPathResultServiceScanner extends ClassPathBeanDefinitionScanner {

    private final BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathResultServiceScanner(BeanDefinitionRegistry registry) {
        super(registry);
        addIncludeFilter(new AnnotationTypeFilter(ResultService.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Assert.notEmpty(basePackages, "At least one base package must be specified");
        Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                try {
                    Class<?> clazz = classLoader.loadClass(candidate.getBeanClassName());
                    beanDefinitions
                        .add(registerBean(getRegistry(), null, getClass(clazz)));
                } catch (ClassNotFoundException e) {
                    throw new BeanCreationException(e.getMessage(), e);
                }
            }
        }
        return beanDefinitions;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface()
               && beanDefinition.getMetadata().isIndependent();
    }

    @SneakyThrows
    protected static CtClass genDubboServiceClass(Class<?> resultDubboServiceClass) {

        //        ClassPool classPool = ClassGenerator.getClassPool(resultDubboServiceClass.getClassLoader());
        ClassPool classPool = ClassPool.getDefault();
        String className = resultDubboServiceClass.getName() + "_ProxyImpl";
        CtClass ctClass = classPool.makeClass(className);

        ClassFile classFile = ctClass.getClassFile();
        classFile.setMajorVersion(ClassFile.JAVA_8);

        ConstPool constPool = classFile.getConstPool();

        // 添加类注解
        AnnotationsAttribute bodyAttr = new AnnotationsAttribute(constPool,
            AnnotationsAttribute.visibleTag);
        Annotation componentAnnotation = new Annotation("org.springframework.stereotype.Component",
            constPool);
        bodyAttr.addAnnotation(componentAnnotation);

        Annotation validatedAnnotation = new Annotation(
            "com.bloom.bloomspringbootdemo.dubbo.Validated", constPool);
        bodyAttr.addAnnotation(validatedAnnotation);

        Annotation serviceAnnotation = new Annotation("org.apache.dubbo.config.annotation.Service",
            constPool);

        ResultService resultService = resultDubboServiceClass.getAnnotation(ResultService.class);

        Method[] members = resultService.dubboService().annotationType().getMethods();
        for (Method member : members) {
            if (Modifier.isPublic(member.getModifiers()) && member.getParameterTypes().length == 0
                && member.getDeclaringClass() == resultService.dubboService().annotationType()) {

                try {
                    Object value = member.invoke(resultService.dubboService());
                    if (null != value) {
                        MemberValue memberValue = createMemberValue(constPool,
                            classPool.get(member.getReturnType().getName()), value);
                        serviceAnnotation.addMemberValue(member.getName(), memberValue);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {

                }
            }
        }
        bodyAttr.addAnnotation(serviceAnnotation);
        classFile.addAttribute(bodyAttr);

        // 添加接口
        ctClass
            .addInterface(classPool.get(resultService.dubboService().interfaceClass().getName()));
        // 添加类默认构造函数
        ctClass
            .addConstructor(CtNewConstructor.defaultConstructor(classPool.getCtClass(className)));
        // 设置字段
        String managerFieldName = resultService.managerClass().getSimpleName().substring(0, 1)
            .toLowerCase() + resultService.managerClass().getSimpleName().substring(1);
        CtField ctField = CtField.make("private " + resultService.managerClass().getCanonicalName()
                                       + " " + managerFieldName + ";",
            classPool.getCtClass(className));

        Annotation autowiredAnnotation = new Annotation(
            "org.springframework.beans.factory.annotation.Autowired", constPool);
        AnnotationsAttribute attribute = new AnnotationsAttribute(classFile.getConstPool(),
            AnnotationsAttribute.visibleTag);
        attribute.addAnnotation(autowiredAnnotation);
        ctField.getFieldInfo().addAttribute(attribute);
        ctClass.addField(ctField);

        // 如果是接口
        Method[] interfaceMethods = resultService.dubboService().interfaceClass().getMethods();

        for (Method method : interfaceMethods) {
            // 添加方法, 里面进行动态代理logic
            // 验证一下方法
            resultService.managerClass().getMethod(method.getName(), method.getParameterTypes());

            String args = getArgsString(method, resultService.managerClass());
            String body = getBodyString(method, managerFieldName);

            String methodString = "public " + method.getReturnType().getTypeName() + " "
                                  + method.getName() + "(" + args + ") { " + body + "}";
            CtMethod ctMethod = CtNewMethod.make(methodString, ctClass);
            // 给所有的参数加入@Name, validation会用到
            //            addNameAnnotationToParameters(ctMethod, method, constPool);
            ctClass.addMethod(ctMethod);
        }
        return ctClass;
    }

    @SneakyThrows
    public static Class<?> getClass(Class<?> resultDubboServiceClass) {
        return genDubboServiceClass(resultDubboServiceClass).toClass();
    }

    //    private static void addNameAnnotationToParameters(CtMethod ctMethod, Method method,
    //                                                      ConstPool constPool) {
    //        if (method.getParameterCount() == 0) {
    //            return;
    //        }
    //        ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(
    //                constPool, ParameterAnnotationsAttribute.visibleTag);
    //        Annotation[][] annotations = new Annotation[method.getParameterCount()][1];
    //        Parameter[] parameters = method.getParameters();
    //        for (int i = 0; i < method.getParameterCount(); i++) {
    //            Annotation name = new Annotation("com.guahao.convention.validation.Name", constPool);
    //            String parameterName = parameters[i].getName();
    //            if (parameters[i].isAnnotationPresent(Name.class)) {
    //                parameterName = parameters[i].getAnnotation(Name.class).value();
    //            }
    //            name.addMemberValue("value", new StringMemberValue(parameterName, constPool));
    //            annotations[i][0] = name;
    //        }
    //        parameterAtrribute.setAnnotations(annotations);
    //        ctMethod.getMethodInfo().addAttribute(parameterAtrribute);
    //    }

    private static String getArgsString(Method member, Class<?> managerClass) {
        StringBuilder args = new StringBuilder();

        Parameter[] parameters = member.getParameters();
        for (int i = 0, length = member.getParameterCount(); i < length; i++) {
            Parameter parameter = parameters[i];

            args.append(parameter.getType().getTypeName()).append(" ").append(parameter.getName());
            if (i < length - 1) {
                args.append(", ");
            }
        }
        return args.toString();
    }

    private static String getBodyString(Method member, String managerFieldName) {
        StringBuilder body = new StringBuilder();
        String typeName = member.getGenericReturnType().getTypeName();
        if ("com.bloom.bloomspringbootdemo.dubbo.Result<java.lang.Void>".equals(typeName)) {

            body.append(managerFieldName).append(".").append(member.getName()).append("(");
            Parameter[] parameters = member.getParameters();
            for (int i = 0, length = member.getParameterCount(); i < length; i++) {
                body.append(parameters[i].getName());
                if (i < length - 1) {
                    body.append(", ");
                }
            }
            body.append(");");
            body.append("return com.bloom.bloomspringbootdemo.dubbo.Results.success();");
        } else {
            body.append("return com.bloom.bloomspringbootdemo.dubbo.Results.success(");
            body.append(managerFieldName).append(".").append(member.getName()).append("(");
            Parameter[] parameters = member.getParameters();
            for (int i = 0, length = member.getParameterCount(); i < length; i++) {
                body.append(parameters[i].getName());
                if (i < length - 1) {
                    body.append(", ");
                }
            }
            body.append("));");
        }
        return body.toString();
    }

    // Copy from javassist.bytecode.annotation.Annotation.createMemberValue(ConstPool, CtClass);
    private static MemberValue createMemberValue(ConstPool cp, CtClass type,
                                                 Object value) throws NotFoundException {
        MemberValue memberValue = javassist.bytecode.annotation.Annotation.createMemberValue(cp,
            type);
        if (memberValue instanceof BooleanMemberValue) {
            ((BooleanMemberValue) memberValue).setValue((Boolean) value);
        } else if (memberValue instanceof ByteMemberValue) {
            ((ByteMemberValue) memberValue).setValue((Byte) value);
        } else if (memberValue instanceof CharMemberValue) {
            ((CharMemberValue) memberValue).setValue((Character) value);
        } else if (memberValue instanceof ShortMemberValue) {
            ((ShortMemberValue) memberValue).setValue((Short) value);
        } else if (memberValue instanceof IntegerMemberValue) {
            ((IntegerMemberValue) memberValue).setValue((Integer) value);
        } else if (memberValue instanceof LongMemberValue) {
            ((LongMemberValue) memberValue).setValue((Long) value);
        } else if (memberValue instanceof FloatMemberValue) {
            ((FloatMemberValue) memberValue).setValue((Float) value);
        } else if (memberValue instanceof DoubleMemberValue) {
            ((DoubleMemberValue) memberValue).setValue((Double) value);
        } else if (memberValue instanceof ClassMemberValue) {
            ((ClassMemberValue) memberValue).setValue(((Class<?>) value).getName());
        } else if (memberValue instanceof StringMemberValue) {
            ((StringMemberValue) memberValue).setValue((String) value);
        } else if (memberValue instanceof EnumMemberValue) {
            ((EnumMemberValue) memberValue).setValue(((Enum<?>) value).name());
        }
        /* else if (memberValue instanceof AnnotationMemberValue) */
        else if (memberValue instanceof ArrayMemberValue) {
            CtClass arrayType = type.getComponentType();
            int len = Array.getLength(value);
            MemberValue[] members = new MemberValue[len];
            for (int i = 0; i < len; i++) {
                members[i] = createMemberValue(cp, arrayType, Array.get(value, i));
            }
            ((ArrayMemberValue) memberValue).setValue(members);
        }
        return memberValue;
    }

    private BeanDefinitionHolder registerBean(BeanDefinitionRegistry registry, String name,
                                              Class<?> beanClass) {
        AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(
            beanClass);
        //可以自动生成name
        String beanName = (name != null ? name
            : this.beanNameGenerator.generateBeanName(annotatedBeanDefinition, registry));
        //bean注册的holer类.
        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(
            annotatedBeanDefinition, beanName);
        //使用bean注册工具类进行注册.
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
        return beanDefinitionHolder;
    }

    @SneakyThrows
    public static void main(String[] args) {
        CtClass ctClass = genDubboServiceClass(DemoServiceImpl.class);
        byte[] byteArr = ctClass.toBytecode();
        FileOutputStream fos = new FileOutputStream(new File("/Users/taosy/Documents/taosy/haixin/my.class"));
        fos.write(byteArr);
        fos.close();
        System.out.println("over!!");

    }
}