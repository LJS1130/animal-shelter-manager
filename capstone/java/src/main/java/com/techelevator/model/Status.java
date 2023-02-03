package com.techelevator.model;

public class Status {

    private char statusId;
    private String statusName;

    public Status(char statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public char getStatusId() {
        return statusId;
    }

    public void setStatusId(char statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
