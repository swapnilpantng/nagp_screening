package com.contact.urbancontact.service;

import com.contact.urbancontact.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImp implements ContactService{

    //fake list
    List<Contact> list= List.of(
      new Contact(1L,"swapnil@sadm.com","swapnil111",1L),
      new Contact(2L,"swapnil1@sadm.com","swapnil112",1L),
      new Contact(3L,"gfdhdfgfgfg@sadm.com","swapnil221",2L),
      new Contact(4L,"w312dsa@sadm.com","swapnil222",2L),
      new Contact(4L,"gfdgdf@sadm.com","swapnil331",3L)
    );
    @Override
    public List<Contact> getContactOfCustomer(Long customerId) {
        return list.stream().filter(contact -> contact.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }
}
