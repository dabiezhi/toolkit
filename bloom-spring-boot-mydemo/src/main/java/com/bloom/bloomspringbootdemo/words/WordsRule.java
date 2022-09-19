package com.bloom.bloomspringbootdemo.words;

import lombok.Data;

import java.util.List;

/**
 * @author curry
 * Created by on 2022-08-19 下午2:49
 */
@Data
public class WordsRule {

    /**
     * 业务code
     */
    private String     bizCode;
    /**
     * 如果特殊的规则没匹配，则走默认话术规则
     */
    private Rule       defaultRule;
    /**
     * 特殊的话术规则
     */
    private List<Rule> rules;

    @Data
    public static class Rule {
        /**
         * 话术规则-条件
         */
        private String      condition;
        /**
         * 符合条件的话术
         */
        private List<Words> words;
    }

    @Data
    public static class Words {
        /**
         * 话术类型
         */
        private Integer contentType;
        /**
         * 话术内容
         */
        private String  content;
    }

}