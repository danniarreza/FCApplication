package nl.UTwente.FCApplication.model;

public class Goods {

    private int goodsId;
    private Product product;
    private int amount;

    public Goods(){}

    public Goods(int goodsId, Product product, int amount){
        this.goodsId = goodsId;
        this.product = product;
        this.amount = amount;
    }

    public int getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    
}

