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

    @RequestMapping(method = RequestMethod.PUT, value = "/isAccepted")
    public ProviderResponse acceptDenyProviderStatus(@RequestBody JsonRequest request){
        if (request.getRequest().equals("accept")){
            this.providerService.updateProvider(request.getProviderId(), "not available");
            Order orderUpdated = this.providerService.updateOrder(request.getOrderId(), request.getProviderId());
            if (orderUpdated.getOrderId() == null){
                return null;
            }
            ProviderResponse response =  new ProviderResponse();
            response.setEmail(orderUpdated.getCustomerEmail());
            response.setName(orderUpdated.getCustomerName());
            response.setPhone(orderUpdated.getCustomerPhone());
            response.setJobDescription(orderUpdated.getJobDescription());
            response.setScheduledDate(orderUpdated.getScheduledDate());
            response.setCurrentAdddress(orderUpdated.getCustomerAdddress());
            return response;
        }
        if (request.getRequest().equals("deny")){
            this.providerService.updateProvider(request.getProviderId(), "available");
            return null;
        }
        return null;
    }
}
