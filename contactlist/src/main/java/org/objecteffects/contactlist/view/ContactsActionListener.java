package org.objecteffects.contactlist.view;

import org.slf4j.Logger;

import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;
import jakarta.inject.Inject;

public class ContactsActionListener implements ActionListener {
    @Inject
    private transient Logger log;

    @Override
    public void processAction(final ActionEvent event)
            throws AbortProcessingException {
        this.log.debug("action phase id: {}", event.getPhaseId());
    }
}
