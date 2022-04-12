package com.urbancustomer.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Integer customerId;
    private String name;
    private String phone;
    private String email;


    List<Address> addresses = new ArrayList<>();

    public Customer(Integer customerId, String name, String phone, String email, List<Address> addresses) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.addresses = addresses;
    }

    public Customer(Integer customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
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

    public List<Address> getContacts() {
        return addresses;
    }

    public void setContacts(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
