package controller;

import Model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ReadDataFile {

    File TestFile = new File("TestFile.csv");
    File ActualOrderFile = new File("ActualOrderFile.csv");

    public String readXlForShipName() {
        try {
            String shipNameAndNum = "";
            FileInputStream excelFile = new FileInputStream("MV POLAR BRASIL-325082.xlsm");
            Workbook workbook = new XSSFWorkbook(excelFile);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Sheet> sheet = workbook.sheetIterator();
            while(sheet.hasNext()) {
                Sheet sh = sheet.next();
                Iterator<Row> rowIterator = sh.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    for (Cell cell : row) {
                        if (cell.getCellType() != CellType.BLANK) {
                            if(row.getRowNum() == 0) {
                                shipNameAndNum = cell.getStringCellValue();
                            }
                        }
                    }
                }
            }
        return shipNameAndNum;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public String readShipName() {
        String shipName = "";
        try (Scanner scanner = new Scanner(ActualOrderFile)) {
            while(scanner.hasNext()) {
                shipName = scanner.nextLine().split(",")[2];
                break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return shipName;
    }


    //1.00,BX,MACINTOSH APPLES 40  LB BOX
    public List<Product> loadFile() {
        List<Product> orderSheet = new ArrayList<>();

        try (Scanner scanner = new Scanner(ActualOrderFile)) {
            int counter = 1;
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                if (counter >= 4) {
                    Product currentProduct = null;
                    String[] currentLineSplit = currentLine.split(",");
                    if (currentLineSplit.length >= 3) {
                        String quantity = currentLineSplit[0];
                        BigDecimal qtyAsBD = new BigDecimal(quantity);
                        String packaging = currentLineSplit[1];
                        String itemName = currentLineSplit[2];
                        currentProduct = new Product(qtyAsBD, packaging, itemName);
                        orderSheet.add(currentProduct);
                    }
                } counter++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Bad number.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return orderSheet;
    }
}
