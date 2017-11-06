package com.weebly.sarikcyber.recylerview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    EditText edtitle,edlocation,eddivision,edgender,eddate,eddetails;
    Button submit;

    DatePickerDialog datePickerdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_add);

        edtitle=(EditText)findViewById(R.id.edtitle);
        edlocation=(EditText)findViewById(R.id.edlocation);
        eddivision=(EditText)findViewById(R.id.eddivision);
        edgender=(EditText)findViewById(R.id.edgender);
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


                    String PostURL = "https://cybersarik.000webhostapp.com/tolet/postadd.php";


                    StringRequest sr = new StringRequest(Request.Method.POST, PostURL, new Response.Listener<String>() {
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

                            datasent.put("utitle", edtitle.getText().toString().trim());
                            datasent.put("udivision", eddivision.getText().toString().trim());
                            datasent.put("ulocation", edlocation.getText().toString().trim());
                            datasent.put("ugender", edgender.getText().toString().trim());
                            datasent.put("udate", eddate.getText().toString().trim());
                            datasent.put("udetails", eddetails.getText().toString().trim());

                            return datasent;
                        }
                    };

                AppController.getInstance().addToRequestQueue(sr);
                Toast.makeText(PostingAdd.this, "Data Is Posted", Toast.LENGTH_SHORT).show();
                clearFeild();
                Intent i = new Intent(PostingAdd.this,MainActivity.class);
                Toast.makeText(PostingAdd.this, "Redirect", Toast.LENGTH_SHORT).show();

                startActivity(i);
                finish();


            }

        });

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
