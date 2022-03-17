/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.bloompoi;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 静态变量类
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:11
 */
public interface Constant {

    /**
     * 静态常量
     */
    int DEFAULT_ORDER = -1;
    int ZERO = 0;

    /**
     * 静态符号变量
     */
    String EMPTY_STRING = "";
    String LEFT_BRACKET = "(";
    String RIGHT_BRACKET = ")";
    String ASTERISK = "*";
    String POUND = "#";
    String UNDERLINE = "_";
    String LEFT_BRACE = "{";
    String RIGHT_BRACE = "}";

    /**
     * Excel相关变量
     */
    String SHEET_ZERO = "sheet0";
    String EXCEL_MODEL_SPEL = "&model";
    String EXCEL_IMG_SPEL = "&img";
    String EXCEL_LIST_SPEL = "&list";
    String EXCEL_MAP_SPEL = "&map";

    /**
     * 默认Excel标题样式.
     *
     * @param workbook workbook对象
     * @return Excel标题样式
     */
    default CellStyle defaultTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setBold(true);
        font.setFontName("Arial");
        style.setFont(font);
        return style;
    }

    /**
     * 默认Excel行头样式.
     *
     * @param workbook workbook对象
     * @return Excel行头样式
     */
    default CellStyle defaultHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();

        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        font.setFontName("Arial");
        headerStyle.setFont(font);
        return headerStyle;
    }

    /**
     * 默认单元格样式
     *
     * @param workbook workbook对象
     * @return 单元格样式
     */
    default CellStyle defaultColumnStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setWrapText(true);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        cellStyle.setFont(font);
        return cellStyle;
    }
}
