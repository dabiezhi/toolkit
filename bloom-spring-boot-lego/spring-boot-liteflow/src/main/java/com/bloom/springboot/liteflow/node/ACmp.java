package com.bloom.springboot.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;

/**
 * @author curry
 * Created by on 2023-03-24 4:16 PM
 */
@LiteflowComponent("a")
public class ACmp extends NodeComponent {

    @Override
    public void process() throws Exception {
        DefaultContext contextBean = this.getContextBean(DefaultContext.class);
        contextBean.setData("k1","郭艾伦");
        contextBean.setData("tag",this.getTag());
        System.out.println("ACmp executed!");
    }
}