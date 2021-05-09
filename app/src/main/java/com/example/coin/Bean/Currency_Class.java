package com.example.coin.Bean;

public class Currency_Class {
    private String name;
    private int img;
    private String symbol;

    public Currency_Class(String name, int img, String symbol) {
        this.name = name;
        this.img = img;
        this.symbol = symbol;
    }

    public Currency_Class() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
