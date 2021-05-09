package com.example.coin.Bean;

public class Wallet_Entity {
    private String user_email;
    private String wallet_name;
    private int wallet_img;
    private String wallet_currency;

    public Wallet_Entity() {
    }

    public Wallet_Entity( String user_email, String wallet_name, int wallet_img, String wallet_currency) {
        this.user_email = user_email;
        this.wallet_name = wallet_name;
        this.wallet_img = wallet_img;
        this.wallet_currency = wallet_currency;
    }


    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getWallet_name() {
        return wallet_name;
    }

    public void setWallet_name(String wallet_name) {
        this.wallet_name = wallet_name;
    }

    public int getWallet_img() {
        return wallet_img;
    }

    public void setWallet_img(int wallet_img) {
        this.wallet_img = wallet_img;
    }

    public String getWallet_currency() {
        return wallet_currency;
    }

    public void setWallet_currency(String wallet_currency) {
        this.wallet_currency = wallet_currency;
    }
}
