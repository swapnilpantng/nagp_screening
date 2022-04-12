package com.admin.urbanadmin.controller;

import com.admin.urbanadmin.entity.Order;
import com.admin.urbanadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        return this.orderService.getOrder(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrders(@PathVariable("customerId") Integer customerId) {
        return this.orderService.getOrders(customerId);
    }
}
