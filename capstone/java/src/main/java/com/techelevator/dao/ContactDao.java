package com.techelevator.dao;

import com.techelevator.model.Contact;
import com.techelevator.model.ContactDTO;

import java.util.List;

public interface ContactDao {

    List<Contact> getAllContacts();

    List<Contact> getContactsByRole(String role);

    Long create(Contact contact);

    List<ContactDTO> getListOfContactDTO();
}
