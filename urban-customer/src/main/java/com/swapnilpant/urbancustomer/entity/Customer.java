package com.swapnilpant.urbancustomer.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Long customerId;
    private String name;
    private String phone;

    List<Contact> contacts = new ArrayList<>();

    public Customer(Long customerId, String name, String phone, List<Contact> contacts) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.contacts = contacts;
    }

    public Customer(Long customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
