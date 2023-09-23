package com.objecteffects.contactlist.view;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;

import com.objecteffects.contactlist.model.Contact;
import com.objecteffects.contactlist.service.ContactService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactList implements Serializable {
    private static final long serialVersionUID = -570500230181100578L;

    private List<Contact> contacts;

    @Inject
    private ContactService contactService;
    @Inject
    private transient Logger log;

    @PostConstruct
    public void init() {
        this.contacts = this.contactService.list();
        this.log.warn("starting contactlist-0.0.2");
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public Contact getContact(final Long id) {
        this.log.info("get {}", id);
        return this.contactService.getContact(id);
    }

    public void submit() {
        // nothing
    }
}
