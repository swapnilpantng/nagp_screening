package com.urbancustomer.service;

import com.urbancustomer.entity.Address;
import org.springframework.stereotype.Service;

import com.urbancustomer.entity.Customer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    //fake customer
    List<Customer> list = new ArrayList<Customer>(Arrays.asList(
            new Customer(1,"Swapnil Pant","9022121212", "swapnil@gmail.com", 1),
            new Customer(2,"John snow","80000001222","john@snow.com", 3),
            new Customer(3,"Zoe milton","70000121221","joe@gmail.com", 5)
    ));

    //fake customer address
    List<Address> addressList= List.of(
            new Address(1,"home",1,"Y21, Baker street, Manipur, India, 901123"),
            new Address(2,"office",1,"Z001 6th floor, Rampur, Manipur, India, 901103"),
            new Address(3,"home1",2,"Shyam nagar, dalal street, mumbai, India, 800023"),
            new Address(4,"home",2,"22,block 12, kanpur, london, England, 111001"),
            new Address(5,"home2",3,"OLP-20002, st pitt, dongra, Ireland, 300021")
    );

    @Override
    public Customer getCustomer(Integer id) {
        return this.list.stream().filter(user->user.getCustomerId().equals(id)).findAny().orElse(new Customer());
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Customer last = list.get(list.size() - 1);
        customer.setCustomerId(last.getCustomerId() + 1);
        if (this.list.add(customer)){
            return list.get(list.size() - 1);
        }
        else {
            return new Customer();
        }
    }

    @Override
    public Address getAddress(Integer id) {
        return this.addressList.stream().filter(address->address.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<Address> getCustomerAddress(Integer id) {
        return addressList.stream().filter(address -> address.getCustomerId().equals(id)).collect(Collectors.toList());
    }
}
