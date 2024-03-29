/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2023 All Rights Reserved.
 */
package
        com.bloom.springboot.liteflow.component;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.core.NodeIfComponent;
import com.yomahub.liteflow.util.LiteFlowProxyUtil;

/**
 * @author curry
 * Created by on 2023-03-31 3:45 PM
 */
public abstract class TNodeIfComponent extends NodeIfComponent {

    @Override
    public void process() throws Exception {
        boolean result = this.processIf();
        System.out.println("===============>"+result);
        Class<?> originalClass = LiteFlowProxyUtil.getUserClass(this.getClass());
        this.getSlot().setIfResult(originalClass.getName(), result);
    }

}
