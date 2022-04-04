package com.example.androidstuidolab_07.lab7b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.androidstuidolab_07.lab7a.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandle extends SQLiteOpenHelper {

    private static final String databaseName = "QLPLACE";
    private static final int databaseVersion = 1;
    private static final String tableName = "places";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandle(Context context){
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)", tableName, KEY_ID, KEY_NAME);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }

    public void addPlace(Place p){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.getPlaceName());

        sqLiteDatabase.insert(tableName, null, values);
        sqLiteDatabase.close();
    }

    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        String query = "SELECT * FROM "+ tableName;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null); //Tra truy van vao 1 cursor
        cursor.moveToFirst(); //chuyen con tro cursor len dau tien

        while(cursor.isAfterLast() == false){ //Lap voi dieu kien: contro nam cuoi cung == false. KHi cham den true thi dung
            Place place = new Place(cursor.getInt(0), cursor.getString(1));
            places.add(place);
            cursor.moveToNext();
        }
        return places;
    }
}
