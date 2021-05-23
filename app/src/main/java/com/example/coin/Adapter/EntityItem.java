package com.example.coin.Adapter;

import com.example.coin.Bean.Item_gr;

public class EntityItem implements Item_gr {
    private  String Ten_grd;
    private  int id_grd;
    private  int hinh_grd;

    public EntityItem(String ten_gr, int id_gr, int hinh_gr) {
        Ten_grd = ten_gr;
        this.id_grd = id_gr;
        this.hinh_grd = hinh_gr;
    }
    public EntityItem() {
    }

    @Override
    public String getName() {
        return Ten_grd;
    }
    @Override
    public int getHinh() {
        return hinh_grd;
    }

    @Override
    public int getID() {
        return id_grd;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}
