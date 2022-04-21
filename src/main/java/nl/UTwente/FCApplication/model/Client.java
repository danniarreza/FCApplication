package nl.UTwente.FCApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branchId;

    private String companyName;
    private String branchAddress;

    public Integer getBranchId() {
        return this.branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBranchAddress() {
        return this.branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    // @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private List<SalesOrder> SalesOrder;

    public Client(){}

    public Client(Integer branchId, String companyName, String branchAddress) {
        this.companyName = companyName;
        this.branchId = branchId;
        this.branchAddress = branchAddress;
    }



}