package com.example.booking;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelpaer extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Roombook.db";
    public static final String TABLE_NAME = "Roombook_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "CustomerId";
    public static final String COL_3 = "HotelName";
    public static final String COL_4 = "NombofGues";
    public static final String COL_5 = "Date";


    public DatabaseHelpaer(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table" + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,CustomerId TEXT,HotelName TEXT,NombofGues TEXT, Data TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String customerid, String hotelname, String nombogues, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, customerid);
        contentValues.put(COL_3, hotelname);
        contentValues.put(COL_4, nombogues);
        contentValues.put(COL_5, date);
       long result= db.insert(TABLE_NAME, null, contentValues);
       if(result == -1)
           return false;
       else
           return true;
    }
     public Cursor getAllData() {
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor res =  db.rawQuery("select * from "+ TABLE_NAME,null);
         return res;
     }
     public boolean updateData(String id,String customerid, String hotelname, String nombogues, String date){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(COL_1, id);
         contentValues.put(COL_2, customerid);
         contentValues.put(COL_3, hotelname);
         contentValues.put(COL_4, nombogues);
         contentValues.put(COL_5, date);
         db.update(TABLE_NAME,contentValues, "id= ?", new String[] {id} );
         return true;
     }
     public Integer deleteData (String id) {
         SQLiteDatabase db = this.getWritableDatabase();
         return db.delete(TABLE_NAME,"ID= ?",new String[] {id});
     }
}

