package com.bloom.springboot.liteflow.spring;

/**
 * @author curry
 * Created by on 2023-03-30 3:23 PM
 */

import com.bloom.springboot.liteflow.component.TScriptIfComponent;
import com.yomahub.liteflow.builder.LiteFlowNodeBuilder;
import com.yomahub.liteflow.core.ScriptComponent;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import com.yomahub.liteflow.flow.FlowBus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring上下文获取
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ScriptComponent.ScriptComponentClassMap.put(NodeTypeEnum.IF_SCRIPT, TScriptIfComponent.class);
//        LiteFlowNodeBuilder.createScriptIfNode().setId("s5").setName("name").setType(NodeTypeEnum.IF_SCRIPT).setScript("return true").setClazz(TScriptIfComponent.class).build();
//        FlowBus.getNode("s5").setClazz("com.bloom.springboot.liteflow.component.TScriptIfComponent");
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
