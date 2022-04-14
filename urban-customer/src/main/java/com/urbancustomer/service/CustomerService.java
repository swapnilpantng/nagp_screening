package com.urbancustomer.service;

import com.urbancustomer.entity.Address;
import com.urbancustomer.entity.Customer;

import java.util.List;

public interface CustomerService {

    public Customer getCustomer(Integer id);
    public List<Address> getCustomerAddress(Integer id);
    public Address getAddress(Integer id);
}
