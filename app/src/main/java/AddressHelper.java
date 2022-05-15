package com.example.mobileapplicationdeveloper700;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddressHelper extends SQLiteOpenHelper {

    public AddressHelper(Context context){
        super(context,"BaseAddress.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table BaseAddress(AddressId TEXT primary key, StreetAddress TEXT , City TEXT , State TEXT , ZipCode TEXT , locationId TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists BaseAddress");
    }

    public Boolean addAddress(String AddressId,String street , int city , String state, int ZipCode , String locationId){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AddressId",AddressId);
        contentValues.put("StreetAddress",street);
        contentValues.put("City",city);
        contentValues.put("State",state);
        contentValues.put("ZipCode",ZipCode);
        contentValues.put("locationId",locationId);
        long result = Db.insert("BaseAddress",null,contentValues);
        if(result==-1){
            return false;
        } else {
            return true;
        }
    }
}
