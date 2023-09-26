package org.objecteffects.contactlist.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Singleton;

@Model
@Singleton
public class LoggerProducer {
    @Produces
    public Logger produceLogger(final InjectionPoint injectionPoint) {
        return LoggerFactory
                .getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
