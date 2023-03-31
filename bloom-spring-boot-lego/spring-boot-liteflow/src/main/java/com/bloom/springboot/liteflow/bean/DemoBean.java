package com.bloom.springboot.liteflow.bean;

import com.yomahub.liteflow.script.annotation.ScriptBean;
import com.yomahub.liteflow.script.annotation.ScriptMethod;
import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2023-03-27 3:21 PM
 */
@Component
@ScriptBean("demo")
public class DemoBean {

    public Boolean getFlag() {
        return false;
    }

    public String getStr(String str) {
        return "curry" + str;
    }

}