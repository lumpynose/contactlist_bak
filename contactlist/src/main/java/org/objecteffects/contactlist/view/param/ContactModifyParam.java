package org.objecteffects.contactlist.view.param;

import java.io.Serializable;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.objecteffects.contactlist.view.ContactUtil;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactModifyParam implements Serializable {
    private static final long serialVersionUID = 4694088548123087426L;

    @Inject
    private transient Logger log;

    @Inject
    private ContactService contactService;
    @Inject
    private ContactUtil contactUtil;

    Contact contact;

    @Inject
    @ManagedProperty(value = "#{param.paramId}")
    private String paramId;

    @PostConstruct
    public void init() {
        this.log.debug("init: paramId: {}", this.paramId);
    }

    public Contact getContact() {
        this.log.debug("getContact: paramId: {}", this.paramId);

        this.contact =
                this.contactService.getContact(Long.valueOf(this.paramId));

        return this.contact;
    }

    public String modifyContact() {
        this.log.debug("modifyContact: paramId: {}, contact: {}", this.paramId,
                this.contact);

        // does a merge()
        this.contactService.addContact(this.contact);

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .setKeepMessages(true);

        this.contactUtil.addMessage(this.contact, "modified");

        return "/param/contactlistparam.xhtml?faces-redirect=true";
    }
}
