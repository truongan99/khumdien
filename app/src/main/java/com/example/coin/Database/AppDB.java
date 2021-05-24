package com.example.coin.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.coin.Bean.Account_Entity;
import com.example.coin.Bean.Group_Detail_Entity;
import com.example.coin.Bean.Group_Entity;
import com.example.coin.R;

import java.util.ArrayList;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {


    public AppDB(Context context) {
        super(context, "MoneyLover.db", null, 1);
        Log.d("data","Load Database ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        doCreateTbTaiKhoan(db);
        doCreateTbLoai(db);
        doCreateTbCT_Loai(db);
        doCreateTbKeHoach(db);
        doCreateTbCT_Vi(db);
        doCreateTbChiTieu(db);
    }
    public void doCreateTbTaiKhoan(SQLiteDatabase db){
        String tbAccount= "CREATE TABLE IF NOT EXISTS TAIKHOAN(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TENVI NVARCHAR(100), HINHANH_VI INTEGER, DONVITIEN NVARCHAR(100), EMAIL NVARCHAR(100) UNIQUE, PASSWORD NVARCHAR(100))";
        db.execSQL(tbAccount);
        Log.d("data","Create Table TaiKhoan");
    }
    public void doCreateTbLoai(SQLiteDatabase db){
        String tb= "CREATE TABLE IF NOT EXISTS LOAI(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TEN NVARCHAR(100), LOAI NVARCHAR(100),HINHANH_NHOM INTEGER)";
        db.execSQL(tb);
        InsertGroup("Eating and drinking","0",R.drawable.icon_116,db);
        InsertGroup("Bills and utilities","0",R.drawable.icon_135,db);
        InsertGroup("Move","0",R.drawable.icon_12,db);
        InsertGroup("Shopping","0",R.drawable.icon_3,db);
        InsertGroup("Friends and lovers","0",R.drawable.icon_1,db);
        InsertGroup("Entertainment","0",R.drawable.icon_49,db);
        InsertGroup("Travel","0",R.drawable.icon_122,db);
        InsertGroup("Health","0",R.drawable.icon_58,db);
        InsertGroup("Gifts and Donations","0",R.drawable.icon_140,db);
        InsertGroup("Family","0",R.drawable.icon_115,db);
        InsertGroup("Education","0",R.drawable.icon_113,db);
        InsertGroup("Invest","0",R.drawable.icon_119,db);
        InsertGroup("Business","0",R.drawable.icon_59,db);
        InsertGroup("Insurrance","0",R.drawable.icon_137,db);
        InsertGroup("Cost","0",R.drawable.icon_138,db);

        InsertGroup("Bonus","1",R.drawable.icon_111,db);
        InsertGroup("Interest","1",R.drawable.icon_118,db);
        InsertGroup("Salary","1",R.drawable.icon_76,db);
        InsertGroup("Sell things","1",R.drawable.icon_121,db);
        Log.d("data","Create Table Loai");

    }
    public void doCreateTbCT_Loai(SQLiteDatabase db){
        String tb= "CREATE TABLE IF NOT EXISTS CT_LOAI(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TEN NVARCHAR(100), HINHANH INTEGER, ID_LOAI INTEGER , FOREIGN KEY (ID_LOAI) REFERENCES LOAI(ID))";
        db.execSQL(tb);
        Log.d("data","Create Table CT_Loai");
    }
    public void doCreateTbKeHoach(SQLiteDatabase db){
        String tb= "CREATE TABLE IF NOT EXISTS KEHOACH(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TEN NVARCHAR(100), SOTIEN INTEGER, NGAYBATDAU DATETIME, NGAYKETTHUC DATETIME, ID_CT_LOAI INTEGER , FOREIGN KEY (ID_CT_LOAI) REFERENCES CT_LOAI(ID))";
        db.execSQL(tb);
        Log.d("data","Create Table KeHoach");
    }
    public void doCreateTbCT_Vi(SQLiteDatabase db){
        String tb= "CREATE TABLE IF NOT EXISTS CT_VI(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TIENHIENTAI INTEGER, THOIGIAN DATETIME, ID_TAIKHOAN INTEGER , FOREIGN KEY (ID_TAIKHOAN) REFERENCES TAIKHOAN(ID))";
        db.execSQL(tb);
        Log.d("data","Create Table CT_vi");
    }
    public void doCreateTbChiTieu(SQLiteDatabase db){
        String tb= "CREATE TABLE IF NOT EXISTS CHITIEU(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, SOTIEN INTEGER, NGAYGIAODICH DATETIME, MOTA NVARCHAR(200), ID_CT_VI INTEGER , ID_CT_LOAI INTEGER , FOREIGN KEY (ID_CT_VI) REFERENCES CT_VI(ID), FOREIGN KEY (ID_CT_LOAI) REFERENCES CT_LOAI(ID))";
        db.execSQL(tb);
        Log.d("data","Create Table ChiTieu");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        delTable("TAIKHOAN",db);
        delTable("LOAI",db);
        delTable("CT_LOAI",db);
        delTable("CHITIEU",db);
        delTable("CT_VI",db);
        delTable("KEHOACH",db);

        Log.d("data","On Upgrade");
    }
    public void delTable(String name,SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + name);
        onCreate(db);
        Log.d("data","On Upgrade Delete Table "+name);
    }
    public void InsertAccount(Account_Entity user){
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("EMAIL",user.getEmail());
            values.put("PASSWORD",user.getPassword());
            db.insert("TAIKHOAN",null,values);
            db.close();
            Log.d("data","Insert TAIKHOAN");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("data","Insert TAIKHOAN Fail");
        }
    }
    public void UpdateAccount(Account_Entity acc){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("TENVI",acc.getTenvi());
            values.put("DONVITIEN",acc.getDon_vi_tien());
            values.put("HINHANH_VI",acc.getHinhanh_vi());////
            db.update("TAIKHOAN",values,"EMAIL = ?",new String[]{acc.getEmail()});
            db.close();
            Log.d("data"," Update TAIKHOAN");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("data","Update TAIKHOAN Fail");
        }
    }
    public Account_Entity select_User(String email, String password){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "Select * From TAIKHOAN WHERE EMAIL = ? and PASSWORD = ?";
            Cursor cursor = db.rawQuery(sql,new String[]{email,password});
            Account_Entity acc = new Account_Entity();
            if(cursor!=null && cursor.getCount() > 0){
                cursor.moveToFirst();
                acc.setTenvi(cursor.getString(1));
                acc.setHinhanh_vi(cursor.getInt(2));
                acc.setDon_vi_tien(cursor.getString(3));
                acc.setEmail(cursor.getString(4));
                acc.setPassword(cursor.getString(5));
                return acc;
            }
            else return  null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void InsertGroup(String ten,String loai , int hinhanh,SQLiteDatabase db){
        try{
            ContentValues values = new ContentValues();
            values.put("TEN",ten);
            values.put("LOAI",loai);
            values.put("HINHANH_NHOM",hinhanh);
            db.insert("LOAI",null,values);
            Log.d("data"," THEM LOAI THANH CONG");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void InsertGroup_Detail(Group_Detail_Entity detail){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("TEN",detail.getTen());
            values.put("HINHANH",detail.getHinhanh());
            values.put("ID_LOAI",detail.getId_loai());
            db.insert("CT_LOAI",null,values);
            Log.d("data"," THEM CT_LOAI THANH CONG");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Group_Entity> getAllGroup(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Group_Entity> words = new ArrayList<>();
        String sql = "SELECT * FROM LOAI WHERE LOAI = "+type;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                words.add(new Group_Entity(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return words;
    }
    public List<Group_Detail_Entity> getAllGroupDe() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Group_Detail_Entity> words = new ArrayList<>();
        String sql = "SELECT * FROM CT_LOAI";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                words.add(new Group_Detail_Entity(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),cursor.getInt(3)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return words;
    }
}
