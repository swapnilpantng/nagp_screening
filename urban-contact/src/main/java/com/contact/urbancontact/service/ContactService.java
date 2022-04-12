package com.contact.urbancontact.service;

import com.contact.urbancontact.entity.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> getContactOfCustomer(Long customerId);
}
