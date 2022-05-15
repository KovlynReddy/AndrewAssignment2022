package com.example.mobileapplicationdeveloper700;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderHelper extends SQLiteOpenHelper {

    public OrderHelper(Context context){
        super(context,"BaseOrder.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table BaseOrder(OrderId TEXT primary key, ItemId TEXT , Rating TEXT , Type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists BaseOrder");
    }

    public Boolean addAddress(String OrderId,String ItemId , int Rating , String Type){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("OrderId",OrderId);
        contentValues.put("ItemId",ItemId);
        contentValues.put("Rating",Rating);
        contentValues.put("Type",Type);
        long result = Db.insert("BaseOrder",null,contentValues);
        if(result==-1){
            return false;
        } else {
            return true;
        }
    }
}
