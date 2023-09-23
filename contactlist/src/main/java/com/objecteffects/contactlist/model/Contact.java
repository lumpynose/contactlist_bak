package com.objecteffects.contactlist.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private @NotNull String firstName;
    @Column(nullable = false)
    private @NotNull String lastName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public long getId() {
        return this.id;
    }

    public void setId(final long _id) {
        this.id = _id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String _firstName) {
        this.firstName = _firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String _lastName) {
        this.lastName = _lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String _email) {
        this.email = _email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(final String _phoneNumber) {
        this.phoneNumber = _phoneNumber;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(final Date _birthDate) {
        this.birthDate = _birthDate;
    }

    @Override
    public boolean equals(final Object obj) {
        return java.util.Objects.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s, %s, %s", this.firstName, this.lastName,
                this.email, this.phoneNumber, this.birthDate);
    }
}
