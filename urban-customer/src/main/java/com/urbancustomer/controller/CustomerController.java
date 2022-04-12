package com.urbancustomer.controller;

import com.urbancustomer.entity.Customer;
import com.urbancustomer.entity.Order;
import com.urbancustomer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) {
        Customer customer = this.customerService.getCustomer(customerId);
        if(customer.getCustomerId() != null){
            List<Order> orders = this.restTemplate.getForObject("http://admin-service/order/customer/" + customer.getCustomerId(),List.class);
            customer.setOrders(orders);
            customer.setAddresses(this.customerService.getCustomerAddress(customerId));
        }
        return customer;
    }
}