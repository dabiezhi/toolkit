/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi;

import com.bloom.bloompoi.model.CardSecret;
import com.bloom.bloompoi.model.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * TODO
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-07 16:18
 */
public class SpelTest {

    public static void main(String[] args) {
        // 使用SpEL进行key的解析
        ExpressionParser PARSER = new SpelExpressionParser();
        // SpEL上下文
        StandardEvaluationContext CONTEXT = new StandardEvaluationContext();

        CardSecret secret = new CardSecret();
        secret.setCardType(1);
        secret.setSecret("niadafd");
        secret.setName("花花");
        List<CardSecret> list = new ArrayList<>();
        list.add(secret);
        User user = new User();
        user.setList(list);
        CONTEXT.setVariable(user.getClass().getSimpleName(), user);
        String result = PARSER.parseExpression("#User.list[0].name").getValue(CONTEXT, String.class);
        System.out.println(result);
    }
}
