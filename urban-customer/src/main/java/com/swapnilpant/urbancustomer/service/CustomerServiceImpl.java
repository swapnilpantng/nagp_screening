package com.swapnilpant.urbancustomer.service;

import com.swapnilpant.urbancustomer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    //fake customer
    List<Customer> list = List.of(
            new Customer(1L,"Swapnil Pant","9022121212"),
            new Customer(2L,"Swapnil Pant 2","9122121212"),
            new Customer(3L,"Swapnil Pant 2","9122121212")
    );

    @Override
    public Customer getCustomer(Long id) {
        return this.list.stream().filter(user->user.getCustomerId().equals(id)).findAny().orElse(null);
    }
}
