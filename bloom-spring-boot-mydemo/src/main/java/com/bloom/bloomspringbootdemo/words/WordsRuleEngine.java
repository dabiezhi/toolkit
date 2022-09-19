package com.bloom.bloomspringbootdemo.words;

import com.bloom.bloomspringbootdemo.utils.StreamUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author curry
 * Created by on 2022-08-19 下午2:49
 */
public class WordsRuleEngine {

    private String         bizCode;
    private WordsRule      wordsRule;
    private WordsRule.Rule defaultRule;
    private WordsCondition wordsCondition;
    private WordsAction    wordsAction;

    public static WordsRuleEngine of(String bizCode) {
        return new WordsRuleEngine().bizCode(bizCode);
    }

    public WordsRuleEngine bizCode(String bizCode) {
        this.bizCode = Objects.requireNonNull(bizCode, "bizCode is null");
        this.wordsRule = Optional.ofNullable(getWordsRule()).orElseGet(WordsRule::new);
        this.defaultRule = Optional.ofNullable(wordsRule.getDefaultRule())
            .orElseGet(WordsRule.Rule::new);
        this.wordsCondition = (rules, ctx) -> Optional.empty();
        this.wordsAction = (words, ctx) -> {
        };
        return this;
    }

    public WordsRuleEngine when(WordsCondition wordsCondition) {
        this.wordsCondition = wordsCondition;
        return this;
    }

    public WordsRuleEngine then(WordsAction wordsAction) {
        this.wordsAction = wordsAction;
        return this;
    }

    public List<WordsRule.Words> execute(WordsContext ctx) {
        List<WordsRule.Words> optionalWords;
        if (CollectionUtils.isEmpty(wordsRule.getRules())) {
            optionalWords = this.defaultRule.getWords();
        } else {
            optionalWords = wordsCondition.eval(wordsRule.getRules(), ctx)
                .orElseGet(() -> this.defaultRule.getWords());
        }

        if (Objects.nonNull(wordsAction) && !CollectionUtils.isEmpty(optionalWords)) {
            wordsAction.doAction(optionalWords, ctx);
        }
        return optionalWords;
    }

    private WordsRule getWordsRule() {
        List<WordsRule> wordsList = new ArrayList<>();
        return StreamUtil.toMap(wordsList, WordsRule::getBizCode, Function.identity())
            .get(bizCode);
    }

}