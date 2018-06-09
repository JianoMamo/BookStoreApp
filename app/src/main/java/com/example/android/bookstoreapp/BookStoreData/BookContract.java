package com.example.android.bookstoreapp.BookStoreData;

import android.provider.BaseColumns;

public final class BookContract {

    private BookContract() {
    }

    public static final class BookEntry implements BaseColumns {

        public static final String TABLE_NAME = "books";

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplierName";
        public static final String COLUMN_SUPPLIER_PHONE = "supplierPhone";
    }
}
