package com.example.coin.Bean;

public class WalletDetail_Entity {
    int id;
    int sotien;
    String thoigian;
    int id_vi;
    public WalletDetail_Entity() {
    }

    public WalletDetail_Entity(int id, int sotien, String thoigian, int id_vi) {
        this.id = id;
        this.sotien = sotien;
        this.thoigian = thoigian;
        this.id_vi = id_vi;
    }
    public WalletDetail_Entity( int sotien, String thoigian, int id_vi) {
        this.sotien = sotien;
        this.thoigian = thoigian;
        this.id_vi = id_vi;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public int getId_vi() {
        return id_vi;
    }

    public void setId_vi(int id_vi) {
        this.id_vi = id_vi;
    }
}
