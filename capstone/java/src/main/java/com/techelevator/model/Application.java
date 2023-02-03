package com.techelevator.model;

import java.time.LocalDateTime;

public class Application {
    private int id;
    private int contactId;
    private String statusId;
    private int weeklyHours;
    private boolean isDay;
    private String preferredAnimal;
    private String reason;
    private LocalDateTime timeRegistered;

    public Application() {

    }



    public Application(int id, int contactId, String statusId, int weeklyHours, boolean isDay,
                       String preferredAnimal, String reason, LocalDateTime timeRegistered) {
        this.id = id;
        this.contactId = contactId;
        this.statusId = statusId;
        this.weeklyHours = weeklyHours;
        this.isDay = isDay;
        this.preferredAnimal = preferredAnimal;
        this.reason = reason;
        this.timeRegistered = timeRegistered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public boolean isDay() {
        return isDay;
    }

    public void setDay(boolean day) {
        isDay = day;
    }

    public String getPreferredAnimal() {
        return preferredAnimal;
    }

    public void setPreferredAnimal(String preferredAnimal) {
        this.preferredAnimal = preferredAnimal;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimeRegistered() {
        return timeRegistered;
    }

    public void setTimeRegistered(LocalDateTime timeRegistered) {
        this.timeRegistered = timeRegistered;
    }
}
