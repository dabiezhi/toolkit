package com.bloom.bloompoi.core.utils;

import java.util.Collection;

import lombok.experimental.UtilityClass;

/**
 * 集合工具类
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:10
 */
@UtilityClass
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }
}
