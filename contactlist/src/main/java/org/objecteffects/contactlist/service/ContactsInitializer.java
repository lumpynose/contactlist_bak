package org.objecteffects.contactlist.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.objecteffects.contactlist.model.Contact;
import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// @Startup
// @Singleton
public class ContactsInitializer {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private transient Logger log;

    // insert into contact(id, firstname, lastname, email, phonenumber,
    // birthdate) values(999, 'Sophie', 'Hunter', 'sh@stripesbook.org',
    // '555-555-8440',
    // '1981-08-08'),
    // (2,'Daniel','Greene','dg@stripesbook.org','555-555-7763','1986-06-03'),
    // (3,'Jen','Ballou','jb@stripesbook.org','555-555-6495','1982-08-30'),
    // (4,'Sammy','Blair','sb@stripesbook.org','555-555-9592','1981-06-02'),
    // (5,'Betty','Stocker','bs@stripesbook.org','555-555-8316','1987-05-22'),
    // (6,'Lou','Thompson','lt@stripesbook.org','555-555-2765','1980-08-29'),
    // (7,'Lexi','Hawk','lh@stripesbook.org','555-555-7211','1982-05-01'),
    // (8,'George','Wells','gw@stripesbook.org','555-555-7689','1987-05-15'),
    // (9,'Donna','McCallum','dm@stripesbook.org','555-555-3432','1979-12-28'),
    // (10,'Jason','Wilson','jw@stripesbook.org','555-555-4032','1978-04-03');

    @PostConstruct
    public void init() {
        this.log.debug("init");

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar cal = dateFormat.getCalendar();

        final Contact contact1 = new Contact();
        contact1.setEmail("sh@stripesbook.org");
        contact1.setFirstName("Sophie");
        contact1.setLastName("Hunter");
        contact1.setPhoneNumber("555-555-8440");
        contact1.setBirthDate(cal.getTime());
        this.entityManager.persist(contact1);

        final Contact contact2 = new Contact();
        contact2.setEmail("dg@stripesbook.org");
        contact2.setFirstName("Daniel");
        contact2.setLastName("Greene");
        contact2.setPhoneNumber("555-555-8440");
        contact2.setBirthDate(cal.getTime());
        this.entityManager.persist(contact2);

        final Contact contact3 = new Contact();
        contact3.setEmail("sb@stripesbook.org");
        contact3.setFirstName("Sammy");
        contact3.setLastName("Blair");
        contact3.setPhoneNumber("555-555-8440");
        contact3.setBirthDate(cal.getTime());
        this.entityManager.persist(contact3);

        this.entityManager.flush();
    }
}
