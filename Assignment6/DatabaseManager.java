package com.example.filteringspendingmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "spendManager.db";
    public static final String TABLE_NAME = "SpendingTable";
    public static final String COL_NAME_ENUMERATION = "Count";
    public static final String COL_NAME_Category = "Category";
    public static final String COL_NAME_DATE = "Date";
    public static final String COL_NAME_Amount = "Amount";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    public void onCreate(SQLiteDatabase sqlDB) {
        sqlDB.execSQL("CREATE TABLE " + TABLE_NAME + " (Count INTEGER PRIMARY KEY AUTOINCREMENT, Category varchar(100), Date varchar(100), Amount DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int older, int newer) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqlDB);
    }


    public boolean createHistory(TableModel mTable) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME_Category, mTable.mCategory);
        contentValues.put(COL_NAME_DATE, mTable.mDate);
        contentValues.put(COL_NAME_Amount, mTable.mAmount);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Cursor pullData() {
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor res = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public Cursor returnFilter(String initAmount, String finAmount, String initDate, String finalDate) {
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor result = null;
        Double initialAmount = null;
        Double finalAmount = null;

        if (!initAmount.isEmpty()) {
            initialAmount = Double.parseDouble(initAmount);
        }
        if (!finAmount.isEmpty()) {
            finalAmount = Double.parseDouble(finAmount);
        }
        if (initialAmount != null && finalAmount == null && initDate.isEmpty() && finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount, null);
        }
        else if (initialAmount == null && finalAmount != null && initDate.isEmpty() && finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount <= " + finalAmount, null);
        }
        else if (initialAmount == null && finalAmount == null && !initDate.isEmpty() && finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Date >= '" + initDate + "'", null);
        }
        else if (initialAmount == null && finalAmount == null && initDate.isEmpty() && !finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Date <= '" + finalDate + "'", null);
        }
        else if (initialAmount != null && finalAmount != null && initDate.isEmpty() && finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount + " AND Amount <= " + finalAmount, null);
        }
        else if (initialAmount == null && finalAmount == null && !initDate.isEmpty() && !finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Date >= '" + initDate + "' AND Date <= '" + finalDate + "'", null);
        }
        else if (initialAmount != null && finalAmount == null && !initDate.isEmpty() && finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount + " AND Date >= '" + initDate + "'", null);
        }
        else if (initialAmount != null && finalAmount == null && initDate.isEmpty() && !finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount + " AND Date <= '" + finalDate + "'", null);
        }
        else if (initialAmount == null && finalAmount != null && !initDate.isEmpty() && finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount <= " + finalAmount + " AND Date >= '" + initDate + "'", null);
        }
        else if (initialAmount == null && finalAmount != null && initDate.isEmpty() &&!finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount <= " + finalAmount + " AND Date <= '" + finalDate + "'", null);
        }
        else if (initialAmount != null && finalAmount != null && !initDate.isEmpty() && finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount + " AND Amount <= " +
                    finalAmount + " AND Date >= '" + initDate + "'", null);
        }
        else if (initialAmount != null && finalAmount != null && initDate.isEmpty() && !finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount + " AND Amount <= " +
                    finalAmount + " AND Date <= '" + finalDate + "'", null);
        }
        else if (initialAmount == null && finalAmount != null && !initDate.isEmpty() && !finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount <= " + finalAmount + " AND Date >= '" +
                    initDate + "' AND Date <= '" + finalDate + "'", null);
        }
        else if (initialAmount != null && finalAmount == null && !initDate.isEmpty() && !finalDate.isEmpty()) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount + " AND Date >= '" +
                    initDate + "' AND Date <= '" + finalDate +"'", null);
        }
        else if (initialAmount != null && finalAmount != null && initDate != null && finalDate != null) {
            result = sqlDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Amount >= " + initialAmount + " AND Amount <= " + finalAmount + " AND Date >= '" +
                    initDate + "' AND Date <= '" + finalDate + "'", null);
        }
        return result;
    }
}
