package controller;

import Model.Product;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NewReadData {
    private File file;
    private Workbook workbook;
    private Sheet sheet;
    private String shipName;

    public NewReadData(File file) {
        this.file = file;
        this.workbook = workbookFromFile(this.file);
        this.sheet = sheetFromWorkbook(this.workbook);
        this.shipName = getShipNameAndNumberFromFile(this.sheet);
    }

    public String getShipName() {
        return shipName;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public List<Product> productsFromExcelOrder(@NotNull Sheet sheet){
        List<Product> products = new ArrayList<>();
        for (Row row : sheet){
            if (row.getCell(0).getCellType() != CellType.BLANK) {
                Product product = mapExcelRowToProduct(row);
                products.add(product);
                System.out.println(product.toString());
            }
        }
        return products;
    }

    public Product mapExcelRowToProduct(@NotNull Row row) {
        DataFormatter dataFormatter = new DataFormatter();
        String bigDecUse = dataFormatter.formatCellValue(row.getCell(0));
        BigDecimal constUseQty = new BigDecimal(bigDecUse);
        String packaging = dataFormatter.formatCellValue(row.getCell(1));
        String itemName = dataFormatter.formatCellValue(row.getCell(2));
        return new Product(constUseQty, packaging, itemName);
    }

    private static String getShipNameAndNumberFromFile(@NotNull Sheet sheet) {
        Cell cellContainingShipName = sheet.getRow(0).getCell(2);
        return cellContainingShipName.getStringCellValue();
    }

    private static Sheet sheetFromWorkbook(@NotNull Workbook workbook){
        int sheetIndex = 0;
        if (workbook.getNumberOfSheets() > 1) {
            sheetIndex = 2;
        }
        return workbook.getSheetAt(sheetIndex);
    }

    private static Workbook workbookFromFile(File file) { // create a workbook from incoming order file
        Workbook workbook = null; // initiate return
        try {
            // find the file extension
            String fileExtension = FilenameUtils.getExtension(file.getName());
            // create an input stream from incoming file
            FileInputStream excelFile = new FileInputStream(file);
            if (fileExtension.equals("xls")) { // if file has extension of .xls
                workbook = new HSSFWorkbook(excelFile); // it needs to be parsed as HSSF
            } else {
                workbook = new XSSFWorkbook(excelFile); // otherwise parse as XSSF
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return
        return workbook;
    }
}


