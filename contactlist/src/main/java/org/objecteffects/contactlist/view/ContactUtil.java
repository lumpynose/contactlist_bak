package org.objecteffects.contactlist.view;

import java.io.IOException;

import org.objecteffects.contactlist.model.Contact;
import org.slf4j.Logger;

import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;

@Stateless
public class ContactUtil {
    @Inject
    private transient Logger log;

    public void addMessage(final Contact contact, final String msg) {
        final String firstName = contact.getFirstName();
        final String lastName = contact.getLastName();

        final StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        sb.append(" ");
        sb.append(msg);

        final String message = sb.toString();

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .setKeepMessages(true);

        final FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);

        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public Contact getFromFlash() {
        final Flash flash = FacesContext.getCurrentInstance()
                .getExternalContext().getFlash();

        final Contact contact = (Contact) flash.get("contact");

        this.log.debug("getFromFlash: contact: {}", contact);

        return contact;
    }

//    public Long getFromFlash() {
//        final Flash flash = FacesContext.getCurrentInstance()
//                .getExternalContext().getFlash();
//
//        final Long id = (Long) flash.get("id");
//
//        this.log.debug("getFromFlash: id: {}", id);
//
//        return id;
//    }

    public String addToFlash(final Contact contact) throws IOException {
        this.log.debug("addToFlash: id: {}", contact);

        final Flash flash = FacesContext.getCurrentInstance()
                .getExternalContext().getFlash();

        flash.put("contact", contact);

        return "contactviewflash?faces-redirect=true";
    }

//    public String addToFlash(final Long id) throws IOException {
//        this.log.debug("addToFlash: id: {}", id);
//
//        final Flash flash = FacesContext.getCurrentInstance()
//                .getExternalContext().getFlash();
//
//        flash.put("id", id);
//
//        return "contactviewflash?faces-redirect=true";
//    }
}
