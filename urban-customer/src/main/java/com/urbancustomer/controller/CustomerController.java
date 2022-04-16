package com.urbancustomer.controller;

import com.urbancustomer.entity.Customer;
import com.urbancustomer.service.CustomerService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) {
        Customer customer = this.customerService.getCustomer(customerId);
        customer.setOrders(this.customerService.getOrders(customerId));
        return customer;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public Customer addCustomer(@RequestBody Customer customer){
        return this.customerService.addCustomer(customer);
    }
}
