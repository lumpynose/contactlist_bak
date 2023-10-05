package org.objecteffects.contactlist.view;

import org.objecteffects.contactlist.model.Contact;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Singleton;

@Singleton
public class ContactUtil {
    static void addMessage(final Contact contact, final String msg) {
        final String firstName = contact.getFirstName();
        final String lastName = contact.getLastName();

        final StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        sb.append(" ");
        sb.append(msg);

        final String message = sb.toString();

        final FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
}
