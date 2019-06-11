package com.example.com.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatdBasaSQlist extends SQLiteOpenHelper {

    public static final String DATABASE_NAME2 = "mylist.db";

    public static final int DATABASE_VERSION = 2;
    public DatdBasaSQlist(Context context) {
        super(context, DATABASE_NAME2, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_GROCERYLIST_TABLE2 = "CREATE TABLE " +
                listConatntDB.ListEntry.TABLE_NAME + " (" +
                listConatntDB.ListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                listConatntDB.ListEntry.COLUMN_NAME + " TEXT NOT NULL,"+
                listConatntDB.ListEntry.COLUMN_Quantity + " INTEGER," +
                listConatntDB.ListEntry.COLUMN_sold + " INTEGER," +
                listConatntDB.ListEntry.COLUMN_priceInsid + " INTEGER," +
                listConatntDB.ListEntry.COLUMN_priceOutsid + " INTEGER," +
                listConatntDB.ListEntry.COLUMN_group + " TEXT,"+
                listConatntDB.ListEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + listConatntDB.ListEntry .TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
