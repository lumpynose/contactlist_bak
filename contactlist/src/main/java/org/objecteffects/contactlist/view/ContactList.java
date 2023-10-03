package org.objecteffects.contactlist.view;

import java.io.Serializable;
import java.util.List;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactList implements Serializable {
    private static final long serialVersionUID = -570500230181100578L;

    @Inject
    private transient Logger log;
    @Inject
    private ContactService contactService;

    @PostConstruct
    public void init() {
        this.log.warn("starting contactlist-0.0.2");
    }

    public List<Contact> getContacts() {
        this.log.debug("get contacts");

        return this.contactService.getContacts();
    }
}
