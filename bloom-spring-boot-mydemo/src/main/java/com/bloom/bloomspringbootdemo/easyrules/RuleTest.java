package com.bloom.bloomspringbootdemo.easyrules;

import com.alibaba.fastjson.JSONObject;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

/**
 * @author curry
 * Created by on 2022-08-19 下午6:20
 */
public class RuleTest {
    public static void main(String[] args) {
        Rule rule = new RuleBuilder().name("weather rule")
            .description("if it rains then take an umbrella").priority(3)
            .when(facts -> facts.get("rain").equals(true))
            .then(facts -> facts.get("rain").equals(true)).build();

        Rule rule1 = new RuleBuilder().name("weather rule")
                .description("if it rains then take an umbrella").priority(3)
                .when(facts -> facts.get("rain").equals(true))
                .then(facts -> facts.get("rain").equals(true)).build();

        System.out.println(JSONObject.toJSONString(rule));
    }
}