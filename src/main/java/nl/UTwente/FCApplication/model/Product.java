package nl.UTwente.FCApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productUnit;

    // @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    // private Goods goods;

    public Product(){}

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


    // public Goods getGoods() {
    //     return this.goods;
    // }

    // public void setGoods(Goods goods) {
    //     this.goods = goods;
    // }

}