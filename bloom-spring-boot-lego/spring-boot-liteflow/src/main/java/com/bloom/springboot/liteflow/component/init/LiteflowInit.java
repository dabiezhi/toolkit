package com.bloom.springboot.liteflow.component.init;

import javax.annotation.PostConstruct;

import com.bloom.springboot.liteflow.component.CustomScriptIfComponent;
import com.yomahub.liteflow.core.ScriptComponent;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import com.yomahub.liteflow.springboot.LiteflowExecutorInit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * Created by on 2023-04-01 22:35
 */
@Component
@ConditionalOnMissingBean(LiteflowExecutorInit.class)
public class LiteflowInit {

    @PostConstruct
    public void init() {
        ScriptComponent.ScriptComponentClassMap.put(NodeTypeEnum.IF_SCRIPT, CustomScriptIfComponent.class);
    }

}