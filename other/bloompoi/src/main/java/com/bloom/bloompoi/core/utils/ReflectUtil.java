/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi.core.utils;

import com.bloom.bloompoi.core.lang.SimpleCache;
import com.bloom.bloompoi.writer.spel.strategy.CellSpElStrategy;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.Assert;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * 反射工具类
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-07 09:57
 */
@UtilityClass
public class ReflectUtil {

    /**
     * Field缓存
     */
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();
    /**
     * 目标类的父类集合缓存
     */
    private static final SimpleCache<Class<?>, List<Class<?>>> SUB_CLASS_CACHE = new SimpleCache<>();

    /**
     * 获取字段值
     *
     * @param obj       对象，如果static字段，此处为类
     * @param fieldName 字段名
     * @return 字段值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        if (null == obj || StringUtil.isBlank(fieldName)) {
            return null;
        }
        return getFieldValue(obj, getField(obj instanceof Class ? (Class<?>) obj : obj.getClass(), fieldName));
    }

    /**
     * 获取字段值
     *
     * @param obj   对象，static字段则此字段为null
     * @param field 字段
     * @return 字段值
     */
    @SneakyThrows
    public static Object getFieldValue(Object obj, Field field) {
        if (null == field) {
            return null;
        }
        if (obj instanceof Class) {
            // 静态字段获取时对象为null
            obj = null;
        }
        setAccessible(field);
        return field.get(obj);
    }

    /**
     * 查找指定类中的所有字段（包括非public字段），也包括父类和Object类的字段， 字段不存在则返回<code>null</code>
     *
     * @param beanClass 被查找字段的类,不能为null
     * @param name      字段名
     * @return 字段
     * @throws SecurityException 安全异常
     */
    public static Field getField(Class<?> beanClass, String name) throws SecurityException {
        final Field[] fields = getFields(beanClass);
        if (ArrayUtil.isNotEmpty(fields)) {
            for (Field field : fields) {
                if ((name.equals(getFieldName(field)))) {
                    return field;
                }
            }
        }
        return null;
    }

    /**
     * 获取字段名
     *
     * @param field 字段
     * @return 字段名
     */
    public static String getFieldName(Field field) {
        if (null == field) {
            return null;
        }

        return field.getName();
    }

    /**
     * 获得一个类中所有字段列表，包括其父类中的字段
     *
     * @param beanClass 类
     * @return 字段列表
     */
    public static Field[] getFields(Class<?> beanClass) {
        Field[] allFields = FIELDS_CACHE.get(beanClass);
        if (null != allFields) {
            return allFields;
        }

        allFields = getFieldsDirectly(beanClass, false);
        return FIELDS_CACHE.put(beanClass, allFields);
    }

    /**
     * 获得一个类中所有字段列表，包括其父类中的字段
     *
     * @param beanClass 类
     * @return 字段列表
     * @throws SecurityException 安全检查异常
     */
    public static List<Field> getFiledList(Class<?> beanClass) {
        return Arrays.asList(getFields(beanClass));
    }

    /**
     * 获得一个类中所有字段列表，直接反射获取，无缓存
     *
     * @param beanClass            类
     * @param withSuperClassFields 是否包括父类的字段列表
     * @return 字段列表
     * @throws SecurityException 安全检查异常
     */
    public static Field[] getFieldsDirectly(Class<?> beanClass, boolean withSuperClassFields) {
        Assert.notNull(beanClass, "class can't be null");
        Field[] allFields = null;
        Class<?> searchType = beanClass;
        Field[] declaredFields;
        while (searchType != null) {
            declaredFields = searchType.getDeclaredFields();
            if (null == allFields) {
                allFields = declaredFields;
            } else {
                allFields = ArrayUtil.append(allFields, declaredFields);
            }
            searchType = withSuperClassFields ? searchType.getSuperclass() : null;
        }

        return allFields;
    }

    /**
     * 设置方法为可访问（私有方法可以被外部调用）
     *
     * @param <T>              AccessibleObject的子类，比如Class、Method、Field等
     * @param accessibleObject 可设置访问权限的对象，比如Class、Method、Field等
     * @return 被设置可访问的对象
     * @since 4.6.8
     */
    public static <T extends AccessibleObject> T setAccessible(T accessibleObject) {
        if (null != accessibleObject && !accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
        return accessibleObject;
    }

    /**
     * 获取一个接口的所有实现类
     *
     * @param target 目标类
     * @return 目标类的所有实现类
     */
    public static List<Class<?>> getInterfaceImpls(Class<? extends CellSpElStrategy> target) {
        List<Class<?>> subClassCache = SUB_CLASS_CACHE.get(target);
        if (CollectionUtil.isEmpty(subClassCache)) {
            // 判断class对象是否是一个接口
            if (target.isInterface() && null != target.getSuperclass()) {
                List<Class<?>> subClass = Arrays.asList(target.getInterfaces());
                SUB_CLASS_CACHE.put(target, subClass);
            }
        }
        return subClassCache;
    }
}
