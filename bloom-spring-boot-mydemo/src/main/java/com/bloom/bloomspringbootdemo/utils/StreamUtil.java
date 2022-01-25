package com.bloom.bloomspringbootdemo.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.CollectionUtils;

/**
 * @author taosy
 * Created by on 2022-01-14 下午4:53
 */
public class StreamUtil {

    private StreamUtil() {

    }

    /**
     * 简单的List转换
     */
    public static <T, R> List<R> toList(List<T> list, Function<T, R> mapper) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList()
            : list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 简单的List转换，并且去重
     */
    public static <T, R> List<R> toDistinctList(List<T> list, Function<T, R> mapper) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList()
            : list.stream().map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * 简单的List转换，过滤并且去重
     */
    public static <T, R> List<R> filterToDistinctList(List<T> list, Predicate<T> predicate,
                                                      Function<T, R> mapper) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList()
            : list.stream().filter(predicate).map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * 简单的List转换，按照对象某个key过滤并且去重
     */
    public static <T> List<T> toDistinctListByKey(List<T> list,
                                                  Function<? super T, ?> keyExtractor) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList()
            : list.stream().filter(StreamUtil.distinctByKey(keyExtractor))
                .collect(Collectors.toList());
    }

    /**
     * 按照对象某个key过滤并且去重
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * 简单的Set转换
     */
    public static <T, R> Set<R> toSet(Set<T> set, Function<T, R> mapper) {
        return CollectionUtils.isEmpty(set) ? Collections.emptySet()
            : set.stream().map(mapper).collect(Collectors.toSet());
    }

    /**
     * 简单的Set转换
     */
    public static <T, R> Set<R> toSet(List<T> list, Function<T, R> mapper) {
        return CollectionUtils.isEmpty(list) ? Collections.emptySet()
            : list.stream().map(mapper).collect(Collectors.toSet());
    }

    /**
     * 简单的List转Map（聚合）
     */
    public static <T, R> Map<R, List<T>> toGroupMap(List<T> list, Function<T, R> classifier) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyMap()
            : list.stream().collect(Collectors.groupingBy(classifier));
    }

    /**
     * 简单的List转Map（覆盖，key能冲突）
     */
    public static <T, K, V> Map<K, V> toMap(List<T> list, Function<T, K> key,
                                            Function<T, V> value) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyMap()
            : list.stream().collect(Collectors.toMap(key, value, (key1, key2) -> key2));
    }

    /**
     * list转聚合map，value也是一个map
     */
    public static <T, R1, R2> Map<R1, Map<R2, T>> toGroupMap(List<T> list,
                                                             Function<T, R1> classifier,
                                                             Function<T, R2> classifier2) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<R1, List<T>> map = list.stream().collect(Collectors.groupingBy(classifier));
        Map<R1, Map<R2, T>> result = Collections.emptyMap();
        // value的list对象转map
        map.forEach((key, value) -> {
            result.put(key, toMap(value, classifier2, v -> v));
        });
        return result;
    }

    /**
     * 连续2次分组
     */
    public static <T, R, R2> Map<R, Map<R2, List<T>>> toGroupMapTwice(List<T> list,
                                                                      Function<T, R> classifier,
                                                                      Function<T, R2> classifier2) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<R, List<T>> map = list.stream().collect(Collectors.groupingBy(classifier));
        Map<R, Map<R2, List<T>>> result = Collections.emptyMap();
        map.forEach((key, value) -> {
            // value的list对象转map
            result.put(key, toGroupMap(value, classifier2));
        });
        return result;
    }

    /**
     * 简单的List过滤
     */
    public static <T> List<T> listFilter(List<T> list, Predicate<T>... predicates) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        for (Predicate<T> p : predicates) {
            list = list.stream().filter(p).collect(Collectors.toList());
        }
        return list;
    }

    public static <T, R> List<R> arrayTrans(T[] arrays, Function<T, R> mapper) {
        if (Objects.isNull(arrays) || arrays.length <= 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(arrays).map(mapper).collect(Collectors.toList());
    }

    /**
     * 把集合转换成流，如果为null则转换成空流
     *
     * @param collection 集合
     * @param <T>        集合中的类型
     * @return collection的流
     */
    public static <T> Stream<T> ofNullable(Collection<T> collection) {
        return CollectionUtils.isEmpty(collection) ? Stream.empty() : collection.stream();
    }

}