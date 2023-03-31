package com.bloom.springboot.liteflow.node;

import com.bloom.springboot.liteflow.component.TNodeIfComponent;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.core.NodeIfComponent;
import com.yomahub.liteflow.slot.DefaultContext;

/**
 * @author curry
 * Created by on 2023-03-24 4:16 PM
 */
@LiteflowComponent("d")
public class DCmp extends NodeIfComponent {


    @Override
    public boolean processIf() throws Exception {
        System.out.println("DCmp executed!");
        return true;
    }
}