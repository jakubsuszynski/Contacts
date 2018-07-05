package jsuszynski.contacts.repository;

import jsuszynski.contacts.domain.Contact;

import java.util.List;

public interface ContactsRepository {
    void persist(Contact contact);

    List<Contact> getAllContacts();

    void removeContact(Contact contact);

    void removeContactById(Long id);

    Contact findByEmail(String email);

    Contact findById(Long id);

}
