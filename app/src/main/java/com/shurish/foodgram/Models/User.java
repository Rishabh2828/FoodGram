package com.shurish.foodgram.Models;

public class User {

    String name, email, pass,profile, referCode;

    public User() {
    }

    public User(String name, String email, String pass, String referCode) {
    }

    public User(String name, String email, String pass, String profile, String referCode) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.profile = profile;
        this.referCode = referCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }
}
