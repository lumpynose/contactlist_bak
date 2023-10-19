package org.objecteffects.contactlist.view;

import java.io.Serializable;

import org.apache.commons.validator.routines.EmailValidator;
import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactAdd implements Serializable {
    private static final long serialVersionUID = -3294594409478388858L;

    @Inject
    private transient Logger log;

    @Inject
    private ContactService contactService;
    @Inject
    private ContactUtil contactUtil;

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

        this.contactService.mergeContact(this.contact);

        this.contactUtil.addMessage(this.contact, "added");

        return "/contactlist.xhtml?faces-redirect=true";
    }

    public void validateEmail(final FacesContext ctx, final UIComponent cmp,
            final Object value) {
        final String email = (String) value;

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new ValidatorException(
                    new FacesMessage("Invalid email address"));
        }
    }
}
