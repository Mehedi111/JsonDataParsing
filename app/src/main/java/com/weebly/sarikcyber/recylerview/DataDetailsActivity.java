package com.weebly.sarikcyber.recylerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataDetailsActivity extends AppCompatActivity {
    TextView title, division, location, gender, date, details, rent;
    Toolbar toolbar;
    TextView tvname, tvphoneNoOne, tvphoneNoTwo, tvemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.title);
        division = (TextView) findViewById(R.id.division);
        location = (TextView) findViewById(R.id.location);
        gender = (TextView) findViewById(R.id.gender);
        rent = (TextView) findViewById(R.id.rentfee);
        date = (TextView) findViewById(R.id.date);
        details = (TextView) findViewById(R.id.details);


        tvname = (TextView) findViewById(R.id.name);
        tvphoneNoOne = (TextView) findViewById(R.id.phonenoone);
        tvphoneNoTwo = (TextView) findViewById(R.id.phonenotwo);
        tvemail = (TextView) findViewById(R.id.email);


        title.setText(getIntent().getStringExtra("title"));
        division.setText(getIntent().getStringExtra("division"));
        location.setText(getIntent().getStringExtra("location"));
        gender.setText(getIntent().getStringExtra("gender"));
        rent.setText(getIntent().getStringExtra("rent"));
        date.setText(getIntent().getStringExtra("date"));
        details.setText(getIntent().getStringExtra("details"));
        tvname.setText(getIntent().getStringExtra("name"));
        tvphoneNoOne.setText(getIntent().getStringExtra("phoneNoOne"));
        tvphoneNoTwo.setText(getIntent().getStringExtra("phoneNoTwo"));
        tvemail.setText(getIntent().getStringExtra("email"));



    }

}