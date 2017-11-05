package com.weebly.sarikcyber.recylerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DataDetailsActivity extends AppCompatActivity {
    TextView title,division,location,gender,date,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_details);

        title= (TextView)findViewById(R.id.title);
        division= (TextView)findViewById(R.id.division);
        location= (TextView)findViewById(R.id.location);
        gender= (TextView)findViewById(R.id.gender);
        date= (TextView)findViewById(R.id.date);
        details= (TextView)findViewById(R.id.details);


        title.setText(getIntent().getStringExtra("title"));
        division.setText(getIntent().getStringExtra("division"));
        location.setText(getIntent().getStringExtra("location"));
        gender.setText(getIntent().getStringExtra("gender"));
        date.setText(getIntent().getStringExtra("date"));
        details.setText(getIntent().getStringExtra("details"));





    }
}
