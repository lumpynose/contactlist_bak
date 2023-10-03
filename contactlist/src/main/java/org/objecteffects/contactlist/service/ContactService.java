package org.objecteffects.contactlist.service;

import java.io.Serializable;
import java.util.List;

import org.objecteffects.contactlist.model.Contact;
import org.slf4j.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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

//    public List<Contact> getContacts() {
//        this.log.debug("get contacts");
//
//        return this.entityManager.createQuery("select c from Contact c",
//                Contact.class).getResultList();
//    }

    public List<Contact> getContacts() {
        final CriteriaQuery<Contact> cq =
                this.entityManager.getCriteriaBuilder()
                        .createQuery(Contact.class);

        cq.select(cq.from(Contact.class));

        return this.entityManager.createQuery(cq).getResultList();
    }

    public Contact getContact(final Long id) {
        this.log.debug("get contact, {}", id);

        final CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        final CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);

        final Root<Contact> contact = cq.from(Contact.class);

        cq.select(contact);
        cq.distinct(true);
        cq.where(cb.equal(contact.get("id"), id));

        return this.entityManager.createQuery(cq).getSingleResult();
    }

    public void deleteContact(final Long id) {
        this.log.debug("delete contact, {}", id);

        final CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        // create delete
        final CriteriaDelete<Contact> cq =
                cb.createCriteriaDelete(Contact.class);

        // set the root class
        final Root<Contact> contact = cq.from(Contact.class);

        // set where clause
        cq.where(cb.equal(contact.get("id"), id));

        // perform update
        final int deletes = this.entityManager.createQuery(cq).executeUpdate();

        this.log.debug("deleted count: {}", Long.valueOf(deletes));
//        }
    }
}
