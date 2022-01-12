package com.bloom.bloompoi.core.utils;

import com.bloom.bloompoi.Constant;
import com.bloom.bloompoi.annotation.Excel;
import com.bloom.bloompoi.annotation.ExcelField;
import com.bloom.bloompoi.converter.Converter;
import com.bloom.bloompoi.converter.EmptyConverter;
import com.bloom.bloompoi.core.lang.SimpleCache;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import lombok.experimental.UtilityClass;

/**
 * Excel工具类
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:10
 */
@UtilityClass
public class ExcelUtil {

    private static final SimpleCache<Class<?>, List<Field>> EXCEL_FIELD_CACHE = new SimpleCache<>();

    public static String getSheetName(Object item) {
        Excel excel = item.getClass().getAnnotation(Excel.class);
        return StringUtil.isNotBlank(excel.sheetName()) ? Constant.SHEET_ZERO : excel.sheetName();
    }

    public static Map<String, Integer> getFieldNameAndColMap(Class<?> type) {
        List<Field> fields = getAndSaveFields(type);
        Map<String, Integer> map = new HashMap<>();
        fields.forEach(field -> {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (excelField.special().isSpecial()) {
                map.put(excelField.columnName(), excelField.special().specialColNum());
            } else {
                map.put(excelField.columnName(), excelField.order());
            }
        });
        return map;
    }

    public static List<Field> getAndSaveFields(Class<?> type) {
        if (CollectionUtil.isNotEmpty(EXCEL_FIELD_CACHE.get(type))) {
            return EXCEL_FIELD_CACHE.get(type);
        }
        List<Field> fields = ReflectUtil.getFiledList(type);
        fields = fields.stream().filter(u -> null != u.getAnnotation(ExcelField.class))
            .filter(s -> StringUtil.isNotBlank(s.getAnnotation(ExcelField.class).columnName()))
            .filter(u -> -999 != u.getAnnotation(ExcelField.class).order()).collect(Collectors.toList());
        return EXCEL_FIELD_CACHE.put(type, fields);
    }

    public static String validFieldByAnnotation(Object item, int row, int col, String value) {
        Field field = ExcelUtil.getFieldByCols(item.getClass(), row, col);
        if (null == field) {
            return null;
        }
        ExcelField excelField = field.getAnnotation(ExcelField.class);
        if (StringUtil.isBlank(value) && !excelField.nullable()) {
            return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不能为空";
        }
        if (value.length() > excelField.maxLength() && excelField.maxLength() != 0) {
            return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "长度不能超过" + excelField
                .maxLength();
        }
        if (value.length() < excelField.minLength() && excelField.minLength() != 0) {
            return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "长度不能小于" + excelField
                .minLength();
        }
        switch (excelField.regexType()) {
        case SPECIAL_CHAR:
            if (RegexUtil.hasSpecialChar(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不能含有特殊字符";
            }
            break;
        case CHINESE:
            if (RegexUtil.isChinese2(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不能含有中文字符";
            }
            break;
        case EMAIL:
            if (!RegexUtil.isEmail(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "地址格式不正确";
            }
            break;
        case IP:
            if (!RegexUtil.isIp(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "IP地址格式不正确";
            }
            break;
        case NUMBER:
            if (!RegexUtil.isNumber(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不是数字";
            }
            break;
        case PHONE:
            if (!RegexUtil.isPhoneNumber(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不是正规手机号";
            }
            break;
        case DATE_YYYY_MM_DD:
            if (!RegexUtil.isValidDate(value, "yyyy-MM-dd")) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不是正规日期格式";
            }
            break;
        case DATE_YYYY_MM_DD_HH_MM:
            if (!RegexUtil.isValidDate(value, "yyyy-MM-dd HH:mm")) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不是正规日期格式";
            }
            break;
        case IDENTITY_CARD:
            if (!RegexUtil.isIdentifyCard(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不是正规身份证格式";
            }
            break;
        case DOUBLE:
            if (!RegexUtil.isDouble(value)) {
                return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "不是浮点格式";
            }
            break;
        default:
            break;
        }
        if (StringUtil.isNotBlank(excelField.regExp()) && value.matches(excelField.regExp())) {
            return excelField.columnName() + Constant.LEFT_BRACE + value + Constant.RIGHT_BRACE + "格式不正确";
        }
        return null;
    }

    public static void writeToField(Object item, int row, int col, String value) {
        Field field = ExcelUtil.getFieldByCols(item.getClass(), row, col);
        if (null != field) {
            try {
                field.setAccessible(true);
                Object newValue = asObject(field, value);
                field.set(item, newValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static Field getFieldByCols(Class<?> type, int row, int col) {
        List<Field> fields = getAndSaveFields(type);
        for (Field field : fields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (excelField.order() == col) {
                return field;
            }
        }
        return null;
    }

    public static <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings({ "unchecked" })
    public static <T> String asString(Field field, T value) {
        if (Objects.isNull(value)) {
            return Constant.EMPTY_STRING;
        }
        ExcelField excelField = field.getAnnotation(ExcelField.class);
        if (null != excelField && !excelField.convertType().equals(EmptyConverter.class)) {
            Converter converter = newInstance(excelField.convertType());
            if (null != converter) {
                return converter.write(value);
            }
        }

        if (value instanceof Date) {
            if (null != excelField && !Constant.EMPTY_STRING.equals(excelField.datePattern())) {
                return DateUtil.toString((Date) value, excelField.datePattern());
            }
        }
        return value.toString();
    }

    private static Object asObject(Field field, String value) {
        if (null == value || "".equals(value)) {
            return null;
        }
        ExcelField excelField = field.getAnnotation(ExcelField.class);
        if (null != excelField && !excelField.convertType().equals(EmptyConverter.class)) {
            Converter converter = newInstance(excelField.convertType());
            if (null != converter) {
                return converter.read(value);
            }
        }
        if (field.getType().equals(String.class)) {
            return value;
        }
        if (field.getType().equals(BigDecimal.class)) {
            return new BigDecimal(value);
        }
        if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
            return Long.valueOf(value);
        }
        if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            return Integer.valueOf(value);
        }
        if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
            return Double.valueOf(value);
        }
        if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
            return Float.valueOf(value);
        }
        if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
            return Short.valueOf(value);
        }
        if (field.getType().equals(Byte.class) || field.getType().equals(byte.class)) {
            return Byte.valueOf(value);
        }
        if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
            return Boolean.valueOf(value);
        }

        if (field.getType().equals(Date.class)) {
            if (null != excelField && !Constant.EMPTY_STRING.equals(excelField.datePattern())) {
                return DateUtil.toDate(value, excelField.datePattern());
            }
        }
        return value;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
            cell.setCellType(CellType.STRING);
        }
        switch (cell.getCellTypeEnum()) {
        case NUMERIC:
            cellValue = String.valueOf(cell.getNumericCellValue());
            break;
        case STRING:
            cellValue = String.valueOf(cell.getStringCellValue());
            break;
        case BOOLEAN:
            cellValue = String.valueOf(cell.getBooleanCellValue());
            break;
        case FORMULA:
            cellValue = String.valueOf(cell.getCellFormula());
            break;
        case BLANK:
            cellValue = "";
            break;
        case ERROR:
            cellValue = "illegal character";
            break;
        default:
            cellValue = "Unknown type";
            break;
        }
        return cellValue;
    }

}
