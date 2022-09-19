package com.bloom.bloomspringbootdemo.words;

import java.util.List;

/**
 * @author curry
 * Created by on 2022-08-21 上午10:05
 */
public interface WordsAction {

    /**
     * 话术执行动作
     * @param words 话术
     * @param ctx 上下文
     */
    void doAction(List<WordsRule.Words> words, WordsContext ctx);
}