package com.provider.urbanprovider.service;

import com.provider.urbanprovider.enity.Provider;

import java.util.List;

public interface ProviderService {

    public Provider getProvider(Integer id);
    public List<Provider> getProvidersByLocation(Integer locationCode);
    public Provider updateProvider(Integer id, String status);
}
