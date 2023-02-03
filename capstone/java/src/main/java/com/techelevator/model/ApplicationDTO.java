package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class ApplicationDTO {
    @NotNull
    private Application application;
    @NotNull
    private Contact contact;

    public ApplicationDTO(Application application, Contact contact) {
        this.application = application;
        this.contact = contact;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
