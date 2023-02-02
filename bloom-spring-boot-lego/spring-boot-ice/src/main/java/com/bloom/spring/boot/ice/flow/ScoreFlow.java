package com.bloom.spring.boot.ice.flow;

import com.bloom.spring.boot.ice.service.SendService;
import com.ice.core.annotation.IceNode;
import com.ice.core.context.IceRoam;
import com.ice.core.leaf.roam.BaseLeafRoamFlow;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.Resource;

/**
 * @author waitmoon
 * 取出roam中的值比较大小
 */
@Data
@EqualsAndHashCode(callSuper = true)
@IceNode(name = "发放余额", desc = "用于发放余额")
public class ScoreFlow extends BaseLeafRoamFlow {

    private double score;

    private String key;

    @Resource
    private SendService sendService;

    /*
     * 叶子节点流程处理
     *
     * @param roam 传递roam
     */
    @Override
    protected boolean doRoamFlow(IceRoam roam) {
        sendService.sendPoint(1,2);
        Object value = roam.getMulti(key);
        if (value == null) {
            return false;
        }
        double valueScore = Double.parseDouble(value.toString());
        return !(valueScore < score);
    }
}