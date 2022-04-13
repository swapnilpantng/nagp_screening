package com.provider.urbanprovider.controller;

import com.provider.urbanprovider.enity.Provider;
import com.provider.urbanprovider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

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
}
