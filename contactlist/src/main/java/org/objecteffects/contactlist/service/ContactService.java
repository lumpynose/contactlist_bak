package org.objecteffects.contactlist.service;

import java.io.Serializable;
import java.util.List;

import org.objecteffects.contactlist.model.Contact;
import org.slf4j.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ContactService implements Serializable {
    private static final long serialVersionUID = 1585658078828924772L;

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private transient Logger log;

    public void addContact(final Contact contact) {
        this.entityManager.persist(contact);
    }

    public List<Contact> getContacts() {
        this.log.debug("get contacts");

        return this.entityManager.createQuery("select c from Contact c",
                Contact.class).getResultList();
    }

    public Contact getContact(final Long id) {
        this.log.debug("get contact, {}", id);

        return this.entityManager.find(Contact.class, id);
    }

    public void deleteContact(final Long id) {
        this.log.debug("delete contact, {}", id);

        final Contact contact = this.entityManager.find(Contact.class, id);

        if (contact != null) {
            this.log.debug("removing {} {}", contact.getFirstName(),
                    contact.getLastName());
            this.entityManager.remove(contact);
        }
    }
}
