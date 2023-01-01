package com.konden.projectpart2.room.profile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class ProfileEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String email;
    private String birthday;
    private String gender;
    private String country;

    public ProfileEntity() {
    }

    public ProfileEntity(String username, String email, String birthday, String gender, String country) {
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
