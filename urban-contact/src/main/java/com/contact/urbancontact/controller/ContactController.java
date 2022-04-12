package com.contact.urbancontact.controller;

import com.contact.urbancontact.entity.Contact;
import com.contact.urbancontact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/customer/{customerId}")
    public List<Contact> getContacts(@PathVariable("customerId") Long customerId){
        return this.contactService.getContactOfCustomer(customerId);
    }
}
