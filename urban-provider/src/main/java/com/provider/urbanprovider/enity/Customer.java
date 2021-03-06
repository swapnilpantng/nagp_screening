package com.provider.urbanprovider.enity;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Integer customerId;
    private String name;
    private String phone;
    private String email;
    private Integer locationCode;

    public void setlocationCode(Integer locationCode) {
        this.locationCode = locationCode;
    }

    List<Order> orders = new ArrayList<>();

    public Customer(Integer customerId, String name, String phone, String email,Integer locationCode, List<Order> orders) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.locationCode = locationCode;
        this.orders = orders;
    }

    public Customer(Integer customerId, String name, String phone, String email,Integer locationCode) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.locationCode = locationCode;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getlocationCode() {
        return locationCode;
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
