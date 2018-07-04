package jsuszynski.contacts.repository;

import jsuszynski.contacts.domain.Contact;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class ContactsRepository {
    @PersistenceContext(unitName = "contacts")
    private EntityManager entityManager;

    public void addContact(Contact contact) {
        entityManager.persist(contact);
    }

    public List<Contact> getAllContacts() {
        return entityManager.createQuery("FROM Contact").getResultList();
    }

    public void removeContact(Contact contact) {
        entityManager.remove(contact);
    }

    public void removeContactById(Long id) {
        entityManager.remove(findById(id));
    }

    public Contact findByEmail(String email) {
        try {
            return (Contact) entityManager.createQuery("FROM Contact u WHERE u.email=:email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    public Contact findById(Long id) {
        try {
            return (Contact) entityManager.createQuery("FROM Contact u WHERE u.id=:id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void editContact(Contact contact) {
        entityManager.merge(contact);
    }
}
