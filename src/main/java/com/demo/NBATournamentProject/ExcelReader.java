package com.demo.NBATournamentProject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static List<Map<String, List<Map<String, String>>>> readExcelPlayer(String filePath) {
        List<Map<String, List<Map<String, String>>>> dataList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);

                if (!sheet.getSheetName().contains("Stats")) {
                    Map<String, List<Map<String, String>>> sheetDataMap = new LinkedHashMap<>();
                    String sheetName = sheet.getSheetName();

                    List<Map<String, String>> sheetRows = new ArrayList<>();

                    for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        if (row != null && row.getFirstCellNum() >= 0) {
                            Map<String, String> dataMap = new LinkedHashMap<>();
                            if (isValidRow(row)) {
                            	fillDataMapPlayer(row, dataMap);
                                if (!dataMap.isEmpty()) {
                                    sheetRows.add(dataMap);
                                }
                            }
                        }
                    }

                    // Only add the sheetDataMap if sheetRows is not empty
                    if (!sheetRows.isEmpty()) {
                        sheetDataMap.put(sheetName, sheetRows);
                        dataList.add(sheetDataMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
    
    
    public static List<Map<String, List<Map<String, String>>>> readExcelTeamStats(String filePath) {
        List<Map<String, List<Map<String, String>>>> dataList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);

                if (sheet.getSheetName().equals("Team Stats")) {
                    Map<String, List<Map<String, String>>> sheetDataMap = new LinkedHashMap<>();
                    String sheetName = sheet.getSheetName();

                    List<Map<String, String>> sheetRows = new ArrayList<>();

                    for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        if (row != null && row.getFirstCellNum() >= 0) {
                            Map<String, String> dataMap = new LinkedHashMap<>();
                            if (isValidRow(row)) {
                            	fillDataMapTeam(row, dataMap);
                                if (!dataMap.isEmpty()) {
                                    sheetRows.add(dataMap);
                                }
                            }
                        }
                    }

                    // Only add the sheetDataMap if sheetRows is not empty
                    if (!sheetRows.isEmpty()) {
                        sheetDataMap.put(sheetName, sheetRows);
                        dataList.add(sheetDataMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
    
    
    public static List<Map<String, List<Map<String, String>>>> readExcelOpponentStats(String filePath) {
        List<Map<String, List<Map<String, String>>>> dataList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);

                if (sheet.getSheetName().equals("Opponent Stats")) {
                    Map<String, List<Map<String, String>>> sheetDataMap = new LinkedHashMap<>();
                    String sheetName = sheet.getSheetName();

                    List<Map<String, String>> sheetRows = new ArrayList<>();

                    for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        if (row != null && row.getFirstCellNum() >= 0) {
                            Map<String, String> dataMap = new LinkedHashMap<>();
                            if (isValidRow(row)) {
                            	fillDataMapTeam(row, dataMap);
                                if (!dataMap.isEmpty()) {
                                    sheetRows.add(dataMap);
                                }
                            }
                        }
                    }

                    // Only add the sheetDataMap if sheetRows is not empty
                    if (!sheetRows.isEmpty()) {
                        sheetDataMap.put(sheetName, sheetRows);
                        dataList.add(sheetDataMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
    
    
    
    
    
    
    
    
    
    
    
    

    private static String getValueAsString(Cell cell, String columnName) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    // Check if the cell is a percentage value
                    if (cell.getCellStyle().getDataFormatString().contains("%")) {
                        // Format percentage values without rounding
                        return new DecimalFormat("#0.0%").format(cell.getNumericCellValue());
                    } else {
                        // Format other numeric values
                        return new DecimalFormat("#.#").format(cell.getNumericCellValue());
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                switch (cellValue.getCellType()) {
                    case STRING:
                        return cellValue.getStringValue();
                    case NUMERIC:
                        // Check if the formula result is a percentage value
                        if (cell.getCellStyle().getDataFormatString().contains("%")) {
                            // Format percentage values without rounding
                            return new DecimalFormat("#0.0%").format(cellValue.getNumberValue());
                        } else if (columnName.equals("PER")) {
                            return new DecimalFormat("0.00").format(cellValue.getNumberValue());
                        } else if (columnName.equals("FPTS") || columnName.equals("GS")) {
                            // For game score, keep the decimal without rounding
                            return new DecimalFormat("0.0").format(cellValue.getNumberValue());
                        } else {
                            // Format other numeric values
                            return new DecimalFormat("#.#").format(cellValue.getNumberValue());
                        }
                    case BOOLEAN:
                        return String.valueOf(cellValue.getBooleanValue());
                    default:
                        return "";
                }
            default:
                return "";
        }
    }

    private static boolean isValidRow(Row row) {
        // Check if the row is not null and if it has at least one cell with data
        return row != null && row.getPhysicalNumberOfCells() > 0;
    }

    private static void fillDataMapPlayer(Row row, Map<String, String> dataMap) {
        // Define the mapping between column indexes and column names
        String[] columnNames = {"Games", "PTS", "REB", "AST", "BLK", "STL", "TO", "FGM", "FGA",
                "3PTM", "3PTA", "FTM", "FTA", "OR", "FLS", "+/-", "FG%", "3PT%",
                "FT%", "MIN", "PRF", "DNK", "PER", "FPTS", "GS", "POG"};

        // Check if the first cell meets the exclusion criteria
        String firstCellValue = getValueAsString(row.getCell(0), columnNames[0]);
        if (isExcludedRow(firstCellValue)) {
            return; // Skip this row
        }

        // Iterate through each cell in the row and fill the dataMap
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                // Get the value of the cell as a string
                String cellValue = getValueAsString(cell, columnNames[i]);
                // Skip adding empty values to the dataMap
                if (!cellValue.isEmpty()) {
                    // Put the value into the dataMap with the corresponding column name
                    dataMap.put(columnNames[i], cellValue);
                }
            }
        }
    }
    
    private static void fillDataMapTeam(Row row, Map<String, String> dataMap) {
        // Define the mapping between column indexes and column names
        String[] columnNames = {"Games", "PTS", "FGM", "FGA", "3PTM", "3PTA", "FTM", "FTA", "DNK",
                "FBPTS", "PTSiP", "SCPTS", "BPTS", "AST", "OREB", "DREB", "TREB", "STL",
                "BLK", "TO", "PTS Off", "TF", "TOP", "FG%", "3PT%", "FT%", "POS"};

        // Check if the first cell meets the exclusion criteria
        String firstCellValue = getValueAsString(row.getCell(0), columnNames[0]);
        if (isExcludedRow(firstCellValue)) {
            return; // Skip this row
        }

        // Iterate through each cell in the row and fill the dataMap
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                // Get the value of the cell as a string
                String cellValue = getValueAsString(cell, columnNames[i]);
                // Skip adding empty values to the dataMap
                if (!cellValue.isEmpty()) {
                    // Put the value into the dataMap with the corresponding column name
                    dataMap.put(columnNames[i], cellValue);
                }
            }
        }
    }

    // Method to check if a row should be excluded based on the first cell value
    private static boolean isExcludedRow(String value) {
        return value.equals("0") || value.isEmpty() || value.equals("AVG") || value.equals("TOTAL") || value.equals("Games") || 
        		value.equals("PPP") || value.equals("OER") || value.equals("Opp PPP") || value.equals("DER") || value.equals("PD") ;
        
        
        

    }
}
