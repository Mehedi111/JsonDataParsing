package com.weebly.sarikcyber.recylerview;

/**
 * Created by dustu on 10/25/2017.
 */

public class UserAdd {

    private int id;
    private String title, location, divisions, dateofrent, details, gender, rent;
    String name = "";
    String phoneNoOne = "";
    String phonenotwo = "";
    String email = "";

    public UserAdd(){};

    public UserAdd(String title, String location, String dateofrent) {
        this.title = title;
        this.location = location;
        this.dateofrent = dateofrent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNoOne() {
        return phoneNoOne;
    }

    public void setPhoneNoOne(String phoneNoOne) {
        this.phoneNoOne = phoneNoOne;
    }

    public String getPhonenotwo() {
        return phonenotwo;
    }

    public void setPhonenotwo(String phonenotwo) {
        this.phonenotwo = phonenotwo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateofrent() {
        return dateofrent;
    }

    public void setDateofrent(String dateofrent) {
        this.dateofrent = dateofrent;
    }

    public String getDivisions() {
        return divisions;
    }

    public void setDivisions(String divisions) {
        this.divisions = divisions;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
