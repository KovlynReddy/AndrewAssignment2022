import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public List<RatingModel> GetRatingByItem(String itemId){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from BaseRating.db Where ItemId ='"+id+"'";
        Cursor cursor = db.rawQuery(query,null);

        RatingModel model = new RatingModel();
        model.RatingId = cursor.getString(0);
        model.rating = cursor.getString(1);
        model.location = cursor.getString(2);
        model.item = cursor.getString(3);

        return model;

    }

    public List<RatingModel> GetRatingByLocation(String LocationId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from BaseRating.db Where LocationId ='"+id+"';";
        Cursor cursor = db.rawQuery(query,null);

        RatingModel model = new RatingModel();
        model.RatingId = cursor.getString(0);
        model.rating = cursor.getString(1);
        model.location = cursor.getString(2);
        model.item = cursor.getString(3);

        return model;


    }

    public int GetRatingValue(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from BaseRating.db Where RatingId ='"+id+"';";
        Cursor cursor = db.rawQuery(query,null);

        return InternalError.parseInt(cursor.getString(1));


    }


}
