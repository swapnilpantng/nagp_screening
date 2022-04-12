package com.urbancustomer.entity;

public class Address {

    private Integer id;
    private String addressName;
    private Integer customerId;
    private String wholeAddress;

    public Address(Integer id, String addressName, Integer customerId, String wholeAddress) {
        this.id = id;
        this.addressName = addressName;
        this.customerId = customerId;
        this.wholeAddress = wholeAddress;
    }

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getWholeAddress() {
        return wholeAddress;
    }

    public void setWholeAddress(String wholeAddress) {
        this.wholeAddress = wholeAddress;
    }
}
