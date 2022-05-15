package com.example.mobileapplicationdeveloper700;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BussinessHelper extends SQLiteOpenHelper {

    public BussinessHelper(Context context){
        super(context,"BaseLocation.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table BaseLocation(LocationId TEXT primary key, AddressId TEXT , AverageRating TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists BaseLocation");
    }

    public Boolean addBussiness(String locationId , int addressId , String averageRating){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LocationId",locationId);
        contentValues.put("AddressId",addressId);
        contentValues.put("AverageRating",averageRating);
        long result = Db.insert("BaseLocation",null,contentValues);
        if(result==-1){
            return false;
        } else {
            return true;
        }
    }
}
