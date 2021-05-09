package com.example.coin.Bean;

import java.util.Date;

public class User_Entity {
    String password;
    String email;
    Date birthday;
    String job;

    public User_Entity(String password, String email, Date birthday, String job) {
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.job = job;
    }

    public User_Entity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User_Entity() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
