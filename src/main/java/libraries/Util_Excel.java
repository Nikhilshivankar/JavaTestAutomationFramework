package libraries;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Util_Excel {

    private static int int_current_row_num = 0;
    private static String page_test_data;
    private static XSSFWorkbook td_workbook;
    private static XSSFSheet test_sheet;
    private static XSSFCell Cell;

    public static void openExcel_TestData() {
        if (test_sheet == null) {
            try {
                PropertyReader propReader = new PropertyReader();
                String testDataPath = "src/main/resources/TestData/TestData.xlsx";
                try {
                    testDataPath = propReader.getProperty("testDataExcel");
                } catch (Exception e) {
                    System.out.println("Could not read 'testDataExcel' from configuration. Using default.");
                }

                FileInputStream ExcelFile = new FileInputStream(testDataPath);
                td_workbook = new XSSFWorkbook(ExcelFile);
                test_sheet = td_workbook.getSheet("TestData");
                if (test_sheet == null) {
                    test_sheet = td_workbook.getSheetAt(0);
                }
                System.out.println("Captured excel testdata sheet. Last row number: " + test_sheet.getLastRowNum());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static HashMap<String, String> getMapTestData(String str_testname) throws Exception {
        openExcel_TestData();
        HashMap<String, String> mapTestData = new HashMap<String, String>();
        int int_lastrow = test_sheet.getLastRowNum();
        int int_TCColumn = 0;

        for (int intRow = 1; intRow <= int_lastrow; intRow++) {
            if (test_sheet.getRow(intRow) != null && 
                test_sheet.getRow(intRow).getCell(int_TCColumn) != null &&
                test_sheet.getRow(intRow).getCell(int_TCColumn).getStringCellValue().equalsIgnoreCase(str_testname)) {

                int int_LastColumn = test_sheet.getRow(intRow).getLastCellNum();

                for (int intCol = 1; intCol < int_LastColumn; intCol++) {
                    DataFormatter dataFormatter = new DataFormatter();
                    String strKey = dataFormatter.formatCellValue(test_sheet.getRow(0).getCell(intCol));
                    String strValue = dataFormatter.formatCellValue(test_sheet.getRow(intRow).getCell(intCol));

                    mapTestData.put(strKey, strValue);
                }
                break;
            } else if (intRow == int_lastrow) {
                throw new Exception("Unable to find test data row mapped against test name = " + str_testname);
            }
        }

        if (mapTestData.size() > 0) {
            System.out.println("Captured Test Data for test: " + str_testname);
        }

        return mapTestData;
    }

    public static void get_test_xl_row(String str_testname) {
        openExcel_TestData();
        int int_lastrow = test_sheet.getLastRowNum();
        boolean bln_testfound = false;

        for (int i = 1; i <= int_lastrow; i++) {
            if (test_sheet.getRow(i) != null && 
                test_sheet.getRow(i).getCell(1) != null &&
                test_sheet.getRow(i).getCell(1).getStringCellValue().equals(str_testname)) {
                int_current_row_num = i;
                bln_testfound = true;
                break;
            }
        }
        if (!bln_testfound) {
            System.out.println("Test Not found -> " + str_testname);
        }
    }

    public static String get_page_testdata(String str_pagename) {
        openExcel_TestData();
        int int_lastcolumn = test_sheet.getRow(int_current_row_num).getLastCellNum();
        boolean bln_testdatafound = false;

        for (int i = 0; i < int_lastcolumn; i++) {
            if (test_sheet.getRow(0).getCell(i) != null &&
                test_sheet.getRow(0).getCell(i).getStringCellValue().equals(str_pagename)) {
                bln_testdatafound = true;
                page_test_data = test_sheet.getRow(int_current_row_num).getCell(i).getStringCellValue();
            }
        }
        return page_test_data;
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = test_sheet.getRow(RowNum).getCell(ColNum);
            if (Cell == null || Cell.getCellType() == CellType.BLANK) {
                return "";
            } else {
                return Cell.getStringCellValue();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    public static String get_field_value(String str_field_name) {
        if (page_test_data == null) return null;
        String[] inp_list = page_test_data.split("\\|");
        String str_field_value = null;

        for (String str_val_set : inp_list) {
            if (str_val_set.contains("=") && str_val_set.split("=")[0].equalsIgnoreCase(str_field_name)) {
                str_field_value = str_val_set.split("=")[1];
                str_field_value = str_field_value.trim();
                break;
            }
        }
        System.out.println("Current Field = " + str_field_name + " --> value = " + str_field_value);
        return str_field_value;
    }
}
