package com.admin.urbanadmin.service;

import com.admin.urbanadmin.entity.Order;

import java.util.List;

public interface OrderService {

    public Order getOrder(Integer orderId);
    public List<Order> getOrders(Integer customerId);
}
