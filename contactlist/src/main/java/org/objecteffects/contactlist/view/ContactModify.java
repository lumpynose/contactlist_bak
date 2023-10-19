package org.objecteffects.contactlist.view;

import java.io.Serializable;

import org.objecteffects.contactlist.model.Contact;
import org.objecteffects.contactlist.service.ContactService;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ConversationScoped
public class ContactModify implements Serializable {
    private static final long serialVersionUID = 4694088548123087426L;

    @Inject
    private transient Logger log;

    @Inject
    private ContactService contactService;

    @Inject
    private ContactUtil contactUtil;

    @Inject
    private Conversation conversation;

    @Inject
    @ManagedProperty(value = "#{param.paramId}")
    private String paramId;

    Contact contact;

    @PostConstruct
    public void init() {
        this.log.debug("init: paramId: {}", this.paramId);
    }

    public Contact getContact() {
        this.log.debug("getContact: paramId: {}", this.paramId);

        return this.contact;
    }

    public String fetchContact(final Long id) {
        if (this.conversation.isTransient()) {
            this.conversation.begin();
        }

        this.log.debug("fetchContact: id: {}", id);

        this.contact =
                this.contactService.getContact(id);

        return "/contactmodify.xhtml?faces-redirect=true";
    }

    public String modifyContact() {
        this.log.debug("modifyContact: paramId: {}, contact: {}", this.paramId,
                this.contact);

        this.contactService.mergeContact(this.contact);

        this.contactUtil.addMessage(this.contact, "modified");

        if (!this.conversation.isTransient()) {
            this.conversation.end();
        }

        return "/contactlist.xhtml?faces-redirect=true";
    }
}
