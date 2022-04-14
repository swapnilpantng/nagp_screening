package com.provider.urbanprovider.controller;

import com.provider.urbanprovider.enity.*;
import com.provider.urbanprovider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{providerId}")
    public Provider getProvider(@PathVariable("providerId") Integer providerId){
        return this.providerService.getProvider(providerId);
    }

    @GetMapping("/location/{locationCode}")
    public List<Provider> getProviderByLocation(@PathVariable("locationCode") Integer locationCode){
        return this.providerService.getProvidersByLocation(locationCode);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{providerId}")
    public Provider updateProviderStatus(@PathVariable("providerId") Integer providerId, @RequestBody Provider provider){
        return this.providerService.updateProvider(providerId, provider.getStatus());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/acceptDeny/{orderId}/{providerId}")
    public ProviderResponse acceptDenyProviderStatus(@PathVariable("orderId") Integer orderId, @PathVariable("providerId") Integer providerId, @RequestBody JsonRequest request){
        if (request.getRequest().equals("accept")){
            this.providerService.updateProvider(providerId, "not available");
            Order order = this.restTemplate.getForObject("http://admin-service/order/" + orderId, Order.class);
            Customer customerDetails = this.restTemplate.getForObject("http://customer-service/customer/forProvider/" + order.getCustomerId(), Customer.class);
            ProviderResponse response =  new ProviderResponse();
            response.setEmail(customerDetails.getEmail());
            response.setName(customerDetails.getName());
            response.setPhone(customerDetails.getPhone());
            response.setJobDescription(order.getJobDescription());
            response.setScheduledDate(order.getScheduledDate());
            response.setCurrentAdddress(customerDetails.getCurrentAdddress());
            Map<String, String> param = new HashMap<String, String>();
            param.put("type","assignment");
            param.put("status","assigned");
            param.put("provider",providerId.toString());
            HttpEntity<Order> responsePut = restTemplate.exchange("http://admin-service/order/update/" + order.getOrderId(), HttpMethod.PUT, null, Order.class, param);
            Order orderUpdated = responsePut.getBody();
            return response;
        }
        if (request.getRequest().equals("deny")){
            this.providerService.updateProvider(providerId, "available");
            return null;
        }
        return null;
    }
}
