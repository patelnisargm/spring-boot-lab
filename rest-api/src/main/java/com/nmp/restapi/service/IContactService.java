package com.nmp.restapi.service;

import com.nmp.restapi.dto.Contact;

import java.util.List;

public interface IContactService {
    Contact getContact(int index);

    Contact getContactById(String id);

    List<Contact> getContacts();

    void saveContact(Contact contact);

    void updateContact(String id, Contact contact);

    void deleteContact(String id);
}
