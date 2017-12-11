package com.weebly.sarikcyber.recylerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ContactDetails extends AppCompatActivity {

    Toolbar toolbar;
    String ptitle= "";String plocation= "";String pdivision= "";
    String pgender= "";String pdate= "";
    String prent= "";String pdetails="";

    EditText edName, edPhoneNoOne, edPhoneNumberTwo, edEmail;
    String name = "";
    String phoneNoOne="";
    String phoneNoTwo="None";
    String email="None";
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        edName = (EditText) findViewById(R.id.edName);
        edPhoneNoOne = (EditText) findViewById(R.id.edPhoneNoOne);
        edPhoneNumberTwo = (EditText) findViewById(R.id.edPhoneNoTwo);
        edEmail = (EditText) findViewById(R.id.edEmail);

        submitButton = (Button) findViewById(R.id.btnSubmit);

        ///getting data from postingadd
        ptitle= getIntent().getStringExtra("title");
        pdivision= getIntent().getStringExtra("division");
        plocation= getIntent().getStringExtra("location");
        pgender= getIntent().getStringExtra("gender");
        pdate= getIntent().getStringExtra("date");
        pdetails= getIntent().getStringExtra("details");
        prent= getIntent().getStringExtra("rent");


    }



    private void sendDataToServer() {

        StringRequest sr = new StringRequest(Request.Method.POST, URLofApi.POSTADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("error", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> datasent = new HashMap<String, String>();

                datasent.put("utitle", ptitle);
                datasent.put("udivision", pdivision);
                datasent.put("ulocation", plocation);
                datasent.put("ugender", pgender);
                datasent.put("urent", prent);
                datasent.put("udate", pdate);
                datasent.put("udetails", pdetails);
                datasent.put("uname", name);
                datasent.put("uphoneNoOne", phoneNoOne);
                datasent.put("uphoneNoTwo", phoneNoTwo);
                datasent.put("uemail", email);


                return datasent;
            }
        };

        AppController.getInstance().addToRequestQueue(sr);

        Intent i = new Intent(this, MainActivity.class);
        //Toast.makeText(PostingAdd.this, "Redirect", Toast.LENGTH_SHORT).show();

        startActivity(i);
        finish();


    }


    public void submitButton(View view) {

        boolean error = false;

        name = edName.getText().toString().trim();
        phoneNoOne = edPhoneNoOne.getText().toString().trim();
        phoneNoTwo = edPhoneNumberTwo.getText().toString();
        email = edEmail.getText().toString().trim();

        if (!name.isEmpty() && !phoneNoOne.isEmpty()){

            if ( name.startsWith("(") || name.startsWith(")")|| name.startsWith("<")|| name.startsWith(">")|| name.startsWith("*")
                    || name.startsWith("#")|| name.startsWith("@")|| name.startsWith("$")|| name.startsWith("{")
                    || name.startsWith("}")|| name.startsWith("^")|| name.startsWith("&")|| name.startsWith("-")
                    || name.startsWith("_")|| name.startsWith("%")|| name.startsWith("/")|| name.startsWith("?")
                    || name.startsWith(".")){
                error = true;
                edName.setError("Invalid Name");

            }

            if ( phoneNoOne.startsWith("+880") || phoneNoOne.startsWith("01")){
                if (phoneNoOne.length()==11 || phoneNoOne.length()==14){

                }
            }else {
                error = true;
                edPhoneNoOne.setError("Invalid Phone Number !");
            }
        }else {
            edName.setError(" Name is Required !");
            edPhoneNoOne.setError(" Phone Number is Required !");
        }

        if ( !phoneNoTwo.isEmpty()){
            if ( phoneNoTwo.startsWith("+880") || phoneNoOne.startsWith("01")){
                if (phoneNoTwo.length()==11 || phoneNoOne.length()==14){

                }
            }else {
                error = true;
                edPhoneNumberTwo.setError("Invalid Phone Number !");
            }
        }


        if (!email.isEmpty()){
            if (!email.contains("@") && email.contains(".")){
                error = true;
                edEmail.setError("Invalid Email Address !");
            }
        }


        if (error){

        }else {
            sendDataToServer();
        }


    }
}
