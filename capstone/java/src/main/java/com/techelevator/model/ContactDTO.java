package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class ContactDTO {
    @NotNull
    private User user;
    @NotNull
    private Contact contact;

    public ContactDTO(User user, Contact contact) {
        this.user = user;
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
