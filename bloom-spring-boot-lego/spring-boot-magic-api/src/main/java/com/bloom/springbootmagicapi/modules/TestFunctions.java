package com.bloom.springbootmagicapi.modules;

import org.springframework.stereotype.Component;
import org.ssssssss.magicapi.core.annotation.MagicModule;
import org.ssssssss.script.annotation.Comment;

/**
 * @author curry
 * Created by on 2022-11-04 下午6:24
 */
@Component //注入到Spring容器中
@MagicModule("test") // 模块名称
public class TestFunctions {

    /**
     *   调用打印方法
     */
    @Comment("方法名的注释(用于提示)")
    public String println(@Comment("参数名的提示(用于提示)") String value) {
        return value;
    }
}