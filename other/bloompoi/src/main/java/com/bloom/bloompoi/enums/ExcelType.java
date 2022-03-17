package com.bloom.bloompoi.enums;

/**
 * Excel后缀类型
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:10
 */
public enum ExcelType {

    /**
     * xls
     */
    XLS,
    /**
     * xlsx
     */
    XLSX;

    public static ExcelType getExcelType(String fileName) {
        if (fileName.toUpperCase().endsWith(XLS.toString())) {
            return ExcelType.XLS;
        }
        if (fileName.toUpperCase().endsWith(XLSX.toString())) {
            return ExcelType.XLSX;
        }
        return ExcelType.XLS;
    }
}
