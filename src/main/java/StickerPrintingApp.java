import Model.Product;
import Model.Sticker;
import controller.Audit;
import controller.ReadDataFile;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StickerPrintingApp {


    public static void main(String[] args) {

        ReadDataFile incoming = new ReadDataFile();
        List<Product> order = incoming.readXlForProducts();
        List<Product> revisedOrder = changeName(order);
        Collections.sort(revisedOrder);
        List<Sticker> stickers = retrieveStickers(revisedOrder);
        printStickers(stickers);
        incoming.readXlForProducts();
    }

    public static List<Sticker> retrieveStickers(List<Product> order) {
        ReadDataFile shipNameRead = new ReadDataFile();
        String shipName = shipNameRead.readXlForShipName();
        List<Sticker> stickers = new ArrayList<>();
        for (Product item : order) {
            stickers.addAll(item.makeStickers(shipName));
        }
        return stickers;
    }

    public static void printStickers(List<Sticker> stickers) {
        File stickersForPrinting = new File("stickers.csv");
        try (PrintWriter writer = new PrintWriter(stickersForPrinting)) {
            for (Sticker sticker : stickers) {
                writer.println(sticker);
            }
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
    }

    public static List<Product> changeName(List<Product> orderToChange) {
        BasicDataSource dataSource = new BasicDataSource();
        String url = "jdbc:postgresql://localhost:5432/delaware_ship_db";
        dataSource.setUrl(url);
        dataSource.setUsername(System.getenv("DB_USERNAME"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Product> changedNames = new ArrayList<>();
        String sql = "SELECT sticker_name, base_case FROM name_clean_up" +
                " WHERE order_name LIKE ?";

        for (Product item : orderToChange) {
            // get rid of extra spaces in incoming order name.
            String nameToCheck = item.getItemName().replaceAll("\\s+"," ");

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, "%" + nameToCheck + "%");
            if (result.next()) {
                Product changedProduct = new Product(item.getQuantity(),
                        item.getPackaging(), result.getString("sticker_name"), result.getBigDecimal("base_case"));
                changedNames.add(changedProduct);
            } else {
                Product unchanged = new Product(item.getQuantity(), item.getPackaging(),
                        item.getItemName());
                changedNames.add(unchanged);
                Audit.log(item.getItemName());
            }
        }
        return changedNames;
    }

}