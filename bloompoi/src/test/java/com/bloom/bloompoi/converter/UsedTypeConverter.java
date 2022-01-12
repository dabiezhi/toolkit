package com.bloom.bloompoi.converter;

/**
 * Created by mao on 2018/2/14.
 */
public class UsedTypeConverter implements Converter<Integer> {
    @Override
    public String write(Integer value) {
        return 0 == value ? "已使用" : "未使用";
    }

    @Override
    public Integer read(String value) {
        return "已使用".equals(value) ? 0 : 1;
    }
}
