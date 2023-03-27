package com.bloom.springboot.liteflow.bean;

import com.yomahub.liteflow.script.annotation.ScriptMethod;
import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2023-03-27 3:21 PM
 */
@Component
public class DemoBean {

    @ScriptMethod("demo")
    public Boolean getDemoStr1() {
        return false;
    }

}