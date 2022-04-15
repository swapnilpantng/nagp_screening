package com.admin.urbanadmin.service;

import com.admin.urbanadmin.entity.Order;

import java.util.List;

public interface OrderService {

    Order getOrder(Integer orderId);
    Order saveOrder(Order order);
    List<Order> getOrders(Integer customerId);
    List<Order> getOrdersList();
    Order updateOrder(Integer orderId, String orderStatus);
    Order updateOrder(Integer orderId, String orderStatus, Integer providerId);
}
