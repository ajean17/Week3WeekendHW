package com.example.alvin.w3_hw_01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alvin.w3_hw_01.Database.DBHelper;
import com.example.alvin.w3_hw_01.Database.FeedReaderContract.FeedEntry;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {

    public static final String TAG = SearchActivity.class.getSimpleName() + "_TAG";
    private DBHelper helper;
    private SQLiteDatabase database;
    String findUser;
    TextView tvName;
    TextView tvGender;
    TextView tvEmail;
    TextView tvLogin;
    TextView tvLocation;
    TextView tvPhone;
    TextView tvCell;
    //TextView tvRegister;
    //TextView tvNat;
    //TextView tvID;
    TextView tvDOB;
    ImageView ivPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tvLogin = (TextView) findViewById(R.id.tv_search_login);
        tvName = (TextView) findViewById(R.id.tv_search_name);
        tvEmail = (TextView) findViewById(R.id.tv_search_email);
        tvGender = (TextView) findViewById(R.id.tv_search_gender);
        tvLocation = (TextView) findViewById(R.id.tv_search_location);
        tvDOB = (TextView) findViewById(R.id.tv_search_dob);
        tvPhone = (TextView) findViewById(R.id.tv_search_phone);
        tvCell = (TextView) findViewById(R.id.tv_search_cell);
        //tvRegister = (TextView) findViewById(R.id.tv_search_registered);
        //tvNat = (TextView) findViewById(R.id.tv_search_nat);
        //tvID = (TextView) findViewById(R.id.tv_search_id);
        ivPicture = (ImageView) findViewById(R.id.iv_search_pic);

        helper = new DBHelper(this);
        database = helper.getWritableDatabase();
        Intent intent = getIntent();
        if(intent != null) {
            findUser = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_EXTRA);
        }
        readRecord();
    }

    private void readRecord() {
        Log.d(TAG, "readRecord: " + findUser);
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_GENDER,
                FeedEntry.COLUMN_NAME_LOGIN,
                FeedEntry.COLUMN_NAME_NAME,
                FeedEntry.COLUMN_NAME_LOCATION,
                FeedEntry.COLUMN_NAME_DOB,
                FeedEntry.COLUMN_NAME_EMAIL,
                FeedEntry.COLUMN_NAME_PHONE,
                FeedEntry.COLUMN_NAME_CELL,
                FeedEntry.COLUMN_NAME_NAT,
                FeedEntry.COLUMN_NAME_REGISTERED,
                FeedEntry.COLUMN_NAME_PICTURE,
                FeedEntry.COLUMN_NAME_ID
        };
        String selection = FeedEntry.COLUMN_NAME_LOGIN + " LIKE ?";
        String [] selectionArgs = {
                findUser
        };
        Cursor cursor = database.query( //**Requires 7 parameters**
                FeedEntry.TABLE_NAME,   //Table
                projection,             //Projection
                selection,              //Selection (WHERE)
                selectionArgs,          //Values for selection
                null,                   //Group by
                null,                   //Filters
                null                    //Sort Order
        );

        while(cursor.moveToNext()) {
            long entryID = cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID));
            String login = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_LOGIN));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NAME));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_GENDER));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_LOCATION));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_EMAIL));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_PHONE));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_DOB));
            String cell = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_CELL));
            String picture = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_PICTURE));
            String nat = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NAT));
            String id = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_ID));
            String register = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_REGISTERED));

            tvLogin.setText(getString(R.string.lbl_login_username) + login);
            tvName.setText(getString(R.string.lbl_full_name) + name);
            tvGender.setText(getString(R.string.lbl_gender) + gender);
            tvLocation.setText(getString(R.string.lbl_address) + location);
            tvPhone.setText(getString(R.string.lbl_phone) + phone);
            tvCell.setText(getString(R.string.lbl_cell) + cell);
            tvEmail.setText(getString(R.string.lbl_email) + email);
            tvDOB.setText(getString(R.string.lbl_date_of_birth) + dob);
            //tvNat.append(nat);
            //tvID.append(id);
            //tvRegister.append(register);
            Picasso.with(this).load(picture).resize(200, 200).into(ivPicture);

            Log.d(TAG, name);
        }
    }
}
