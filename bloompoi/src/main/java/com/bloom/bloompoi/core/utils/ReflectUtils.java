package com.bloom.bloompoi.core.utils;

import com.bloom.bloompoi.Constant;
import com.bloom.bloompoi.writer.CellWrapper;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

/**
 * 反射工具类
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:10
 */
public class ReflectUtils {

    private ReflectUtils() {
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName 属性名
     * @param o         对象
     * @return 对象
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Class[] classes = new Class[] {};
            Method method = o.getClass().getMethod(getter, classes);
            Object[] obj = new Object[] {};
            return method.invoke(o, obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<T> getListByReflect(CellWrapper cellWrapper) {
        String value = cellWrapper.getCellValue()
            .substring(cellWrapper.getCellValue().indexOf(Constant.LEFT_BRACKET) + 1,
                cellWrapper.getCellValue().indexOf(Constant.RIGHT_BRACKET));
        if (StringUtil.isBlank(value)) {
            return Collections.emptyList();
        }
        return (List<T>) ReflectUtils.getFieldValueByName(value, cellWrapper.getData());
    }

}
