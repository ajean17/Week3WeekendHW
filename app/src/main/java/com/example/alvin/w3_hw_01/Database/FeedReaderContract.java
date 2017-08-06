package com.example.alvin.w3_hw_01.Database;

import android.provider.BaseColumns;

/**
 * Created by Alvin on 8/5/2017.
 */

public class FeedReaderContract {
    private FeedReaderContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "randomUser";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_LOGIN = "login";
        public static final String COLUMN_NAME_DOB = "dob";
        public static final String COLUMN_NAME_REGISTERED = "registered";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_CELL = "cell";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_PICTURE = "picture";
        public static final String COLUMN_NAME_NAT = "nat";
    }
}
