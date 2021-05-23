package com.example.coin.Bean;

public class Group_Detail_Entity {
    int id;
    String ten;
    Integer hinhanh;
    int id_loai;

    public Group_Detail_Entity() {
    }

    public Group_Detail_Entity(int id, String ten, Integer hinhanh, int id_loai) {
        this.id = id;
        this.ten = ten;
        this.hinhanh = hinhanh;
        this.id_loai = id_loai;
    }

    public Group_Detail_Entity(String ten, Integer hinhanh, int id_loai) {
        this.ten = ten;
        this.hinhanh = hinhanh;
        this.id_loai = id_loai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(Integer hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }
}
