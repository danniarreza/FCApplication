package nl.UTwente.FCApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nl.UTwente.FCApplication.model.Client;
import nl.UTwente.FCApplication.model.Goods;
import nl.UTwente.FCApplication.model.Product;
import nl.UTwente.FCApplication.model.SalesOrder;
import nl.UTwente.FCApplication.repository.ClientRepository;
import nl.UTwente.FCApplication.repository.GoodsRepository;
import nl.UTwente.FCApplication.repository.ProductRepository;
import nl.UTwente.FCApplication.repository.SalesOrderRepository;
import nl.UTwente.FCApplication.service.SalesOrderService;

@Controller
public class IndexController {

    @Autowired ProductRepository productRepository;
    @Autowired SalesOrderRepository salesOrderRepository;
    @Autowired ClientRepository clientRepository;
    @Autowired GoodsRepository goodsRepository;
    @Autowired SalesOrderService salesOrderService;

    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("salesOrders", salesOrderRepository.findAll());

        return "index";
    }

    @GetMapping("/salesOrderForm")
    public String salesOrderForm(Model model){

        // get a list of clients for select
        List<Client> clients = clientRepository.findAll();

        // create a new salesorder instance
        SalesOrder newSalesOrder = new SalesOrder();

        model.addAttribute("salesOrder", newSalesOrder);
        model.addAttribute("clients", clients);

        return "salesorderform";
    }

    @PostMapping("/salesOrderSubmit")
    public String salesOrderSubmit(@ModelAttribute SalesOrder salesOrder, Model model){

        // update the sales order
        salesOrderRepository.save(salesOrder);

        // create a new goods instance
        Goods newGoods = new Goods();

        // get the product list for select
        List<Product> products = productRepository.findAll();

        model.addAttribute("salesOrder", salesOrder);
        model.addAttribute("goods", newGoods);
        model.addAttribute("products", products);

        return "salesordergoodsform";
    }

    @GetMapping("/salesOrderGoodsForm/{salesOrderId}")
    public String salesOrderGoodsForm(@PathVariable Integer salesOrderId , Model model){

        // get the sales order detail to be added its goods
        SalesOrder salesOrder = salesOrderRepository.findById(salesOrderId).get();

        // create a new goods instance
        Goods newGoods = new Goods();

        // get the product list
        List<Product> products = productRepository.findAll();

        model.addAttribute("salesOrder", salesOrder);
        model.addAttribute("goods", newGoods);
        model.addAttribute("products", products);

        return "salesordergoodsform";
    }

    @PostMapping("/salesOrderGoodsSubmit/{salesOrderId}")
    public String salesOrderGoodsSubmit(@ModelAttribute Goods goods, @PathVariable Integer salesOrderId, Model model){

        // save new goods to the databasee
        goods = goodsRepository.save(goods);

        // get existing salesorder by its id
        SalesOrder existingSalesOrder = salesOrderRepository.getById(salesOrderId);

        // get the goods list of that salesorder
        List<Goods> existingGoodsList = existingSalesOrder.getGoodsList();
        
        // add the new goods to the goods list and reattach the new list to the sales order 
        existingGoodsList.add(goods);
        existingSalesOrder.setGoodsList(existingGoodsList);

        // save the sales order
        salesOrderRepository.save(existingSalesOrder);

        return "redirect:/salesOrderGoodsForm/" + salesOrderId;
    }

    @GetMapping("/salesOrderCreateRequest/{salesOrderId}")
    public String salesOrderCreateRequest(@PathVariable Integer salesOrderId, Model model){

        // get salesorder by its id
        SalesOrder salesOrder = salesOrderRepository.findById(salesOrderId).orElse(null);

        salesOrder.setOrderStatus("On Negotiation");
        salesOrderRepository.save(salesOrder);

        salesOrderService.createSalesOrder(salesOrder);

        return "result";
    }
    
}
