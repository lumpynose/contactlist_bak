package org.objecteffects.contactlist.view;

import org.slf4j.Logger;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;

public class DebugPhaseListener implements PhaseListener {
    private static final long serialVersionUID = -2838195307829578371L;

    @Inject
    private transient Logger log;

    public DebugPhaseListener() {
    }

    @Override
    public void afterPhase(final PhaseEvent event) {
        this.log.debug("After Phase: {}", event.getPhaseId());

    }

    @Override
    public void beforePhase(final PhaseEvent event) {
        this.log.debug("Before Phase: {}", event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
