package com.admin.urbanadmin.controller;

import com.admin.urbanadmin.config.MQConfig;
import com.admin.urbanadmin.entity.*;
import com.admin.urbanadmin.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate templateProvider;

    @Autowired
    private RabbitTemplate templateCustomer;

    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable("orderId") Integer orderId) {
        Order order = this.orderService.getOrder(orderId);
        if (order.getOrderId() != null && order.getProviderId() != null) {
            order.setProvider(this.orderService.getProvider(order.getProviderId()));
        }
        return order;
    }

    @GetMapping("/order/orders")
    public List<Order> getOrders() {
        return this.orderService.getOrdersList();
    }

    @GetMapping("/order/customer/{customerId}")
    public List<Order> getOrders(@PathVariable("customerId") Integer customerId) {
        return this.orderService.getOrders(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/placeOrder")
    public Order createOrder(@RequestBody Order order){
        if (order.getCustomerId() == null){
            Map<String, String> param = new HashMap<String, String>();
            param.put("name",order.getCustomerName());
            param.put("phone",order.getCustomerPhone());
            param.put("email",order.getCustomerEmail());
            param.put("currentAdddress",order.getCustomerAdddress());
            final HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(param);
            HttpEntity<Customer> responsePost = this.restTemplate.exchange("http://customer-service/customer/add", HttpMethod.POST,entity, Customer.class);
            Customer newCustomer = responsePost.getBody();
            order.setCustomerId(newCustomer.getCustomerId());
        }
        return this.orderService.saveOrder(order);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/order/update/{orderId}")
    public Order updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody JsonRequest payload){
        if (payload.getType().equals("status")) {
            return this.orderService.updateOrder(orderId, payload.getStatus());
        }
        if (payload.getType().equals("assignment")) {
            Order order = this.orderService.updateOrder(orderId, payload.getStatus(), payload.getProvider());
            Provider provider = this.restTemplate.getForObject("http://provider-service/provider/" + order.getProviderId(), Provider.class);
            CustomerMessage message = new CustomerMessage();
            message.setMessageId(UUID.randomUUID().toString());
            message.setBookingUpdate("You order is assigned to expert");
            message.setProviderEmail(provider.getEmail());
            message.setProviderName(provider.getName());
            message.setProviderLocationCode(provider.getLocationCode());
            message.setProviderType(provider.getType());
            message.setMessageDate(new Date());
            templateCustomer.convertAndSend(MQConfig.EXCHANGE,
                    MQConfig.QUEUE_CUSTOMER,message);
            return order;
        }
        return new Order();
    }

    @PostMapping("/requestProviders/{orderId}/{locationCode}")
    public String requestProviderForOrder(@PathVariable("orderId") Integer orderId,@PathVariable("locationCode") Integer locationCode) {
        if (locationCode != null) {
            Order order = this.orderService.getOrder(orderId);
            List<Provider> providers = this.orderService.getProviderByLocationCode(locationCode);
            if (providers == null) {
                return "Can't get providers, Please again later";
            }
            for(Provider nb : providers) {
                ProviderMessage message = new ProviderMessage();
                message.setMessageId(UUID.randomUUID().toString());
                message.setMessageDate(new Date());
                message.setProviderId(nb.getProviderId());
                message.setOrderId(orderId);
                message.setScheduledDate(order.getScheduledDate());
                message.setMessage("Near by job is requested. Please accept or deny");
                templateProvider.convertAndSend(MQConfig.EXCHANGE,
                        MQConfig.QUEUE_PROVIDER,message);
            }
            return "Request to provider are send";
        }
        return "No provider available currently";
    }
}
