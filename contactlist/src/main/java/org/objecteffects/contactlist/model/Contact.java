package org.objecteffects.contactlist.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1)
    @NotNull
    @Column(nullable = false)
    private @NotNull String firstName;

    @Size(min = 1)
    @NotNull
    @Column(nullable = false)
    private @NotNull String lastName;

    @Size(min = 5)
    @NotNull
    @Email
    @Column(nullable = false)
    private @NotNull String email;

    @Pattern(regexp = "((\\(\\d{3}\\))|\\d{3})[ -.]?\\d{3}[-.]?\\d{4}")
    @NotNull
    @Column
    private String phoneNumber;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long _id) {
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
        return String.format("%d: %s %s, %s, %s, %s", this.id, this.firstName,
                this.lastName, this.email, this.phoneNumber, this.birthDate);
    }
}
