/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.utils;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author taosy
 * Created by on 2022-02-25 下午2:22
 */
@NoArgsConstructor
@Component
public class ApolloUtils {

    public static String authorLabelKey;
    public static String cancerLabelKey;

    @Value("${authorLabelKey:author_baseinfo}")
    public void author(String authorLabelKey) {
        ApolloUtils.authorLabelKey = authorLabelKey;
    }

    @Value("${cancerLabelKey:cancer_type}")
    public void cancer(String cancerLabelKey) {
        ApolloUtils.cancerLabelKey = cancerLabelKey;
    }

}