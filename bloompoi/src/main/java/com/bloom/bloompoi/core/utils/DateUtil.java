package com.bloom.bloompoi.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期格式化工具类
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:10
 */
public final class DateUtil {

    public static Date toDate(String value, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LocalDate toLocalDate(String value, String pattern) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime toLocalDateTime(String value, String pattern) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static String toString(Date value, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(value);
    }

    public static String toString(LocalDate value, String pattern) {
        return value.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String toString(LocalDateTime value, String pattern) {
        return value.format(DateTimeFormatter.ofPattern(pattern));
    }

}
