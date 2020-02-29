package com.example.notemanager;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class dbManager extends SQLiteOpenHelper {
    public static final String dbName = "noteList.db";
    public static final String tableName = "Note";
    public static final String txtCategory = "TEXT_IN";

    public dbManager(Context context) {
        super(context, dbName, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TEXT_IN varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public boolean createNote(String str) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(txtCategory, str);
        long result = db.insert(tableName, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + tableName, null);
        return result;
    }
}
