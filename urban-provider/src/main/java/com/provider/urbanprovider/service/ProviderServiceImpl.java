package com.provider.urbanprovider.service;

import com.provider.urbanprovider.enity.Provider;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
