package com.example.coin.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.coin.Bean.User_Entity;
import com.example.coin.Bean.Wallet_Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppDB extends SQLiteOpenHelper {


    public AppDB(Context context) {
        super(context, "MoneyLover.db", null, 1);
        Log.d("data","Load Database ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        doCreateTbUser(db);
        doCreateTbWallet(db);
    }
    public void doCreateTbUser(SQLiteDatabase db){
        String tbUser= "CREATE TABLE tbUser (Email TEXT PRIMARY KEY,Password TEXT, Birthday DATE,Job TEXT)";
        db.execSQL(tbUser);
        Log.d("data","Create Table User");
    }
    public void doCreateTbWallet(SQLiteDatabase db){
        String tbWallet= "CREATE TABLE tbWallet (Id INTEGER primary key autoincrement NOT NULL ,Email TEXT NOT NULL,WalletName TEXT NOT NULL UNIQUE,WalletImg INTEGER NOT NULL,WalletCurrency TEXT NOT NULL,FOREIGN KEY(Email) REFERENCES tbUser (Email))";
        db.execSQL(tbWallet);
        Log.d("data","Create Table Wallet");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        delTable("tbUser",db);
        delTable("tbWallet",db);
        Log.d("data","On Upgrade");
    }
    public void delTable(String name,SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + name);
        onCreate(db);
        Log.d("data","On Upgrade Delete Table "+name);
    }
    public void InsertUser(User_Entity user){
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Email",user.getEmail());
            values.put("Password",user.getPassword());
            db.insert("tbUser",null,values);
            db.close();
            Log.d("data","4. Insert");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("data","4. Insert Fail");
        }
    }
    public void InsertWallet(Wallet_Entity wallet){
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Email",wallet.getUser_email());
            values.put("WalletName",wallet.getWallet_name());
            values.put("WalletImg",wallet.getWallet_img());
            values.put("WalletCurrency",wallet.getWallet_currency());
            db.insert("tbWallet",null,values);
            db.close();
            Log.d("data","Insert Wallet done !");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("data","Insert Wallet Fail");
        }
    }
    public void UpdateUser(User_Entity user){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "Update tbUser set Email = ?,Password = ?,Birthday = ?,Job = ?";
            ContentValues values = new ContentValues();
            values.put("Email",user.getEmail());
            values.put("Password",user.getPassword());
            values.put("Birthday", String.valueOf(user.getBirthday()));////
            values.put("Job",user.getJob());
            db.update("tbUser",values,"Username = ?",new String[]{user.getEmail()});
            db.close();
            Log.d("data","5. Update");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("data","5. Update Fail");
        }
    }
    public User_Entity select_User(String email, String password){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "Select * From tbUser WHERE Email = ? and Password = ?";
            Cursor cursor = db.rawQuery(sql,new String[]{email,password});
            User_Entity user = new User_Entity();
            if(cursor!=null && cursor.getCount() > 0){
                cursor.moveToFirst();
                if(cursor.getString(2)!=null){
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(2));
                    user.setBirthday(date);
                }

                user.setEmail(cursor.getString(0));
                user.setPassword(cursor.getString(1));

                user.setJob(cursor.getString(3));
                return user;
            }
            else return  null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
