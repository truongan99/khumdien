package com.example.coin.Bean;

public class Plan_Entity {
    Integer ID;
    String money;
    String note;
    String dateStart;
    String dateEnd;
    Integer id_gr;
    Integer id_account;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getId_gr() {
        return id_gr;
    }

    public void setId_gr(Integer id_gr) {
        this.id_gr = id_gr;
    }

    public Integer getId_account() {
        return id_account;
    }

    public void setId_account(Integer id_account) {
        this.id_account = id_account;
    }

    public Plan_Entity(String money, String note, String dateStart, String dateEnd, Integer id_gr, Integer id_account) {
        this.money = money;
        this.note = note;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.id_gr = id_gr;
        this.id_account = id_account;
    }

    public Plan_Entity(Integer ID, String money, String note, String dateStart, String dateEnd, Integer id_gr, Integer id_account) {
        this.ID = ID;
        this.money = money;
        this.note = note;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.id_gr = id_gr;
        this.id_account = id_account;
    }

    public Plan_Entity() {
    }
}
