package com.admin.urbanadmin.controller;

import com.admin.urbanadmin.entity.JsonRequest;
import com.admin.urbanadmin.entity.Order;
import com.admin.urbanadmin.entity.Provider;
import com.admin.urbanadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable("orderId") Integer orderId) {
        Order order = this.orderService.getOrder(orderId);
        if (order.getOrderId() != null && order.getProviderId() != null) {
            Provider provider = this.restTemplate.getForObject("http://provider-service/provider/" + order.getProviderId(), Provider.class);
            order.setProvider(provider);
        }
        return order;
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrders(@PathVariable("customerId") Integer customerId) {
        return this.orderService.getOrders(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/placeOrder")
    public Order createOrder(@RequestBody Order order){
        return this.orderService.saveOrder(order);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/update/{orderId}")
    public Order updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody JsonRequest payload){
        if (payload.getType().equals("status")) {
            return this.orderService.updateOrder(orderId, payload.getStatus());
        }
        if (payload.getType().equals("assignment")) {
            return this.orderService.updateOrder(orderId, payload.getStatus(), payload.getProvider());
        }
        return new Order();
    }
}
