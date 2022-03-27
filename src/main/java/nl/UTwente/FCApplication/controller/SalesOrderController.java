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
import nl.UTwente.FCApplication.model.SalesOrder;
import nl.UTwente.FCApplication.repository.ClientRepository;
import nl.UTwente.FCApplication.repository.SalesOrderRepository;
import nl.UTwente.FCApplication.service.SalesOrderService;

    @RestController
    @Tag(name="Sales Order API", description="This is the Sales Order REST API")
    public class SalesOrderController {

        @Autowired SalesOrderService salesOrderService;
        @Autowired SalesOrderRepository salesOrderRepository;
        @Autowired ClientRepository clientRepository;

        @PostMapping("/salesOrder")
        public SalesOrder createSalesOrder(@RequestBody SalesOrder salesOrder) {

            // get client from database
            Client client = clientRepository.getClient();

            // save sales order to database
            salesOrder.setClient(client);
            salesOrderRepository.createSalesOrder(salesOrder);

            // send sales order to SMA
            salesOrderService.createSalesOrder(salesOrder);

            return salesOrder;
        }

        @GetMapping("/salesOrder")
        public List<SalesOrder> getAllSalesOrder(){
            
            // get sales orders from database
            List<SalesOrder> salesOrderList = salesOrderRepository.getSalesOrderAll();

            return salesOrderList;
        }

        @PatchMapping("/salesOrder/{id}")
        public SalesOrder patchSalesOrder(@PathVariable int id, @RequestBody Map<String, Date> confirmedDeliveryDate){

            SalesOrder salesOrder = salesOrderRepository.updateSalesOrderConfirmedDate(id, confirmedDeliveryDate);

            return salesOrder;
        }

    }