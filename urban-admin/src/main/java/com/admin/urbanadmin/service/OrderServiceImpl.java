package com.admin.urbanadmin.service;

import com.admin.urbanadmin.entity.Order;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    List<Order> list = new ArrayList<Order>(Arrays.asList(
            new Order(1,1,"created","electrician", parseDate("16-5-2022"),"fan fitting", parseDate("10-5-2022")),
            new Order(2,1,"assigned","Yoga",parseDate("16-5-2022"),"Yoga class", parseDate("10-5-2022")),
            new Order(3,2,"complete","AC",parseDate("16-5-2022"),"repair", parseDate("10-5-2022")),
            new Order(4,2,"accepted","AC",parseDate("16-5-2022"),"maintenance", parseDate("10-5-2022")),
            new Order(5,2,"created","TV",parseDate("16-5-2022"),"repair", parseDate("10-5-2022")),
            new Order(6,3,"complete","Salon",parseDate("16-5-2022"),"grooming and facial", parseDate("10-5-2022")),
            new Order(7,3,"assigned","Kitchen",parseDate("16-5-2022"),"stove repair", parseDate("10-5-2022"))
        ));

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
        Order last = list.get(list.size() - 1);
        order.setOrderId(last.getOrderId() + 1);
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
}
