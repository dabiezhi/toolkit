package com.bloom.springboot.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeIfComponent;

/**
 * @author curry
 * Created by on 2023-03-24 4:16 PM
 */
@LiteflowComponent("f")
public class FCmp extends NodeIfComponent {


    @Override
    public boolean processIf() throws Exception {
        System.out.println("FFCmp executed!");
        return false;
    }
}