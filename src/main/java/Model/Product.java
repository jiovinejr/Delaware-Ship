package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product implements Comparable<Product>{

    private final BigDecimal ONE_CASE = new BigDecimal("1.00");


    private BigDecimal quantity;
    private String packaging;
    private String itemName;
    private BigDecimal baseCase;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getBaseCase() {
        return baseCase;
    }

    public void setBaseCase(BigDecimal baseCase) {
        this.baseCase = baseCase;
    }

    public Product() {
    }

    public Product(BigDecimal quantity, String packaging, String itemName) {
        this.quantity = quantity;
        this.packaging = packaging;
        this.itemName = itemName;
    }

    public Product(BigDecimal quantity, String packaging, String itemName, BigDecimal baseCase) {
        this.quantity = quantity;
        this.packaging = packaging;
        this.itemName = itemName;
        this.baseCase = baseCase;
    }

    public List<Sticker> makeStickers(String shipName) {
//        System.out.println(itemName + " " + baseCase + " " + packaging);
        List<Sticker> result = new ArrayList<>();
        if (packaging.equals("BX") || packaging.equals("BG") || packaging.equals("CS")) {
            while (quantity.compareTo(ONE_CASE) > 0) {
                result.add(new Sticker(shipName, ONE_CASE, packaging, itemName));
                quantity = quantity.subtract(ONE_CASE);
            }
            result.add(new Sticker(shipName, quantity, packaging, itemName));
        } else if (packaging.equals("LB") || packaging.equals("PC") || packaging.equals("PTS") || packaging.equals("BC") || packaging.equals("EA")) {
            if (baseCase != null) {
                while (quantity.compareTo(baseCase) > 0) {
                    result.add(new Sticker(shipName, baseCase, packaging, itemName));
                    quantity = quantity.subtract(baseCase);
                }
            }
                result.add(new Sticker(shipName, quantity, packaging, itemName));

        } else {
            result.add(new Sticker(shipName, quantity, packaging, itemName));
        }
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "quantity=" + quantity +
                ", packaging='" + packaging + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Product product) {
        return this.getItemName().compareTo(product.getItemName());
    }
}
