package com.weebly.sarikcyber.recylerview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PostingAdd extends AppCompatActivity {

    EditText edtitle,edlocation,eddivision,edgender,eddate,eddetails,edrent;
    Button submit;
    CheckBox checkBox;

    Toolbar toolbar;

    DatePickerDialog datePickerdialog;
    String ptitle= "";String plocation= "";String pdivision= "";String pgender= "";String pdate= "";String prent= "";String pdetails="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_add);

        checkBox = (CheckBox)findViewById(R.id.ceckbox);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtitle=(EditText)findViewById(R.id.edtitle);
        edlocation=(EditText)findViewById(R.id.edlocation);
        eddivision=(EditText)findViewById(R.id.eddivision);
        edgender=(EditText)findViewById(R.id.edgender);
        edgender=(EditText)findViewById(R.id.edgender);
        edrent = (EditText) findViewById(R.id.edrent);
        eddate=(EditText)findViewById(R.id.eddate);
        eddetails=(EditText)findViewById(R.id.eddetails);



        ///DatePicker
        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int date = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.DAY_OF_MONTH);

                 datePickerdialog= new DatePickerDialog(PostingAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        eddate.setText(year+"-"+(month+1)+"-"+dayOfMonth);

                    }
                },date,month,year);

                datePickerdialog.show();
            }
        });

        submit=(Button)findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean error = false;

                ptitle = edtitle.getText().toString().trim();
                pdivision = eddivision.getText().toString().trim();
                plocation = edlocation.getText().toString().trim();
                pgender = edgender.getText().toString().trim();
                pdate = eddate.getText().toString().trim();
                pdetails = eddetails.getText().toString().trim();

                if (ptitle.isEmpty() && pdivision.isEmpty() && plocation.isEmpty() && pgender.isEmpty() && pdate.isEmpty() && pdetails.isEmpty()){

                    error = true;

                    edtitle.setError("Title is required !!");
                    eddivision.setError("Division is required !!");
                    edlocation.setError("Location is required !!");
                    edgender.setError("Gender is required !!");
                    eddate.setError("Date of Rent is required !!");
                    eddetails.setError("Details is required !!");

                }

                 String rentCon = edrent.getText().toString().trim();

                if (rentCon.isEmpty() && checkBox.isChecked()) {
                    prent = "Negotiable";
                } else if (!rentCon.isEmpty() && !checkBox.isChecked()) {
                    prent = rentCon;
                } else {
                    error = true;
                    edrent.setError("Please Enter Rent Fee or Checked The box");
                }


                if (error) {

                } else {
                    clearFeild();
                    sendDataToServer();
                  }
            }

        });

    }

    private void sendDataToServer() {


        Intent i = new Intent(PostingAdd.this, ContactDetails.class);
        i.putExtra("title",ptitle);
        i.putExtra("division",pdivision);
        i.putExtra("location",plocation);
        i.putExtra("gender",pgender);
        i.putExtra("date",pdate);
        i.putExtra("details",pdetails);
        i.putExtra("rent",prent);
        startActivity(i);
        finish();


    }


    private void clearFeild() {
        edtitle.setText("");
        edlocation.setText("");
        eddivision.setText("");
        edgender.setText("");
        eddate.setText("");
        eddetails.setText("");
    }
}
