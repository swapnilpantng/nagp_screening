package com.swapnilpant.urbancustomer.controller;

import com.swapnilpant.urbancustomer.entity.Customer;
import com.swapnilpant.urbancustomer.service.CustomerService;
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
    public Customer getCustomer(@PathVariable("customerId") Long customerId) {
        Customer customer = this.customerService.getCustomer(customerId);
        //http://localhost:9002/contact/customer/3
        List contacts = this.restTemplate.getForObject("http://contact-service/contact/customer/" + customer.getCustomerId(),List.class);
        customer.setContacts(contacts);
        return customer;
    }
}
