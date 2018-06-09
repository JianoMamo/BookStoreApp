package com.example.android.bookstoreapp.BookStoreData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.bookstoreapp.BookStoreData.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {

    /**
     * Database Version.
     */
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "bookstore.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_BOOKS_TABLE =
            "CREATE TABLE " + BookEntry.TABLE_NAME + " ("
            + BookEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BookEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP
            + BookEntry.COLUMN_PRICE + INTEGER_TYPE + COMMA_SEP
            + BookEntry.COLUMN_QUANTITY + INTEGER_TYPE + COMMA_SEP
            + BookEntry.COLUMN_SUPPLIER_NAME + TEXT_TYPE + COMMA_SEP
            + BookEntry.COLUMN_SUPPLIER_PHONE + TEXT_TYPE + ")";

    private static final String SQL_DELETE_BOOKS_TABLE =
            "DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME;

    public BookDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_BOOKS_TABLE);
            onCreate(db);
    }

}
