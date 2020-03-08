package com.malli.labs.models;

import java.util.Date;
import java.util.List;

public class DataModel {
    String id;
    String name;
    Date dob;
    String location;
    String gender;
    int age;
    List<String> skils;

    public DataModel() {

    }

    public DataModel(String id, String name, Date dob, String location, String gender, int age, List<String> skils) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.location = location;
        this.gender = gender;
        this.age = age;
        this.skils = skils;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getSkils() {
        return skils;
    }

    public void setSkils(List<String> skils) {
        this.skils = skils;
    }
}
