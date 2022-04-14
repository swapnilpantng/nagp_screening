package com.provider.urbanprovider.enity;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Integer customerId;
    private String name;
    private String phone;
    private String email;
    private Integer currentAdddressId;
    private Address currentAdddress;

    public void setCurrentAdddressId(Integer currentAdddressId) {
        this.currentAdddressId = currentAdddressId;
    }

    List<Address> addresses = new ArrayList<>();
    List<Order> orders = new ArrayList<>();

    public Customer(Integer customerId, String name, String phone, String email,Integer currentAdddressId, List<Address> addresses, List<Order> orders) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.currentAdddressId = currentAdddressId;
        this.addresses = addresses;
        this.orders = orders;
    }

    public Customer(Integer customerId, String name, String phone, String email,Integer currentAdddressId) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.currentAdddressId = currentAdddressId;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getCurrentAdddressId() {
        return currentAdddressId;
    }

    public Address getCurrentAdddress() {
        return currentAdddress;
    }

    public void setCurrentAdddress(Address currentAdddress) {
        this.currentAdddress = currentAdddress;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
