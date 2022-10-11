package com.bloom.bloomspringbootdemo.easyrules;

import javax.annotation.Resource;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * Created by on 2022-10-11 11:54
 */
@Component
public class TestAction implements Action {

    @Resource
    private Handler handler;

    @Override
    public void execute(Facts facts) throws Exception {
        handler.tt();
    }
}