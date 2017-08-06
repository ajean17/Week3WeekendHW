package com.example.alvin.w3_hw_01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alvin.w3_hw_01.Database.DBHelper;
import com.example.alvin.w3_hw_01.Model.Result;
import com.example.alvin.w3_hw_01.Util.RandomParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.example.alvin.w3_hw_01.Database.FeedReaderContract.FeedEntry;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String BASE_URL = "https://randomuser.me/api";
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    public static final String MAIN_ACTIVITY_EXTRA = "com.example.alvin.w3_hw_01.MAIN_ACTIVITY_EXTRA";
    private static final String MESSAGE_EXTRA = "com.example.alvin.w3_hw_01.MESSAGE_EXTRA";


    private DBHelper helper;
    private SQLiteDatabase database;
    private List<Result> results;
    OkHttpClient client;
    Result nowResult;
    TextView tv_Name;
    TextView tv_Locate;
    TextView tv_Email;
    ImageView iv_Pic;
    EditText et_SearchIn;
    Button saveButton;
    Button readAllButton;
    TextView tvShowAll;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv_Name.setText(getString(R.string.lbl_full_name) + nowResult.getLogin().getUsername());
            tv_Email.setText(getString(R.string.lbl_email) + nowResult.getEmail());
            tv_Locate.setText(getString(R.string.lbl_address) + nowResult.getLocation().toString());
            loadImageView(nowResult.getPicture().getThumbnail());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient.Builder().build();
        results = new ArrayList<>();
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        et_SearchIn = (EditText) findViewById(R.id.et_searchInput);
        iv_Pic = (ImageView) findViewById(R.id.iv_userPic);
        tv_Email = (TextView) findViewById(R.id.tv_userEmail);
        tv_Locate = (TextView) findViewById(R.id.tv_userLocation);
        tv_Name = (TextView) findViewById(R.id.tv_userName);
        tvShowAll = (TextView) findViewById(R.id.tv_showAllUsers);
        saveButton = (Button) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(this);
        readAllButton = (Button) findViewById(R.id.btn_showAll);
        readAllButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Request request = new Request.Builder().url(BASE_URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        results.clear();
                        JSONObject apiRes = new JSONObject(response.body().string());

                        for (int i = 0; i < apiRes.getJSONArray("results").length(); i++) {
                            results.add(RandomParser.parseResult(apiRes.getJSONArray("results").getJSONObject(i)));
                        }

                        for (Result result : results) {
                            Log.d(TAG, "onResponse: " + result.getName().getFirst() + " Image: " + result.getPicture().getThumbnail());
                            nowResult = result;
                        }

                    } catch (JSONException e) {
                        Log.e(TAG, "onResponse: ", e);
                    }
                    final String message = "Assigning random user values";
                    Message msg = handler.obtainMessage();
                    Bundle data = new Bundle();
                    data.putString(MESSAGE_EXTRA, message);
                    msg.setData(data);
                    handler.sendMessage(msg);
                } else {
                    Log.e(TAG, "onResponse: Application error");
                }
            }
        });
    }

    public void saveRecord() {
        Log.d(TAG, "saveRecord: is working");
        String name = nowResult.getName().toString();
        String gender = nowResult.getGender();
        String location = nowResult.getLocation().toString();
        String email = nowResult.getEmail();
        String login = nowResult.getLogin().getUsername();
        String dob = nowResult.getDob();
        String register = nowResult.getRegistered();
        String phone = nowResult.getPhone();
        String cell = nowResult.getCell();
        String id = nowResult.getId().getName();
        String pic = nowResult.getPicture().getThumbnail();
        String nat = nowResult.getNat();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_GENDER, gender);
        values.put(FeedEntry.COLUMN_NAME_NAME, name);
        values.put(FeedEntry.COLUMN_NAME_LOCATION, location);
        values.put(FeedEntry.COLUMN_NAME_EMAIL, email);
        values.put(FeedEntry.COLUMN_NAME_LOGIN, login);
        values.put(FeedEntry.COLUMN_NAME_DOB, dob);
        values.put(FeedEntry.COLUMN_NAME_REGISTERED, register);
        values.put(FeedEntry.COLUMN_NAME_PHONE, phone);
        values.put(FeedEntry.COLUMN_NAME_CELL, cell);
        values.put(FeedEntry.COLUMN_NAME_ID, id);
        values.put(FeedEntry.COLUMN_NAME_PICTURE, pic);
        values.put(FeedEntry.COLUMN_NAME_NAT, nat);

        long recordID = database.insert(
                FeedEntry.TABLE_NAME,
                null,
                values
        );
        String saveResult;
        if(recordID > 0) {
            saveResult = "\nUser saved.";
            Toast.makeText(this, saveResult, Toast.LENGTH_SHORT).show();
            Log.d(TAG, saveResult);
        } else {
            saveResult = "\nUser not saved.";
            Toast.makeText(this, saveResult, Toast.LENGTH_SHORT).show();
            Log.d(TAG, saveResult);
        }
    }

    private void readAllRecord() {
        tvShowAll.setText(R.string.lbl_all_saved_users);
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_LOGIN
        };
        Cursor cursor = database.query( //**Requires 7 parameters**
                FeedEntry.TABLE_NAME,   //Table
                projection,             //Projection
                null,              //Selection (WHERE)
                null,          //Values for selection
                null,                   //Group by
                null,                   //Filters
                null                    //Sort Order
        );

        while(cursor.moveToNext()) {
            long entryID = cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID));
            String login = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_LOGIN));
            tvShowAll.append(String.format(getString(R.string.lbl_result), "\n" + entryID + ": " + login));
        }
    }

    public void toSearchActivity(View view) {
        String name = et_SearchIn.getText().toString();
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra(MAIN_ACTIVITY_EXTRA, name);
        startActivity(intent);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_save:
                saveRecord();
                break;
            case R.id.btn_showAll:
                readAllRecord();
                break;
        }
    }

    private void loadImageView(String urlSource) {
        Picasso.with(this).load(urlSource).resize(200, 200).into(iv_Pic);
    }
}
