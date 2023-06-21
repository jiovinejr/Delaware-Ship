package Model;


import java.math.BigDecimal;

public class Sticker {

    private String DELAWARE_SHIP = "Delaware Ship Supply Co.";
    private String shipName;
    private BigDecimal quantity;
    private String packaging;
    private String itemName;

    public Sticker(BigDecimal quantity, String packaging, String itemName) {
        this.quantity = quantity;
        this.packaging = packaging;
        this.itemName = itemName;
    }

    public Sticker(String shipName, BigDecimal quantity, String packaging, String itemName) {
        this.shipName = shipName;
        this.quantity = quantity;
        this.packaging = packaging;
        this.itemName = itemName;
    }

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

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String packagingCleanUp(String packaging) {
        String result = "";
        if (packaging.equals("BX")) {
            result = "Box";
        } else if (packaging.equals("LB")) {
            result = "Pound";
        } else if (packaging.equals("BG")) {
            result = "Bag";
        } else if (packaging.equals("BC")) {
            result = "Bunch";
        } else if (packaging.equals("CS")) {
            result = "Case";
        } else if (packaging.equals("PC")) {
            result = "Pieces";
        } else if (packaging.equals("PTS")) {
            result = "Pints";
        } else if (packaging.equals("EA")) {
            result = "Each";
        } else {
            result = packaging;
        }
        return result;
    }

    @Override
    public String toString() {
        String qty = quantity.toString();
        String pck = packagingCleanUp(packaging);
        if(itemName.equals("Watermelons")) {
            qty = "";
        }
        return DELAWARE_SHIP + "," + shipName + "," + qty + "," + pck + "," + itemName;
    }
}
