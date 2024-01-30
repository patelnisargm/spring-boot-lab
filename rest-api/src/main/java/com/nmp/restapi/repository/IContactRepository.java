package com.nmp.restapi.repository;

import com.nmp.restapi.dto.Contact;

import java.util.List;

public interface IContactRepository {
    List<Contact> getContacts();

    Contact getContact(int index);

    void saveContact(Contact contact);

    void updateContact(int index, Contact contact);

    void deleteContact(int index);
}
