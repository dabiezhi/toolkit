package com.bloom.springboot.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;
import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2023-03-24 4:16 PM
 */
@LiteflowComponent("b")
public class BCmp extends NodeComponent {

    @Override
    public void process() throws Exception {
        DefaultContext contextBean = this.getContextBean(DefaultContext.class);
        Object mao = contextBean.getData("k1");
        System.out.println("BCmp executed!");
    }
}