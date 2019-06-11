package com.example.com.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseSQLite extends SQLiteOpenHelper  {

    public static final String DATABASE_NAME = "Grouplist.db";

    public static final int DATABASE_VERSION = 2;

    public DataBaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                GroupContantDB.GroupEntry.TABLE_NAME + " (" +
                 GroupContantDB.GroupEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                 GroupContantDB.GroupEntry.COLUMN_NAME + " TEXT NOT NULL,"+
                GroupContantDB.GroupEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GroupContantDB.GroupEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

}
