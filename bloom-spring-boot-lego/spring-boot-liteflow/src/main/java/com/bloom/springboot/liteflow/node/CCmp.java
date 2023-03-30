package com.bloom.springboot.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2023-03-24 4:16 PM
 */
@LiteflowComponent("c")
public class CCmp extends NodeComponent {

    @Override
    public void process() throws Exception {
        System.out.println("CCmp executed!");
    }

    @Override
    public void onSuccess() throws Exception {
        System.out.println("CCmp onSuccess!");
    }
}