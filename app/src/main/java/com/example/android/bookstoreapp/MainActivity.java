package com.example.android.bookstoreapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.bookstoreapp.BookStoreData.BookContract.BookEntry;
import com.example.android.bookstoreapp.BookStoreData.BookDbHelper;

public class MainActivity extends AppCompatActivity {

    private BookDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new BookDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        insertBook();
        displayBookDataInfo();
    }


    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the books database.
     */
    private void displayBookDataInfo() {

        // Create and/or open a database to read from it.
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_NAME,
                BookEntry.COLUMN_PRICE,
                BookEntry.COLUMN_QUANTITY,
                BookEntry.COLUMN_SUPPLIER_NAME,
                BookEntry.COLUMN_SUPPLIER_PHONE
        };

        Cursor cursor = db.query(
                BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayBookView = findViewById(R.id.text_view_book);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // books table in the database).
            // Creating a header in the TextView that looks like this:
            //
            // The books table contains <number of rows in Cursor> books.
            // Book _id : name - price - quantity - supplier name - supplier phone
            //
            displayBookView.setText("The books table contains " + cursor.getCount() + " books.\n\n");
            displayBookView.append("Book " + BookEntry._ID + " : " + BookEntry.COLUMN_NAME + " - " + BookEntry.COLUMN_PRICE + " - "
                    + BookEntry.COLUMN_QUANTITY + " - " + BookEntry.COLUMN_SUPPLIER_NAME + " - "   + BookEntry.COLUMN_SUPPLIER_PHONE);

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor.
            while (cursor.moveToNext()) {

                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                float currentPrice = cursor.getFloat(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayBookView.append("\n" + currentID + "\t" + currentName + " - " + currentPrice + " - " + currentQuantity
                                            + " - " + currentSupplierName + " - " + currentSupplierPhone + "\n");
            }
        } finally {
            // Closing the cursor.This releases all its resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertBook() {

        SQLiteDatabase bookDb = mDbHelper.getWritableDatabase();

        ContentValues bookValues = new ContentValues();
        bookValues.put(BookEntry.COLUMN_NAME,"Rich Dad, Poor Dad");
        bookValues.put(BookEntry.COLUMN_PRICE,16.95);
        bookValues.put(BookEntry.COLUMN_QUANTITY,2103);
        bookValues.put(BookEntry.COLUMN_SUPPLIER_NAME,"George Hamilton");
        bookValues.put(BookEntry.COLUMN_SUPPLIER_PHONE,"+1 212-608-8073");

        long newRowId = bookDb.insert(BookEntry.TABLE_NAME,null,bookValues);

        Log.v("MainActivity","New Row ID " + newRowId);

    }
}
