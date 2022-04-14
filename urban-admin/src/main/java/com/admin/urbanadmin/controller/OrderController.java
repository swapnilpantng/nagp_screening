package com.admin.urbanadmin.controller;

import com.admin.urbanadmin.config.MQConfig;
import com.admin.urbanadmin.entity.CustomMessage;
import com.admin.urbanadmin.entity.JsonRequest;
import com.admin.urbanadmin.entity.Order;
import com.admin.urbanadmin.entity.Provider;
import com.admin.urbanadmin.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable("orderId") Integer orderId) {
        Order order = this.orderService.getOrder(orderId);
        if (order.getOrderId() != null && order.getProviderId() != null) {
            Provider provider = this.restTemplate.getForObject("http://provider-service/provider/" + order.getProviderId(), Provider.class);
            order.setProvider(provider);
        }
        return order;
    }

    @GetMapping("/order/customer/{customerId}")
    public List<Order> getOrders(@PathVariable("customerId") Integer customerId) {
        return this.orderService.getOrders(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/placeOrder")
    public Order createOrder(@RequestBody Order order){
        return this.orderService.saveOrder(order);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/order/update/{orderId}")
    public Order updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody JsonRequest payload){
        if (payload.getType().equals("status")) {
            return this.orderService.updateOrder(orderId, payload.getStatus());
        }
        if (payload.getType().equals("assignment")) {
            return this.orderService.updateOrder(orderId, payload.getStatus(), payload.getProvider());
        }
        return new Order();
    }

    @PostMapping("/requestProviders/{orderId}/{locationCode}")
    public String requestProviderForOrder(@PathVariable("orderId") Integer orderId,@PathVariable("locationCode") Integer locationCode) {
        if (locationCode != null) {
            ResponseEntity<List<Provider>> response = restTemplate.exchange("http://provider-service/provider/location/" + locationCode, HttpMethod.GET,null,new ParameterizedTypeReference<List<Provider>>(){});
            List<Provider> providers = response.getBody();
            for(Provider nb : providers) {
                CustomMessage message = new CustomMessage();
                message.setMessageId(UUID.randomUUID().toString());
                message.setMessageDate(new Date());
                message.setProviderId(nb.getProviderId());
                message.setMessage("Near by job is requested. Please accept or deny");
                template.convertAndSend(MQConfig.EXCHANGE,
                        MQConfig.ROUTING_KEY,message);
            }
            return "Request to provider are send";
        }
        return "No provider available currently";
    }
}
