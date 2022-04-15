package com.urbancustomer.service;

import com.urbancustomer.entity.Customer;

public interface CustomerService {

    public Customer getCustomer(Integer id);
    public Customer addCustomer(Customer customer);
}
