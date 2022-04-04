package com.example.androidstuidolab_07.lab7a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandle extends SQLiteOpenHelper {

    private static final String databaseName = "QLSV";
    private static final int databaseVersion = 1;
    private static final String tableName = "students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandle (Context context){
        super(context, databaseName, null, databaseVersion);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)", tableName, KEY_ID, KEY_NAME);
        db.execSQL(sql);
        Log.d("Create db", "Creating database...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
        db.execSQL(sql);

        onCreate(db);
        Log.d("update db","Updating database...");
    }

    public void addStudent (Student sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sv.getName());

        db.insert(tableName, null, values);
        db.close();
    }

    public Student getStudent(int studentId){
        return null;
    }

    public List<Student> getStudents (){
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM "+ tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null); //Tra truy van vao 1 cursor
        cursor.moveToFirst(); //chuyen con tro cursor len dau tien

        while(cursor.isAfterLast() == false){ //Lap voi dieu kien: contro nam cuoi cung == false. KHi cham den true thi dung
            Student student = new Student(cursor.getInt(0), cursor.getString(1));
            students.add(student);
            cursor.moveToNext();
        }
        return students;
    }
}
