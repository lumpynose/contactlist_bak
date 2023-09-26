package org.objecteffects.contactlist.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.slf4j.Logger;

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
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final String implementationTitle =
                facesContext.getClass().getPackage().getImplementationTitle();
        this.log.warn("starting contactlist-0.0.2");
        this.log.warn(implementationTitle);
    }

    public Long getId() {
        this.log.debug("get id, {}", this.id);

        return this.id;
    }

    public void setId(final Long _id) {
        this.log.debug("set id, {}", _id);

        this.id = _id;
    }

    public Contact getContact() {
        this.log.debug("get contact {}", this.id);

        return this.contact;
    }

    public List<Contact> getContacts() {
        this.log.debug("get contacts", this.id);

        return this.contactService.getContacts();
    }

    public void deleteContact(final Long id) throws IOException {
        this.log.debug("delete contact, {}", id);

        addMessage(deleteMessage(id));

        this.contactService.deleteContact(id);

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .setKeepMessages(true);

        this.externalContext.redirect("contactlist.xhtml");
    }

    public void viewContact(final Long _id) throws IOException {
        this.log.debug("view contact, {}", _id);

        this.id = _id;
        this.contact = this.contactService.getContact(_id);

        this.externalContext.redirect("contact.xhtml");
    }

    public String deleteMessage(final Long _id) {
        final Contact contact = this.contactService.getContact(_id);
        final String firstName = contact.getFirstName();
        final String lastName = contact.getLastName();

        final StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);

        return sb.toString();
    }

    private void addMessage(final String message) {
        final FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
}
