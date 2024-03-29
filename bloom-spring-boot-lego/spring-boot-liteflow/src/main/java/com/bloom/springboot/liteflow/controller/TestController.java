package com.bloom.springboot.liteflow.controller;

import com.bloom.springboot.liteflow.component.TScriptIfComponent;
import com.yomahub.liteflow.builder.LiteFlowNodeBuilder;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import com.yomahub.liteflow.flow.FlowBus;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.flow.element.Chain;
import com.yomahub.liteflow.slot.DefaultContext;
import com.yomahub.liteflow.util.JsonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author curry
 * Created by on 2023-03-24 4:19 PM
 */
@RestController
public class TestController {

    @Resource
    private FlowExecutor flowExecutor;

    @GetMapping(value = "/get")
    public String get() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain2", "arg", DefaultContext.class);
        DefaultContext context = response.getFirstContextBean();
        System.out.println(JsonUtil.toJsonString(context.getData("s1")));
        if (response.isSuccess()) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }
        Map<String, Chain> chainMap = FlowBus.getChainMap();
        return "";
    }

    @GetMapping(value = "/load")
    public String load() {
        flowExecutor.reloadRule();
        return "";
    }

}