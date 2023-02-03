package com.techelevator.model;

public class Contact {
    private int contactId;
    private String contactName;
    private String phone;
    private String email;
    private String city;
    private String state;
    private int age;
    private String socialLink;

    public Contact() {

    }

    public Contact(int contactId, String name, String phone,
                   String email, String city, String state, int age, String socialLink) {
        this.contactId = contactId;
        this.contactName = name;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.state = state;
        this.age = age;
        this.socialLink = socialLink;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }
}
