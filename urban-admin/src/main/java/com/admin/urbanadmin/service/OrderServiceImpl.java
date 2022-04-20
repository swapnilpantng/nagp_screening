package com.admin.urbanadmin.service;

import com.admin.urbanadmin.entity.Customer;
import com.admin.urbanadmin.entity.Order;
import org.springframework.stereotype.Service;
import com.admin.urbanadmin.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpEntity;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private RestTemplate restTemplate;

    List<Order> list = new ArrayList<Order>();

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public Order getOrder(Integer orderId) {
        return this.list.stream().filter(order -> order.getOrderId().equals(orderId)).findAny().orElse(null);
    }

    @Override
    public List<Order> getOrders(Integer customerId) {
        return this.list.stream().filter(order -> order.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrdersList() {
        return this.list.stream().collect(Collectors.toList());
    }

    @Override
    public Order saveOrder(Order order) {
        Integer newId = 1;
        if(list.size() != 0) {
            Order last = list.get(list.size() - 1);
            newId = last.getOrderId() + 1;
        }
        order.setOrderId(newId);
        order.setCreatedDate(Date.from( Instant.now()));
        order.setUpdatedDate(Date.from( Instant.now()));
        if (this.list.add(order)){
            return list.get(list.size() - 1);
        }
        else {
            return new Order();
        }
    }

    @Override
    public Order updateOrder(Integer orderId, String orderStatus) {
        Order currentOrder = this.list.stream().filter(order->order.getOrderId().equals(orderId)).findAny().orElse(null);
        if (currentOrder.getOrderId() != null) {
            currentOrder.setOrderStatus(orderStatus);
        }
        return currentOrder;
    }

    @Override
    public Order updateOrder(Integer orderId, String orderStatus, Integer providerId) {
        Order currentOrder = this.list.stream().filter(order->order.getOrderId().equals(orderId)).findAny().orElse(null);
        if (currentOrder.getOrderId() != null) {
            currentOrder.setOrderStatus(orderStatus);
            currentOrder.setProviderId(providerId);
        }
        return currentOrder;
    }

    @Override
    @CircuitBreaker(name = "getProvider", fallbackMethod = "getProviderFallback")
    public Provider getProvider(Integer providerId){
        if (providerId != null){
           return this.restTemplate.getForObject("http://provider-service/provider/" + providerId, Provider.class);
        }
        return null;
    }

    @Override
    @CircuitBreaker(name = "getProviderByLocationCode", fallbackMethod = "getProviderByLocationCodeFallback")
    @Retry(name = "getProviderByLocationCode")
    public List<Provider> getProviderByLocationCode(Integer locationCode){
        if (locationCode != null){
            ResponseEntity<List<Provider>> response = this.restTemplate.exchange("http://provider-service/provider/location/" + locationCode, HttpMethod.GET,null,new ParameterizedTypeReference<List<Provider>>(){});
            return response.getBody();
        }
        return null;
    }

    @Override
    @CircuitBreaker(name = "addCustomer", fallbackMethod = "getAddCustomer")
    public Customer addCustomer(HttpEntity<Map<String, String>> entity){
        HttpEntity<Customer> responsePost = this.restTemplate.exchange("http://customer-service/customer/add", HttpMethod.POST,entity, Customer.class);
        return responsePost.getBody();
    }

    public Provider getProviderFallback(Exception e){
        return new Provider();
    }

    public Customer getAddCustomer(Exception e){
        return new Customer();
    }

    public List<Provider> getProviderByLocationCodeFallback(Exception e){
        return null;
    }
}
