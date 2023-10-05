package org.objecteffects.contactlist.view;

import java.io.Serializable;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
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
    private transient Logger log;

    @Inject
    @ManagedProperty(value = "#{param.id}")
    private String paramId;

    @Inject
    private ContactService contactService;

    @PostConstruct
    public void init() {
        this.log.debug("init: paramId: {}", this.paramId);

        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final String implementationTitle =
                facesContext.getClass().getPackage().getImplementationTitle();
        this.log.debug("ContactView: implementation title: {}",
                implementationTitle);
    }

    public Contact getContact() {
        getId();

        this.log.debug("getContact: id: {}", this.id);

        this.contact = this.contactService.getContact(this.id);

        return this.contact;
    }

    public String deleteContact(final Long _id) {
        // getId();

        // this.log.debug("delete contact, {}", this.id);
        this.log.debug("deleteContact: id: {}", _id);

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .setKeepMessages(true);

        // addMessage(this.id);
        addMessage(_id);

        // this.contactService.deleteContact(this.id);
        this.contactService.deleteContact(_id);

        // this.externalContext.redirect("contactlist.xhtml");
        return "contactlist?faces-redirect=true";
    }

    private void getId() {
        this.log.debug("getId: id: {}", this.id);

        if (this.paramId != null) {
            this.log.debug("getId: paramId: {}", this.paramId);
            this.id = Long.valueOf(this.paramId);
        }
    }

    private void addMessage(final Long _id) {
        this.log.debug("addMessage: adding message: {}", _id);

        final Contact contact = this.contactService.getContact(_id);
        final String firstName = contact.getFirstName();
        final String lastName = contact.getLastName();

        final StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);

        final String message = sb.toString();

        final FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

//  public String viewContact(final Long _id) throws IOException {
//      this.log.debug("view contact: {}", _id);
//
//      this.contact = this.contactService.getContact(_id);
//
//      // this.externalContext.redirect("contactview.xhtml");
//      return "contactview?faces-redirect=true";
//  }
}
