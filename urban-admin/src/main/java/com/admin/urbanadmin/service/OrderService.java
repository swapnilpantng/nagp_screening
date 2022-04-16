package com.admin.urbanadmin.service;

import com.admin.urbanadmin.entity.Order;
import com.admin.urbanadmin.entity.Provider;

import java.util.List;

public interface OrderService {

    Order getOrder(Integer orderId);
    Order saveOrder(Order order);
    List<Order> getOrders(Integer customerId);
    List<Order> getOrdersList();
    Order updateOrder(Integer orderId, String orderStatus);
    Order updateOrder(Integer orderId, String orderStatus, Integer providerId);
    Provider getProvider(Integer providerId);
    List<Provider> getProviderByLocationCode(Integer locationCode);
}
