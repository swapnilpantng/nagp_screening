package com.urbancustomer.service;

import com.urbancustomer.entity.Customer;
import com.urbancustomer.entity.Order;

import java.util.List;

public interface CustomerService {

    public Customer getCustomer(Integer id);
    public Customer addCustomer(Customer customer);
    public List<Order> getOrders(Integer customerId);
}
