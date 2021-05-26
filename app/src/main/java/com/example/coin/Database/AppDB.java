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
import com.example.coin.Bean.Plan_Entity;
import com.example.coin.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {


    public AppDB(Context context) {
        super(context, "MoneyLover.db", null, 1);
        Log.d("data", "Load Database ");
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

    public void doCreateTbTaiKhoan(SQLiteDatabase db) {
        String tbAccount = "CREATE TABLE IF NOT EXISTS TAIKHOAN(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TENVI NVARCHAR(100), HINHANH_VI INTEGER, DONVITIEN NVARCHAR(100), EMAIL NVARCHAR(100) UNIQUE, PASSWORD NVARCHAR(100))";
        db.execSQL(tbAccount);
        Log.d("data", "Create Table TaiKhoan");
    }

    public void doCreateTbLoai(SQLiteDatabase db) {
        String tb = "CREATE TABLE IF NOT EXISTS LOAI(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TEN NVARCHAR(100), LOAI NVARCHAR(100),HINHANH_NHOM INTEGER)";
        db.execSQL(tb);
        InsertGroup("Eating and drinking", "0", R.drawable.icon_116, db);
        InsertGroup("Bills and utilities", "0", R.drawable.icon_135, db);
        InsertGroup("Move", "0", R.drawable.icon_12, db);
        InsertGroup("Shopping", "0", R.drawable.icon_3, db);
        InsertGroup("Friends and lovers", "0", R.drawable.icon_1, db);
        InsertGroup("Entertainment", "0", R.drawable.icon_49, db);
        InsertGroup("Travel", "0", R.drawable.icon_122, db);
        InsertGroup("Health", "0", R.drawable.icon_58, db);
        InsertGroup("Gifts and Donations", "0", R.drawable.icon_140, db);
        InsertGroup("Family", "0", R.drawable.icon_115, db);
        InsertGroup("Education", "0", R.drawable.icon_113, db);
        InsertGroup("Invest", "0", R.drawable.icon_119, db);
        InsertGroup("Business", "0", R.drawable.icon_59, db);
        InsertGroup("Insurrance", "0", R.drawable.icon_137, db);
        InsertGroup("Cost", "0", R.drawable.icon_138, db);

        InsertGroup("Bonus", "1", R.drawable.icon_111, db);
        InsertGroup("Interest", "1", R.drawable.icon_118, db);
        InsertGroup("Salary", "1", R.drawable.icon_76, db);
        InsertGroup("Sell things", "1", R.drawable.icon_121, db);
        Log.d("data", "Create Table Loai");

    }

    public void doCreateTbCT_Loai(SQLiteDatabase db) {
        String tb = "CREATE TABLE IF NOT EXISTS CT_LOAI(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TEN NVARCHAR(100), HINHANH INTEGER, ID_LOAI INTEGER , FOREIGN KEY (ID_LOAI) REFERENCES LOAI(ID))";
        db.execSQL(tb);
        Log.d("data", "Create Table CT_Loai");
    }

    public void doCreateTbKeHoach(SQLiteDatabase db) {
        String tb = "CREATE TABLE IF NOT EXISTS KEHOACH(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, SOTIEN NVARCHAR(100), NGAYBATDAU DATETIME, NGAYKETTHUC DATETIME, GHICHU NVARCHAR(100), ID_LOAI INTEGER ,ID_TAIKHOAN INTEGER, FOREIGN KEY (ID_LOAI) REFERENCES LOAI(ID), FOREIGN KEY (ID_TAIKHOAN) REFERENCES TAIKHOAN(ID))";
        db.execSQL(tb);
        Log.d("data", "Create Table KeHoach");
    }

    public void doCreateTbCT_Vi(SQLiteDatabase db) {
        String tb = "CREATE TABLE IF NOT EXISTS CT_VI(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TIENHIENTAI INTEGER, THOIGIAN DATETIME, ID_TAIKHOAN INTEGER , FOREIGN KEY (ID_TAIKHOAN) REFERENCES TAIKHOAN(ID))";
        db.execSQL(tb);
        Log.d("data", "Create Table CT_vi");
    }

    public void doCreateTbChiTieu(SQLiteDatabase db) {
        String tb = "CREATE TABLE IF NOT EXISTS CHITIEU(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, SOTIEN NVARCHAR(100), NGAYGIAODICH DATETIME, MOTA NVARCHAR(200), ID_CT_VI INTEGER , ID_CT_LOAI INTEGER , FOREIGN KEY (ID_CT_VI) REFERENCES CT_VI(ID), FOREIGN KEY (ID_CT_LOAI) REFERENCES CT_LOAI(ID))";
        db.execSQL(tb);
        Log.d("data", "Create Table ChiTieu");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        delTable("TAIKHOAN", db);
        delTable("LOAI", db);
        delTable("CT_LOAI", db);
        delTable("CHITIEU", db);
        delTable("CT_VI", db);
        delTable("KEHOACH", db);

        Log.d("data", "On Upgrade");
    }

    public void delTable(String name, SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + name);
        onCreate(db);
        Log.d("data", "On Upgrade Delete Table " + name);
    }

    public void InsertAccount(Account_Entity user) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("EMAIL", user.getEmail());
            values.put("PASSWORD", user.getPassword());
            db.insert("TAIKHOAN", null, values);
            db.close();
            Log.d("data", "Insert TAIKHOAN");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("data", "Insert TAIKHOAN Fail");
        }
    }

    public void UpdateAccount(Account_Entity acc) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("TENVI", acc.getTenvi());
            values.put("DONVITIEN", acc.getDon_vi_tien());
            values.put("HINHANH_VI", acc.getHinhanh_vi());////
            db.update("TAIKHOAN", values, "EMAIL = ?", new String[]{acc.getEmail()});
            db.close();
            Log.d("data", " Update TAIKHOAN");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("data", "Update TAIKHOAN Fail");
        }
    }

    public Account_Entity select_User(String email, String password) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "Select * From TAIKHOAN WHERE EMAIL = ? and PASSWORD = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{email, password});
            Account_Entity acc = new Account_Entity();
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                acc.setID(cursor.getInt(0));
                acc.setTenvi(cursor.getString(1));
                acc.setHinhanh_vi(cursor.getInt(2));
                acc.setDon_vi_tien(cursor.getString(3));
                acc.setEmail(cursor.getString(4));
                acc.setPassword(cursor.getString(5));
                return acc;
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String select_Symbol(Integer ID) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "Select DONVITIEN From TAIKHOAN WHERE ID = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(ID)});
            String sym;
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                sym = cursor.getString(0);
                return sym;
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void InsertGroup(String ten, String loai, int hinhanh, SQLiteDatabase db) {
        try {
            ContentValues values = new ContentValues();
            values.put("TEN", ten);
            values.put("LOAI", loai);
            values.put("HINHANH_NHOM", hinhanh);
            db.insert("LOAI", null, values);
            Log.d("data", " THEM LOAI THANH CONG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertGroup_Detail(Group_Detail_Entity detail) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("TEN", detail.getTen());
            values.put("HINHANH", detail.getHinhanh());
            values.put("ID_LOAI", detail.getId_loai());
            db.insert("CT_LOAI", null, values);
            Log.d("data", " THEM CT_LOAI THANH CONG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Group_Entity> getAllGroup(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Group_Entity> words = new ArrayList<>();
        String sql = "SELECT * FROM LOAI WHERE LOAI = " + type;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                words.add(new Group_Entity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
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
                words.add(new Group_Detail_Entity(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return words;
    }

    public void InsertPlan(Plan_Entity plan) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("SOTIEN", plan.getMoney());
            values.put("NGAYBATDAU", plan.getDateStart());
            values.put("NGAYKETTHUC", plan.getDateEnd());
            values.put("GHICHU", plan.getNote());
            values.put("ID_LOAI", plan.getId_gr());
            values.put("ID_TAIKHOAN", plan.getId_account());
            db.insert("KEHOACH", null, values);
            db.close();
            Log.d("data", "Insert KEHOACH");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("data", "Insert KEHOACH Fail");
        }
    }

    public ArrayList<Plan_Entity> selectPlanRe() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Plan_Entity> plan_re = new ArrayList<>();
        String sql = "SELECT * FROM KEHOACH, LOAI WHERE LOAI.ID = KEHOACH.ID_LOAI AND LOAI.LOAI = 1";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() >= 1) {

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Plan_Entity newplan = new Plan_Entity();
                newplan.setID(cursor.getInt(0));
                newplan.setMoney(cursor.getString(1));
                newplan.setDateStart(cursor.getString(2));
                newplan.setDateEnd(cursor.getString(3));
                newplan.setNote(cursor.getString(4));
                newplan.setId_gr(cursor.getInt(5));
                newplan.setId_account(cursor.getInt(6));
                plan_re.add(newplan);
            }
        }
        return plan_re;
    }

    public ArrayList<Plan_Entity> selectPlanEx() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Plan_Entity> plan_ex = new ArrayList<>();
        String sql = "SELECT * FROM KEHOACH, LOAI WHERE LOAI.ID = KEHOACH.ID_LOAI AND LOAI.LOAI = 0";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() >= 1) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Plan_Entity newplan = new Plan_Entity();
                newplan.setID(cursor.getInt(0));
                newplan.setMoney(cursor.getString(1));
                newplan.setDateStart(cursor.getString(2));
                newplan.setDateEnd(cursor.getString(3));
                newplan.setNote(cursor.getString(4));
                newplan.setId_gr(cursor.getInt(5));
                newplan.setId_account(cursor.getInt(6));
                plan_ex.add(newplan);
            }
        }
        return plan_ex;
    }

    public Integer selectImg(int id_group) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT HINHANH_NHOM FROM LOAI WHERE ID = " + id_group;
        int img_id = 0;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                img_id = cursor.getInt(0);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return img_id;
    }

    public String selectName(int id_group) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT TEN FROM LOAI WHERE ID = " + id_group;
        String name = "";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return name;
    }

    public Integer sumMoney(int id_group, int id_account, String dateStart, String dateEnd) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT NGAYGIAODICH, SOTIEN FROM CHITIEU WHERE ID_CT_LOAI IN (SELECT ID FROM CT_LOAI WHERE ID_LOAI = " + id_group + ") AND ID_CT_VI = " + id_account;
        int sum = 0;
        SimpleDateFormat checkformat = new SimpleDateFormat("dd/MM/yyyy");
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    Date start = checkformat.parse(dateStart);
                    Date end = checkformat.parse(dateEnd);
                    Date date = checkformat.parse(cursor.getString(0));
                    if (start.before(date)) {

                        if (end.after(date)) {

                            sum += cursor.getInt(1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return sum;
    }

    public void UpdatePlan(Plan_Entity plan) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("SOTIEN", plan.getMoney());
            values.put("NGAYBATDAU", plan.getDateStart());
            values.put("NGAYKETTHUC", plan.getDateEnd());
            values.put("GHICHU", plan.getNote());
            values.put("ID_LOAI", plan.getId_gr());
            values.put("ID_TAIKHOAN", plan.getId_account());
            db.update("KEHOACH", values, "ID = ?", new String[]{plan.getID().toString()});
            db.close();
            Log.d("data", "Update KEHOACH");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("data", "Update KEHOACH Fail");
        }
    }

    public void DeletePlan(int ID) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            db.delete("KEHOACH", "ID = ?", new String[]{String.valueOf(ID)});
            db.close();
            Log.d("data", "Delete KEHOACH");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("data", "Delete KEHOACH Fail");
        }
    }

    public HashMap<String, String> getDataChart(int id_group, int id_account, String dateStart, String dateEnd) {
        HashMap<String, String> linechart = new HashMap<String, String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT NGAYGIAODICH, SOTIEN FROM CHITIEU WHERE ID_CT_LOAI IN (SELECT ID FROM CT_LOAI WHERE ID_LOAI = " + id_group + ") AND ID_CT_VI = " + id_account;

        List<String> dayTrading_secondary = new ArrayList<String>();
        List<String> money_secondary = new ArrayList<String>();

        SimpleDateFormat checkformat = new SimpleDateFormat("dd/MM/yyyy");
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    Date start = checkformat.parse(dateStart);
                    Date end = checkformat.parse(dateEnd);
                    Date date = checkformat.parse(cursor.getString(0));
                    if (start.before(date)) {
                        if (end.after(date)) {
                            dayTrading_secondary.add(cursor.getString(0));
                            money_secondary.add(cursor.getString(1));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
            cursor.close();
        }

        List<String> dayTrading_main = new ArrayList<String>();
        List<String> money_main = new ArrayList<String>();

        for (int i = 0; i < dayTrading_secondary.size(); i++) {
            boolean check = false;

            for (int j = 0; j < dayTrading_main.size(); j++) {
                if (dayTrading_main.get(j).equalsIgnoreCase(dayTrading_secondary.get(i))) {
                    check = true;
                    money_main.add(money_main.get(j) + money_secondary.get(i));
                    break;
                }
            }

            if (!check) {
                dayTrading_main.add(dayTrading_secondary.get(i));
                money_main.add(money_secondary.get(i));
            }

        }

        for (int i = 0; i < dayTrading_main.size(); i++) {
            linechart.put(dayTrading_main.get(i), money_main.get(i));
        }

        return linechart;
    }


}
