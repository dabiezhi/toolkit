package com.bloom.bloomutils.core.utils;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合Stream工具类
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-07 10:24
 */
public class StreamUtil {

    private StreamUtil() {

    }

    /**
     * Collection转换List
     */
    public static <T, R> List<R> toList(Collection<T> collection, Function<T, R> mapper) {
        return CollectionUtils.isEmpty(collection) ? Collections.emptyList()
            : collection.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * Collection过滤
     */
    public static <T> List<T> listFilter(Collection<T> collection, Predicate<T> predicates) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().filter(predicates).collect(Collectors.toList());
    }

    /**
     * Collection转Map(聚合)
     */
    public static <T, R> Map<R, List<T>> toGroupMap(Collection<T> collection,
                                                    Function<T, R> classifier) {
        return CollectionUtils.isEmpty(collection) ? Collections.emptyMap()
            : collection.stream().collect(Collectors.groupingBy(classifier));
    }

    /**
     * Collection转Map(覆盖，key能冲突)
     */
    public static <T, K, V> Map<K, V> toMap(Collection<T> collection, Function<T, K> key,
                                            Function<T, V> value) {
        return CollectionUtils.isEmpty(collection) ? Collections.emptyMap()
            : collection.stream().collect(Collectors.toMap(key, value, (key1, key2) -> key2));
    }

    /**
     * Collection转换Set
     */
    public static <T, R> Set<R> toSet(Collection<T> collection, Function<T, R> mapper) {
        return CollectionUtils.isEmpty(collection) ? Collections.emptySet()
            : collection.stream().map(mapper).collect(Collectors.toSet());
    }

}