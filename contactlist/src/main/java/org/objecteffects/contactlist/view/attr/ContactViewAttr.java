package org.objecteffects.contactlist.view.attr;

import java.io.Serializable;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.objecteffects.contactlist.view.ContactUtil;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ContactViewAttr implements Serializable {
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
    @Inject
    private ContactUtil contactUtil;

    @PostConstruct
    public void init() {
        this.log.debug("init: paramId: {}", this.paramId);

        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final String implementationTitle =
                facesContext.getClass().getPackage().getImplementationTitle();
        this.log.debug("ContactViewFlash: implementation title: {}",
                implementationTitle);
    }

    public Contact getContact() {
        getId();

        this.log.debug("getContact: id: {}", this.id);

        this.contact = this.contactService.getContact(this.id);

        return this.contact;
    }

    public String deleteContact(Long _id) {
        _id = this.id;

        this.log.debug("deleteContact: id: {}", _id);

        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .setKeepMessages(true);

        this.contactUtil.addMessage(this.contactService.getContact(_id),
                "deleted");

        this.contactService.deleteContact(_id);

        return "contactlistparam.xhtml?faces-redirect=true";
    }

    private void getId() {
        this.log.debug("getId: id: {}", this.id);

        if (this.paramId != null) {
            this.log.debug("getId: paramId: {}", this.paramId);
            this.id = Long.valueOf(this.paramId);
        }
    }
}
