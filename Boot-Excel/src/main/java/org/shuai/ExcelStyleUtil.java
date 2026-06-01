package org.shuai;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;

/**
 * EasyPoi 自定义样式类
 */
public class ExcelStyleUtil implements IExcelExportStyler {

    private static final short FONT_SIZE_TITLE = 16;   // 大标题字号
    private static final short FONT_SIZE_HEADER = 12;  // 表头字号
    private static final short FONT_SIZE_DATA = 11;    // 数据字号

    private CellStyle headerStyle;   // 大标题样式
    private CellStyle titleStyle;    // 表头样式
    private CellStyle dataStyle;     // 数据行样式

    public ExcelStyleUtil(Workbook workbook) {
        this.init(workbook);
    }

    private void init(Workbook workbook) {
        this.headerStyle = createHeaderStyle(workbook);
        this.titleStyle = createTitleStyle(workbook);
        this.dataStyle = createDataStyle(workbook);
    }

    /**
     * 大标题样式
     */
    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = getBaseStyle(workbook);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints(FONT_SIZE_TITLE);
        style.setFont(font);
        return style;
    }

    /**
     * 表头样式（列名那一行）- 蓝底白字效果
     */
    private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = getBaseStyle(workbook);

        // 背景色：蓝色
        style.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 字体：白色加粗
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints(FONT_SIZE_HEADER);
        style.setFont(font);

        return style;
    }

    /**
     * 数据行样式
     */
    private CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = getBaseStyle(workbook);

        Font font = workbook.createFont();
        font.setFontHeightInPoints(FONT_SIZE_DATA);
        style.setFont(font);

        return style;
    }

    /**
     * 基础样式（边框 + 居中 + 自动换行）
     */
    private CellStyle getBaseStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // 设置边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        // 水平居中 + 垂直居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 自动换行
        style.setWrapText(true);

        return style;
    }

    // ========== 接口需要实现的方法 ==========

    @Override
    public CellStyle getHeaderStyle(short color) {
        return headerStyle;
    }

    @Override
    public CellStyle getTitleStyle(short color) {
        return titleStyle;
    }

    @Override
    public CellStyle getStyles(boolean b, ExcelExportEntity excelExportEntity) {
        return dataStyle;
    }

    // 其他方法返回 null 或默认样式即可
    @Override
    public CellStyle getTemplateStyles(boolean isSingleLine, ExcelForEachParams params) {
        return null;
    }

    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object data, Object obj) {
        return dataStyle;
    }
}