package nl.UTwente.FCApplication.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.UTwente.FCApplication.model.Client;
import nl.UTwente.FCApplication.model.Goods;
import nl.UTwente.FCApplication.model.Product;
import nl.UTwente.FCApplication.model.SalesOrder;
import nl.UTwente.FCApplication.repository.ClientRepository;
import nl.UTwente.FCApplication.repository.GoodsRepository;
import nl.UTwente.FCApplication.repository.ProductRepository;
import nl.UTwente.FCApplication.repository.SalesOrderRepository;
import nl.UTwente.FCApplication.service.SalesOrderService;

@RestController
@Tag(name = "Sales Order API", description = "This is the Sales Order REST API")
public class SalesOrderController {

    @Autowired
    SalesOrderService salesOrderService;
    @Autowired
    SalesOrderRepository salesOrderRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GoodsRepository goodsRepository;

    @PostMapping("/salesOrder")
    public SalesOrder createSalesOrder(@RequestBody SalesOrder salesOrder) {

        // get client from database
        Client client = clientRepository.findById(salesOrder.getClient().getBranchId()).orElse(null);

        // get products from database and save the goods to the database
        List<Goods> goodsList = salesOrder.getGoodsList();
        List<Goods> updatedGoodsList = new ArrayList<>();

        for (Goods goods : goodsList) {
            int productId = goods.getProduct().getProductId();
            Product existingProduct = productRepository.findById(productId).orElse(null);
            goods.setProduct(existingProduct);

            goods = goodsRepository.save(goods);
            updatedGoodsList.add(goods);
        }

        salesOrder.setGoodsList(updatedGoodsList);

        // save sales order to database
        salesOrder.setClient(client);
        salesOrderRepository.save(salesOrder);

        // send sales order to SMA
        salesOrderService.createSalesOrder(salesOrder);

        return salesOrder;
    }

    @GetMapping("/salesOrder")
    public List<SalesOrder> getAllSalesOrder() {

        // get sales orders from database
        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();

        return salesOrderList;
    }

    @GetMapping("/salesOrder/{id}")
    public SalesOrder getSalesOrderById(@PathVariable int id) {

        // get sales orders from database
        SalesOrder salesOrder = salesOrderRepository.findById(id).orElse(null);

        return salesOrder;
    }

    @PatchMapping("/salesOrder/{id}/confirmedDeliveryDate")
    public SalesOrder patchSalesOrderConfirmedDeliveryDate(@PathVariable int id,
            @RequestBody Map<String, Date> confirmedDeliveryDate) {

        // retrieve the sales order based on its id
        SalesOrder existingSalesOrder = salesOrderRepository.findById(id).orElse(null);

        // update its confirmed delivery date
        existingSalesOrder.setConfirmedDeliveryDate(confirmedDeliveryDate.get("confirmedDeliveryDate"));

        // update sales order in database
        salesOrderRepository.save(existingSalesOrder);

        return existingSalesOrder;
    }

    @PatchMapping("/salesOrder/{id}/status")
    public SalesOrder patchSalesOrderStatus(@PathVariable int id,
            @RequestBody Map<String, String> salesOrderStatus) {

        // retrieve the sales order based on its id
        SalesOrder existingSalesOrder = salesOrderRepository.findById(id).orElse(null);
    
        // update its confirmed delivery date
        existingSalesOrder.setOrderStatus(salesOrderStatus.get("orderStatus"));

        // update sales order in database
        salesOrderRepository.save(existingSalesOrder);

        return existingSalesOrder;
    }

}