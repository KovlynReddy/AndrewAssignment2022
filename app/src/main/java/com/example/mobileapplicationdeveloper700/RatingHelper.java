package com.example.mobileapplicationdeveloper700;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class RatingHelper extends SQLiteOpenHelper {

    public RatingHelper(Context context){
        super(context,"BaseRating.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table BaseRating(RatingId TEXT primary key, rating TEXT , LocationId TEXT , ItemId TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists BaseRating");
    }

    public Boolean rate(String rating , int value , String location , String itemId){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RatingId",rating);
        contentValues.put("Rating",value);
        contentValues.put("ItemId",itemId);
        contentValues.put("LoactionId",location);
        long result = Db.insert("BaseRating",null,contentValues);
        if(result==-1){
            return false;
        } else {
            return true;
        }
    }

    public RatingModel GetRating(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from BaseRating.db Where LocationId ='"+id+"' Or itemId = '"+id+"';";
        Cursor cursor = db.rawQuery(query,null);

        RatingModel model = new RatingModel();
        model.RatingId = cursor.getString(0);
        model.rating = cursor.getString(1);
        model.location = cursor.getString(2);
        model.item = cursor.getString(3);

        return model;

    }

    public ArrayList<RatingModel> GetRatingByItem(String itemId){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from BaseRating.db Where ItemId ='"+itemId+"'";
        Cursor cursor = db.rawQuery(query,null);


        ArrayList<RatingModel> model = new ArrayList<RatingModel>();

        for (int col = 0;col <cursor.getColumnCount();col++) {
            cursor.moveToNext();
            RatingModel buff = new RatingModel();
            buff.RatingId = cursor.getString(0);
            buff.rating = cursor.getString(1);
            buff.location = cursor.getString(2);
            buff.item = cursor.getString(3);

            model.add(buff);
        }


        return model;

    }

    public ArrayList<RatingModel> GetRatingByLocation(String LocationId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from BaseRating.db Where LocationId ='"+LocationId+"';";
        Cursor cursor = db.rawQuery(query,null);

        ArrayList<RatingModel> model = new ArrayList<RatingModel>();

        for (int col = 0;col <cursor.getColumnCount();col++) {
            cursor.moveToNext();
            RatingModel buff = new RatingModel();
            buff.RatingId = cursor.getString(0);
            buff.rating = cursor.getString(1);
            buff.location = cursor.getString(2);
            buff.item = cursor.getString(3);

            model.add(buff);
        }


        return model;


    }

    public int GetRatingValue(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from BaseRating.db Where RatingId ='"+id+"';";
        Cursor cursor = db.rawQuery(query,null);

        return Integer.parseInt(cursor.getString(1));


    }


}
