package org.objecteffects.contactlist.view;

import java.io.Serializable;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactAdd implements Serializable {
    private static final long serialVersionUID = -3294594409478388858L;

    @Inject
    private ContactService contactService;
    @Inject
    private transient Logger log;

    private final Contact contact = new Contact();

    @PostConstruct
    public void init() {
        this.log.debug("init");
    }

    public Contact getContact() {
        this.log.debug("getContact: hashcode: {}",
                Integer.valueOf(System.identityHashCode(this.contact)));

        return this.contact;
    }

    public String addContact() {
        this.log.debug("addContact: contact: {}", this.contact);

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .setKeepMessages(true);

        this.contactService.addContact(this.contact);

        return "contactlist?faces-redirect=true";
    }
}
