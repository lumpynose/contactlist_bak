package org.objecteffects.contactlist.view.param;

import java.io.Serializable;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.objecteffects.contactlist.view.ContactUtil;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactViewParam implements Serializable {
    private static final long serialVersionUID = 4694088548123087426L;

    @Inject
    private transient Logger log;

    @Inject
    private ContactService contactService;
    @Inject
    private ContactUtil contactUtil;

    @Inject
    @ManagedProperty(value = "#{param.paramId}")
    private String paramId;

    @PostConstruct
    public void init() {
        this.log.debug("init: paramId: {}", this.paramId);
    }

    public Contact getContact() {
        this.log.debug("getContact: paramId: {}", this.paramId);

        return this.contactService.getContact(Long.valueOf(this.paramId));
    }

    public String deleteContact() {
        this.log.debug("deleteContact(): paramId: {}", this.paramId);

        final Long id = Long.valueOf(this.paramId);

        final Contact contact = new Contact(this.contactService.getContact(id));

        this.contactService.deleteContact(id);

        this.contactUtil.addMessage(contact, "deleted");

        return "/param/contactlistparam.xhtml?faces-redirect=true";
    }

    public String deleteContact(final Long id) {
        this.log.debug("deleteContact(id): paramId: {}, id: {}", this.paramId,
                id);

        final Contact contact =
                new Contact(this.contactService.getContact(id));

        this.contactService.deleteContact(id);

        this.contactUtil.addMessage(contact, "deleted");

        return "/param/contactlistparam.xhtml?faces-redirect=true";
    }

    public String deleteContact(final Contact contact) {
        this.log.debug("deleteContact(contact): contact: {}", contact);

        final Contact contactCopy = new Contact(contact);

        this.contactService.deleteContact(contact);

        this.contactUtil.addMessage(contactCopy, "deleted");

        return "/param/contactlistparam.xhtml?faces-redirect=true";
    }
}
