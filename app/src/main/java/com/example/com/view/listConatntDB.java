package com.example.com.view;

import android.provider.BaseColumns;

public class listConatntDB {

    public listConatntDB() {
    }

    public static final class ListEntry implements BaseColumns {
        public static final String TABLE_NAME = "myList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_Quantity = "Quantity";
        public static final String COLUMN_sold = "sold";
        public static final String COLUMN_priceInsid = "priceInsid";
        public static final String COLUMN_priceOutsid = "priceOutsid";
        public static final String COLUMN_group="Typegroup";

    }

}
