package com.bloom.springboot.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.core.NodeIfComponent;

/**
 * @author curry
 * Created by on 2023-03-24 4:16 PM
 */
@LiteflowComponent("e")
public class ECmp extends NodeComponent {

    @Override
    public void process() throws Exception {
        System.out.println("ECmp executed!");
    }
}