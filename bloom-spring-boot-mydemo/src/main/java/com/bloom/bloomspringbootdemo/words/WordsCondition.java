package com.bloom.bloomspringbootdemo.words;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * Created by on 2022-08-20 14:16
 */
public interface WordsCondition {

    /**
     * 条件评估
     * @param rules 规则条件
     * @param ctx 上下文
     * @return 评估过滤的结果
     */
    Optional<List<WordsRule.Words>> eval(List<WordsRule.Rule> rules, WordsContext ctx);
}