package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtils {
    private static final String DEFAULT_WORKBOOK = "LoginData.xlsx";

    private ExcelUtils() {
    }

    public static List<Map<String, String>> readSheet(String sheetName) {
        return readSheet(DEFAULT_WORKBOOK, sheetName);
    }

    public static List<Map<String, String>> readSheet(String workbookName, String sheetName) {
        try (InputStream inputStream = ExcelUtils.class.getClassLoader().getResourceAsStream(workbookName)) {
            if (inputStream == null) {
                throw new IllegalStateException(workbookName + " was not found in src/test/resources");
            }
            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new IllegalArgumentException("Sheet not found: " + sheetName);
                }
                return toMaps(sheet);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read Excel sheet: " + sheetName, e);
        }
    }

    private static List<Map<String, String>> toMaps(Sheet sheet) {
        DataFormatter formatter = new DataFormatter();
        Row header = sheet.getRow(sheet.getFirstRowNum());
        if (header == null) {
            return List.of();
        }

        List<String> headers = new ArrayList<>();
        for (int column = 0; column < header.getLastCellNum(); column++) {
            headers.add(formatter.formatCellValue(header.getCell(column)).trim());
        }

        List<Map<String, String>> rows = new ArrayList<>();
        for (int rowIndex = sheet.getFirstRowNum() + 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            Map<String, String> values = new LinkedHashMap<>();
            boolean hasData = false;
            for (int column = 0; column < headers.size(); column++) {
                Cell cell = row.getCell(column);
                String value = formatter.formatCellValue(cell).trim();
                values.put(headers.get(column), value);
                hasData = hasData || !value.isBlank();
            }
            if (hasData) {
                rows.add(values);
            }
        }
        return rows;
    }
}
