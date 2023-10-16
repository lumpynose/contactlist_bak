package org.objecteffects.contactlist.view.flash;

import java.io.IOException;
import java.io.Serializable;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.objecteffects.contactlist.view.ContactUtil;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactViewFlash implements Serializable {
    private static final long serialVersionUID = 4694088548123087426L;

//    private Contact contact;
//    private Long id;

    @Inject
    private transient Logger log;

    @Inject
    private ContactService contactService;
    @Inject
    private ContactUtil contactUtil;

    @PostConstruct
    public void init() {
        // empty
    }

//    public Long getId() {
//        this.log.debug("getId: id: {}", this.id);
//
//        return this.id;
//    }

//    public void setId(final Long _id) {
//        this.log.debug("setId: id: {}", _id);
//
//        this.id = _id;
//    }

    public Contact getContact() throws IOException {
        final Flash flash = FacesContext.getCurrentInstance()
                .getExternalContext().getFlash();

        final Contact contact = (Contact) flash.get("contact");

        this.log.debug("getContact: contact: {}", contact);

        return contact;
    }

    public String deleteContact(final Contact _contact) {
        this.log.debug("deleteContact: contact: {}", _contact);

        final Long _id = _contact.getId();

        this.contactService.deleteContact(_id);

        this.contactUtil.addMessage(this.contactService.getContact(_id),
                "deleted");

        return "contactlist.xhtml?faces-redirect=true";
    }
}
