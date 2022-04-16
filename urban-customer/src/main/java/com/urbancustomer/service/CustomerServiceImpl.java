package com.urbancustomer.service;

import org.springframework.stereotype.Service;

import com.urbancustomer.entity.Customer;
import com.urbancustomer.entity.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    //fake customer
    List<Customer> list = new ArrayList<Customer>(Arrays.asList(
            new Customer(1,"Swapnil Pant","9022121212", "swapnil@gmail.com", 10, "address 1, city1,country1,210201"),
            new Customer(2,"John snow","80000001222","john@snow.com", 20, "address 2, city2,country2,12200"),
            new Customer(3,"Zoe milton","70000121221","joe@gmail.com", 30, "address 3, city3, country3,300012")
    ));

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Customer getCustomer(Integer id) {
        return this.list.stream().filter(user->user.getCustomerId().equals(id)).findAny().orElse(new Customer());
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Customer last = list.get(list.size() - 1);
        customer.setCustomerId(last.getCustomerId() + 1);
        if (this.list.add(customer)){
            return list.get(list.size() - 1);
        }
        else {
            return new Customer();
        }
    }

    @Override
    @CircuitBreaker(name = "getOrders", fallbackMethod = "getOrdersFallback")
    public List<Order> getOrders(Integer customerId){
        if(customerId != null){
            return this.restTemplate.getForObject("http://admin-service/order/customer/" + customerId,List.class);
        }
        return null;
    }

    public List<Order> getOrdersFallback(Exception e){
        return null;
    }
}
