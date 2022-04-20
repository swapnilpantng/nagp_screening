package com.provider.urbanprovider.service;

import com.provider.urbanprovider.enity.Order;
import com.provider.urbanprovider.enity.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {

    private List<Provider> list = List.of(
            new Provider(1,"Jagdish narayan","jagdish@gmail.com","electriciam",20,"available"),
            new Provider(2,"Neelam k","neelam@gmail.com","yoga",20,"available"),
            new Provider(3,"rahul m","rahul@gmail.com","hardware",30,"not available"),
            new Provider(4,"yogesh k","yogesh@gmail.com","hardware",20,"available"),
            new Provider(4,"yogesh k","yogesh@gmail.com","yoga",30,"available"),
            new Provider(4,"nima l","nima@gmail.com","salon",10,"available"),
            new Provider(4,"piyush","piyush@gmail.com","salon",20,"available"),
            new Provider(4,"lokesh","lokesh@gmail.com","salon",10,"available")
    );

    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public Provider getProvider(Integer id) {
        return this.list.stream().filter(provider -> provider.getProviderId().equals(id)).findAny().orElse(new Provider());
    }

    @Override
    public Provider updateProvider(Integer id, String status) {
        Provider currentProvider = this.list.stream().filter(provider->provider.getProviderId().equals(id)).findAny().orElse(null);
        currentProvider.setStatus(status);
        return currentProvider;
    }

    @Override
    public List<Provider> getProvidersByLocation(Integer locationCode) {
        return this.list.stream().
                filter(provider -> provider.getLocationCode().equals(locationCode) && provider.getStatus().equals("available"))
                .collect(Collectors.toList());
    }

    @Override
    @CircuitBreaker(name = "updateOrder", fallbackMethod = "getUpdateOrderFallback")
    public Order updateOrder(Integer orderId, Integer providerId){
        Map<String, String> param = new HashMap<String, String>();
        param.put("type","assignment");
        param.put("status","assigned");
        param.put("provider",providerId.toString());

        final HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(param);
        HttpEntity<Order> responsePut = restTemplate.exchange("http://admin-service/order/update/" + orderId, HttpMethod.PUT,  entity , Order.class);
        Order orderUpdated = responsePut.getBody();
        return orderUpdated;
    }

    public Order getUpdateOrderFallback(Exception e){
        return new Order();
    }
}
