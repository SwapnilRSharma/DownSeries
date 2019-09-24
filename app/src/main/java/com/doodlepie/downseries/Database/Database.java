package com.doodlepie.downseries.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "DownSeries.db";
    private static final int DB_VERSION = 1;

    public  SQLiteDatabase db;
    public SQLiteAssetHelper sqLiteAssetHelper;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    public Database open() throws SQLException{
        db = sqLiteAssetHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }




    //Favorites

    public void addToFavorites(String SeriesId){
        db = sqLiteAssetHelper.getWritableDatabase();
        String query = String.format("INSERT INTO Favorites(SeriesId) VALUES('%s');", SeriesId);
        db.execSQL(query);
    }

    public void removeFromFavorites(String SeriesId){
        db = sqLiteAssetHelper.getWritableDatabase();
        String query = String.format("DELETE FROM Favorites WHERE SeriesId='%s';", SeriesId);
        db.execSQL(query);
    }

    public boolean isFavorite(String SeriesId) {
        if (db == null) {
            return false;
        } else {
            db = sqLiteAssetHelper.getReadableDatabase();
            String query = String.format("SELECT * FROM Favorites WHERE SeriesId='%s';", SeriesId);
            db.execSQL(query);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() <= 0) {
                cursor.close();
                return false;
            }
            cursor.close();
            return true;
        }
    }
}
