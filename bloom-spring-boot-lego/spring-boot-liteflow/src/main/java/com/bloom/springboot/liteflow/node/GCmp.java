package com.bloom.springboot.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author curry
 * Created by on 2023-03-24 4:16 PM
 */
@LiteflowComponent("g")
public class GCmp extends NodeComponent {

    @Override
    public void process() throws Exception {
        System.out.println("GCmp executed!");
    }
}