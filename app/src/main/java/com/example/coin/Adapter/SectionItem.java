package com.example.coin.Adapter;

import com.example.coin.Bean.Item_gr;

public class SectionItem implements Item_gr {
    private  String Ten_gr;
    private  int id_gr;
    private  int hinh_gr;

    public SectionItem(String ten_gr, int id_gr, int hinh_gr) {
        Ten_gr = ten_gr;
        this.id_gr = id_gr;
        this.hinh_gr = hinh_gr;
    }
    public SectionItem() {
    }

    @Override
    public String getName() {
        return Ten_gr;
    }
    @Override
    public int getHinh() {
        return hinh_gr;
    }

    @Override
    public int getID() {
        return id_gr;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
