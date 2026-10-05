package webutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public static String getCellValue(String filePath, String sheetName, int rowNumber, String headerName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        // Get header row (usually row 0)
        Row headerRow = sheet.getRow(0);
        int columnIndex = -1;

        // Find the column index for the header name
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell.getStringCellValue().trim().equalsIgnoreCase(headerName.trim())) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
            workbook.close();
            throw new IllegalArgumentException("Header not found: " + headerName);
        }

        // Get the specific row and cell
        Row dataRow = sheet.getRow(rowNumber);
        if (dataRow == null || dataRow.getCell(columnIndex) == null) {
            workbook.close();
            return null;
        }

        dataRow.getCell(columnIndex).setCellType(CellType.STRING);
        String value = dataRow.getCell(columnIndex).getStringCellValue();

        workbook.close();
        return value;
    }


    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < dataRow.getLastCellNum(); j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell dataCell = dataRow.getCell(j);

                    dataMap.put(headerCell.getStringCellValue(), getCellValue(dataCell));
                }

                dataList.add(dataMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default: return "";
        }
    }
}

