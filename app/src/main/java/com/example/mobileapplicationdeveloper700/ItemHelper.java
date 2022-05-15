package com.example.mobileapplicationdeveloper700;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemHelper extends SQLiteOpenHelper {

    public ItemHelper(Context context){
        super(context,"BaseItem.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table BaseItem(ItemId TEXT primary key, ItemName TEXT , AverageRating TEXT,LocationId TEXT , Type TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists BaseItem");
    }

    public Boolean addItem(String itemId,String name , int rating ,String location, String type){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ItemId",itemId);
        contentValues.put("ItemName",name);
        contentValues.put("AverageRating",rating);
        contentValues.put("LocationId",location);
        contentValues.put("type",type);
        long result = Db.insert("BaseItem",null,contentValues);
        if(result==-1){
            return false;
        } else {
            return true;
        }
    }
}
