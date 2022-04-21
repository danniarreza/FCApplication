package nl.UTwente.FCApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nl.UTwente.FCApplication.model.SalesOrder;

@Service
public class SalesOrderService {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public void createSalesOrder(SalesOrder salesOrder) {

        // String url = "http://localhost:8081/salesOrder";
        String url = "http://sma:8081/salesOrder";

        RestTemplate restTemplate = restTemplateBuilder.build();

        System.out.println("This is sales order id");
        System.out.println(salesOrder.getSalesOrderId());

        restTemplate.postForObject(url, salesOrder, SalesOrder.class);

    }

}
