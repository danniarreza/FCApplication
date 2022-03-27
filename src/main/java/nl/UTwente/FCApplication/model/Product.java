package nl.UTwente.FCApplication.model;

public class Product {

    private int productId;
    private String productName;
    private String productUnit;

    public Product(int productId, String productName, String productUnit) {
        this.productId = productId;
        this.productName = productName;
        this.productUnit = productUnit;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }
}