/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2023 All Rights Reserved.
 */
package
        com.bloom.springboot.liteflow.component;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.util.LiteFlowProxyUtil;

/**
 * @author curry
 * Created by on 2023-03-31 3:45 PM
 */
public abstract class TNodeIfComponent extends NodeComponent {

    @Override
    public void process() throws Exception {
        boolean result = this.processIf();
        Class<?> originalClass = LiteFlowProxyUtil.getUserClass(this.getClass());
        this.getSlot().setIfResult(originalClass.getName(), result);
    }

    public abstract boolean processIf() throws Exception;

}
