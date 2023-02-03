package com.techelevator.model;

public class Pet {
    private int id;
    private String name;
    private String type;
    private int age;
    private String sex;
    private String breed;
    private String size;
    private boolean isGoodWithKids;
//    private boolean isGoodWithAnimals;
    private boolean isGoodWithDogs;
    private boolean isGoodWithCats;
    private boolean isAvailable;
    private String imageLink;
    private Integer userId;

    public Pet(){

    }

    public Pet(int id, String name, String type, int age, String sex, String breed, String size, boolean isGoodWithKids,
               boolean isGoodWithDogs, boolean isGoodWithCats, boolean isAvailable, String imageLink, Integer userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.sex = sex;
        this.breed = breed;
        this.size = size;
        this.isGoodWithKids = isGoodWithKids;
        this.isGoodWithDogs = isGoodWithDogs;
        this.isGoodWithCats = isGoodWithCats;
        this.isAvailable = isAvailable;
        this.imageLink = imageLink;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isGoodWithKids() {
        return isGoodWithKids;
    }

    public void setGoodWithKids(boolean isGoodWithKids) {
        this.isGoodWithKids = isGoodWithKids;
    }

    public boolean isGoodWithDogs() {
        return isGoodWithDogs;
    }

    public void setGoodWithDogs(boolean goodWithDogs) {
        isGoodWithDogs = goodWithDogs;
    }

    public boolean isGoodWithCats() {
        return isGoodWithCats;
    }

    public void setGoodWithCats(boolean goodWithCats) {
        isGoodWithCats = goodWithCats;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
