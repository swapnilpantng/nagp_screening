package com.swapnilpant.urbancustomer.entity;

public class Contact {

    private Long id;
    private String email;
    private String contactName;
    private Long customerId;

    public Contact(Long id, String email, String contactName, Long customerId) {
        this.id = id;
        this.email = email;
        this.contactName = contactName;
        this.customerId = customerId;
    }

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
