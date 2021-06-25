package com.example.regdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(firstName TEXT,middleName TEXT,lastName TEXT,gender TEXT,dob TEXT,mobileNumber TEXT,eMail TEXT primary key,nativePlace TEXT,password TEXT,cpassword TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int il) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String firstName,String middleName,String lastName,String gender,String dob,String mobileNumber,String eMail,String nativePlace,String password,String cpassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName",firstName);
        contentValues.put("middleName",middleName);
        contentValues.put("lastName",lastName);
        contentValues.put("gender",gender);
        contentValues.put("dob",dob);
        contentValues.put("mobileNumber",mobileNumber);
        contentValues.put("eMail",eMail);
        contentValues.put("nativePlace",nativePlace);
        contentValues.put("password",password);
        contentValues.put("cpassword",cpassword);
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkUsername(String eMail) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where eMail = ?", new String[] {eMail});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String eMail,String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where eMail = ? and password = ?", new String[] {eMail,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
