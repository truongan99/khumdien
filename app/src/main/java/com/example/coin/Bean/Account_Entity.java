package com.example.coin.Bean;

import java.util.Date;

public class Account_Entity {
    String password;
    String email;
    String tenvi;
    String don_vi_tien;
    Integer hinhanh_vi;

    public Account_Entity(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public Account_Entity(String password, String email, String tenvi, String don_vi_tien, Integer hinhanh_vi) {
        this.password = password;
        this.email = email;
        this.tenvi = tenvi;
        this.don_vi_tien = don_vi_tien;
        this.hinhanh_vi = hinhanh_vi;
    }

    public Account_Entity(String email,String tenvi, String don_vi_tien, Integer hinhanh_vi) {
        this.email = email;
        this.tenvi = tenvi;
        this.don_vi_tien = don_vi_tien;
        this.hinhanh_vi = hinhanh_vi;
    }

    public Account_Entity() {
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

    public String getTenvi() {
        return tenvi;
    }

    public void setTenvi(String tenvi) {
        this.tenvi = tenvi;
    }

    public String getDon_vi_tien() {
        return don_vi_tien;
    }

    public void setDon_vi_tien(String don_vi_tien) {
        this.don_vi_tien = don_vi_tien;
    }

    public Integer getHinhanh_vi() {
        return hinhanh_vi;
    }

    public void setHinhanh_vi(Integer hinhanh_vi) {
        this.hinhanh_vi = hinhanh_vi;
    }
}
