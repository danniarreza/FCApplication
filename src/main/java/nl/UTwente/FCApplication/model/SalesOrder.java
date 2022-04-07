package nl.UTwente.FCApplication.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer salesOrderId;
    private String orderStatus;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private Client client;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<Goods> goodsList;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date proposedDeliveryDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date confirmedDeliveryDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate = new Date();

    public SalesOrder(){
        
    }

    public SalesOrder(Integer salesOrderId, String orderStatus, List<Goods> goodsList, Date proposedDeliveryDate, Date creationDate) {
        this.salesOrderId = salesOrderId;
        this.orderStatus = orderStatus;
        this.goodsList = goodsList;
        this.proposedDeliveryDate = proposedDeliveryDate;
        this.creationDate = creationDate;
    }

    public Integer getSalesOrderId() {
        return this.salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Goods> getGoodsList() {
        return this.goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Date getProposedDeliveryDate() {
        return this.proposedDeliveryDate;
    }

    public void setProposedDeliveryDate(Date proposedDeliveryDate) {
        this.proposedDeliveryDate = proposedDeliveryDate;
    }

    public Date getConfirmedDeliveryDate() {
        return this.confirmedDeliveryDate;
    }

    public void setConfirmedDeliveryDate(Date confirmedDeliveryDate) {
        this.confirmedDeliveryDate = confirmedDeliveryDate;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }    

    
}
