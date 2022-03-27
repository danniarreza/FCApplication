package nl.UTwente.FCApplication.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import nl.UTwente.FCApplication.model.SalesOrder;

@Service
public class SalesOrderRepository {

    private static final List<SalesOrder> salesOrderList = new ArrayList<>();
    private static int idCounter = 1;

    public SalesOrder createSalesOrder(SalesOrder salesOrder){
        salesOrder.setOrderId(idCounter++);
        salesOrderList.add(salesOrder);
        return salesOrder;
    }

    public SalesOrder updateSalesOrder(SalesOrder salesOrder){
        salesOrderList.add(salesOrder);
        return salesOrder;
    }

    public SalesOrder updateSalesOrderConfirmedDate(int salesOrderId, Map<String, Date> confirmedDeliveryDate){

        // retrieve the sales order based on its id 
        SalesOrder salesOrder = getSalesOrder(salesOrderId);

        // update its confirmed delivery date
        salesOrder.setConfirmedDeliveryDate(confirmedDeliveryDate.get("confirmedDeliveryDate"));

        // get the index of that sales order in the list
        int index = salesOrderList.indexOf(salesOrder);

        // update the list with the updated sales order
        salesOrderList.set(index, salesOrder);

        return salesOrder;

    }

    public SalesOrder getSalesOrder(int id){

        for (SalesOrder salesOrder : salesOrderList) {
            if(salesOrder.getOrderId() == id){
                return salesOrder;
            }
        }

        return null ;
    }

    public int deleteSalesOrder(int id){
        salesOrderList.remove(id);
        return id;
    }

    public List<SalesOrder> getSalesOrderAll(){
        return salesOrderList;
    }
}
