package com.bryanchristopher202102276.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "project2.db";

    public DBHelper(@Nullable Context context) {
        super(context,"project2.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create table biodata(nim TEXT primary key, nama TEXT, jeniskelamin TEXT, alamat TEXT, email TEXT)");
        sqLiteDatabase.execSQL("create table buku(kodebuku TEXT primary key, judulbuku TEXT, pengarang TEXT, penerbit TEXT, nomorisbn TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists biodata");
        sqLiteDatabase.execSQL("drop table if exists buku");
    }
    public Boolean insertData (String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username",username);
        values.put("password",password);
        long result = db.insert("users", null,values);
        if (result==1) return false;
        else
            return true;
    }
    public Boolean checkusername (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

    public Boolean checkusernamepassword (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

    public Boolean insertDataMhs (String nim,String nama, String jeniskelamin, String alamat, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nim",nim);
        values.put("nama",nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);
        long result = db.insert("biodata", null,values);
        if (result==1) return false;
        else
            return true;
    }
    public Boolean checkkodebuku (String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from biodata where nim=?", new String[] {nim});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

    public Cursor tampildataMhs(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from biodata", null);
        return cursor;
    }

    public boolean hapusDataMhs (String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from biodata where nim=?", new String[]{nim});
        if (cursor.getCount()>0){
            long result = db.delete("biodata", "nim=?", new String[]{nim});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean editDataMhs (String nim,String nama, String jeniskelamin, String alamat, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama",nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);
        Cursor cursor = db.rawQuery("Select * from biodata where nim=?", new String[]{nim});
        if (cursor.getCount()>0){
            long result = db.update("biodata",values, "nim=?",new String[]{nim});
            if (result == -1){
                return false;
            }else {
                return true;
            }

        }else {
            return  false;
        }
    }



    public Boolean insertDatabuku (String kodebuku,String judulbuku, String pengarang, String penerbit, String nomorisbn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kodebuku",kodebuku);
        values.put("judulbuku",judulbuku);
        values.put("pengarang", pengarang);
        values.put("penerbit", penerbit);
        values.put("nomorisbn", nomorisbn);
        long result = db.insert("buku", null,values);
        if (result==1) return false;
        else
            return true;
    }

    public Cursor tampildatabuku(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from buku", null);
        return cursor;
    }

    public boolean hapusDatabuku (String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from buku where kodebuku=?", new String[]{nim});
        if (cursor.getCount()>0){
            long result = db.delete("buku", "kodebuku=?", new String[]{nim});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean editDatabuku (String kode,String judul, String pengarang, String penerbit, String nomorisbn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kodebuku",kode);
        values.put("judulbuku", judul);
        values.put("pengarang", pengarang);
        values.put("penerbit", penerbit);
        values.put("nomorisbn", nomorisbn);
        Cursor cursor = db.rawQuery("Select * from buku where kodebuku=?", new String[]{kode});
        if (cursor.getCount()>0){
            long result = db.update("buku",values, "kodebuku=?",new String[]{kode});
            if (result == -1){
                return false;
            }else {
                return true;
            }

        }else {
            return  false;
        }
    }

    public Boolean checkkodebuku2 (String kodebuku){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from buku where kodebuku=?", new String[] {kodebuku});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

    public Boolean checkusernamepasswordbuku (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }







}
