package Model;

import java.math.BigDecimal;

public class OrderRecord {
    private final BigDecimal ONE_CASE = new BigDecimal("1.00");
    private BigDecimal quantity;
    private String originalMeasurement;
    private String cleanMeasurement;
    private String originalItemName;
    private String cleanItemName;
    private String shipName;
    private BigDecimal caseWeight;

    public OrderRecord(BigDecimal quantity, String originalMeasurement, String cleanMeasurement, String originalItemName, String cleanItemName, String shipName) {
        this.quantity = quantity;
        this.originalMeasurement = originalMeasurement;
        this.cleanMeasurement = cleanMeasurement;
        this.originalItemName = originalItemName;
        this.cleanItemName = cleanItemName;
        this.shipName = shipName;
    }

    public OrderRecord(BigDecimal quantity, String originalMeasurement, String cleanMeasurement, String originalItemName, String cleanItemName, String shipName, BigDecimal caseWeight) {
        this.quantity = quantity;
        this.originalMeasurement = originalMeasurement;
        this.cleanMeasurement = cleanMeasurement;
        this.originalItemName = originalItemName;
        this.cleanItemName = cleanItemName;
        this.shipName = shipName;
        this.caseWeight = caseWeight;
    }

    public BigDecimal getONE_CASE() {
        return ONE_CASE;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getOriginalMeasurement() {
        return originalMeasurement;
    }

    public void setOriginalMeasurement(String originalMeasurement) {
        this.originalMeasurement = originalMeasurement;
    }

    public String getCleanMeasurement() {
        return cleanMeasurement;
    }

    public void setCleanMeasurement(String cleanMeasurement) {
        this.cleanMeasurement = cleanMeasurement;
    }

    public String getOriginalItemName() {
        return originalItemName;
    }

    public void setOriginalItemName(String originalItemName) {
        this.originalItemName = originalItemName;
    }

    public String getCleanItemName() {
        return cleanItemName;
    }

    public void setCleanItemName(String cleanItemName) {
        this.cleanItemName = cleanItemName;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public BigDecimal getCaseWeight() {
        return caseWeight;
    }

    public void setCaseWeight(BigDecimal caseWeight) {
        this.caseWeight = caseWeight;
    }

    @Override
    public String toString() {
        return "OrderRecord{" +
                "quantity=" + quantity +
                ", originalMeasurement='" + originalMeasurement + '\'' +
                ", cleanMeasurement='" + cleanMeasurement + '\'' +
                ", originalItemName='" + originalItemName + '\'' +
                ", cleanItemName='" + cleanItemName + '\'' +
                ", shipName='" + shipName + '\'' +
                ", caseWeight=" + caseWeight +
                '}';
    }
}
