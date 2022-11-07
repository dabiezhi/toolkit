package com.bloom.springbootmagicapi.modules;

import org.springframework.stereotype.Component;
import org.ssssssss.magicapi.jsr223.LanguageProvider;

import java.util.Map;

/**
 * 自定义脚本语言
 */
@Component //注入到Spring容器中
public class TestLanguage implements LanguageProvider {

    @Override
    public boolean support(String languageName) {
        return "test".equalsIgnoreCase(languageName);
    }

    @Override
    public Object execute(String languageName, String script,
                          Map<String, Object> context) throws Exception {
        return "hello " + context.get("name");
    }

}