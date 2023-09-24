package com.objecteffects.contactlist.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;

import com.objecteffects.contactlist.model.Contact;
import com.objecteffects.contactlist.service.ContactService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class ContactView implements Serializable {
    private static final long serialVersionUID = 4694088548123087426L;

    private Contact contact;
    private Long id;

    @Inject
    private ContactService contactService;
    @Inject
    private ExternalContext externalContext;
    @Inject
    private transient Logger log;

    @PostConstruct
    public void init() {
        this.log.warn("starting contactlist-0.0.2");
    }

    public Long getId() {
        this.log.info("get id, {}", this.id);

        return this.id;
    }

    public void setId(final Long _id) {
        this.log.info("set id, {}", _id);

        this.id = _id;
    }

    public Contact getContact() {
        this.log.info("get contact {}", this.id);

        return this.contact;
    }

    public void setContact(final Contact _contact) {
        this.log.info("set contact, {}", _contact.getId());
        this.contact = _contact;
    }

    public List<Contact> getContacts() {
        return this.contactService.getContacts();
    }

    public void deleteContact(final Long _id) throws IOException {
        this.log.info("delete contact, {}", _id);

        final Contact contact = this.contactService.getContact(_id);
        final String firstName = contact.getFirstName();
        final String lastName = contact.getLastName();

        this.contactService.deleteContact(_id);

        final StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);

        messageInfo(sb.toString());

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .setKeepMessages(true);
        this.externalContext.redirect("contactlist.xhtml");
    }

    public void viewContact(final Long _id) throws IOException {
        this.log.info("view contact, {}", _id);

        this.id = _id;
        this.contact = this.contactService.getContact(_id);

        this.externalContext.redirect("contact.xhtml");
    }

    private void messageInfo(final String message) {
        final FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
}
