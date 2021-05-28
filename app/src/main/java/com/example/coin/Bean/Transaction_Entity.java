package com.example.coin.Bean;

public class Transaction_Entity {
    int id;
    int sotien;
    String ngaygiaodich;
    String mota;
    int id_cr_loai;
    int id_ct_vi;

    public Transaction_Entity(int sotien, String ngaygiaodich, String mota, int id_cr_loai, int id_ct_vi) {
        this.sotien = sotien;
        this.ngaygiaodich = ngaygiaodich;
        this.mota = mota;
        this.id_cr_loai = id_cr_loai;
        this.id_ct_vi = id_ct_vi;
    }

    public int getId_ct_vi() {
        return id_ct_vi;
    }

    public void setId_ct_vi(int id_ct_vi) {
        this.id_ct_vi = id_ct_vi;
    }

    public Transaction_Entity(int id, int sotien, String ngaygiaodich, String mota, int id_cr_loai) {
        this.id = id;
        this.sotien = sotien;
        this.ngaygiaodich = ngaygiaodich;
        this.mota = mota;
        this.id_cr_loai = id_cr_loai;
    }

    public Transaction_Entity(int id, int sotien, String ngaygiaodich, String mota, int id_cr_loai, int id_ct_vi) {
        this.id = id;
        this.sotien = sotien;
        this.ngaygiaodich = ngaygiaodich;
        this.mota = mota;
        this.id_cr_loai = id_cr_loai;
        this.id_ct_vi = id_ct_vi;
    }

    public Transaction_Entity() {
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

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    public int getId_cr_loai() {
        return id_cr_loai;
    }

    public void setId_cr_loai(int id_cr_loai) {
        this.id_cr_loai = id_cr_loai;
    }
}
