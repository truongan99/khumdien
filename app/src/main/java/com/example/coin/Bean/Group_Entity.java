package com.example.coin.Bean;

public class Group_Entity {
    int id;
    String ten;
    String loai;
    Integer hinh;

    public Group_Entity(int id, String ten, String loai, Integer hinh) {
        this.id = id;
        this.ten = ten;
        this.loai = loai;
        this.hinh = hinh;
    }

    public Group_Entity() {
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Integer getHinh() {
        return hinh;
    }

    public void setHinh(Integer hinh) {
        this.hinh = hinh;
    }
}
