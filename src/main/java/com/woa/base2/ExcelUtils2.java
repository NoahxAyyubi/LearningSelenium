package com.woa.base2;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils2 {

    public static void main(String[] args) throws IOException {
        System.out.println("Specific Data********");
        String specificData = getValueOfSpecificColumnAndRow("src/test/resources/Book.xls", 0, 1);
        System.out.println(specificData);

        System.out.println("Specific Datas Of Row********");
        String[] specificRow = getAllValuesOfARow("src/test/resources/Book.xls", 0);
        for (String s : specificRow) {
            System.out.println(s);
        }
    }

    public static String[] getAllValuesOfARow(String path, int column) throws IOException {
        String[] data;
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        int numberOfRows = sheet.getLastRowNum();
        int numberOfCol = sheet.getRow(column).getLastCellNum();
        data = new String[numberOfRows + 1];
        for (int i = 1; i < data.length; i++) {
            HSSFRow rows = sheet.getRow(i);
            for (int j = 0; j < numberOfCol; j++) {
                HSSFCell cell = rows.getCell(j);
                String cellData = getCellValue(cell);
                data[i] = cellData;
            }
        }
        return data;
    }

    public static String getValueOfSpecificColumnAndRow(String path, int column, int row) throws IOException {
        String data;
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        int numberOfRows = sheet.getLastRowNum();
        int numberOfCol = sheet.getRow(0).getLastCellNum();
        HSSFRow rows = sheet.getRow(row);
        HSSFCell cell = rows.getCell(column);
        String cellData = getCellValue(cell);
        data = cellData;
        return data;
    }

    private static String getCellValue(HSSFCell cell) {
        Object value = null;
        int dataType = cell.getCellType();
        switch (dataType) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
        }
        return value.toString();
    }
}