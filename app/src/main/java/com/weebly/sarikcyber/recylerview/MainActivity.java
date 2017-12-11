package com.weebly.sarikcyber.recylerview;

import android.Manifest;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<UserAdd> userAddArrayList;
    ListView lv;
    Custom_adapter adapter;
    Button post;

    Toolbar toolbar;

    SearchView sv;

    FloatingActionButton fab;

    Button postAdd;

    ProgressDialog progressDialog;

    View.OnClickListener onClickListener;

    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        post = (Button) findViewById(R.id.postadd);
        userAddArrayList = new ArrayList<>();


        lv = (ListView) findViewById(R.id.listview);
        sv = (SearchView) findViewById(R.id.search);

        adapter = new Custom_adapter(MainActivity.this, userAddArrayList);
        lv.setAdapter(adapter);

        postAdd = (Button) findViewById(R.id.btnpostAdd);


        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

            }
        };

        if (isOnline()){
            progressDialog.show();
            fetchingdata();
        } else
        {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "NO INTERNET CONNECTION", Snackbar.LENGTH_LONG)
                    .setAction("Try Again", onClickListener);
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();


        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PostingAdd.class);
                startActivity(i);
            }
        });




        postAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PostingAdd.class);
                startActivity(i);
            }
        });



        ///SEARCH_VIEW
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        ///ITEM CLICK
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("To-Let Details");

                dialog.setMessage("\nFor :" + userAddArrayList.get(position).getGender() +
                        "\nRent : " + userAddArrayList.get(position).getRent() + "\nRent From: " + userAddArrayList.get(position).getDateofrent()
                        + "\n\nDetails\n\n" + userAddArrayList.get(position).getDetails()
                        + "\n\nContact Details: \n\n" + userAddArrayList.get(position).getName() + "\n\nPhoneNumber:\n\n" + userAddArrayList.get(position).getPhoneNoOne()
                        + "\n" + userAddArrayList.get(position).getPhonenotwo() + "\n\nEmail :\n\n" + userAddArrayList.get(position).getEmail());

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("CALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + userAddArrayList.get(position).getPhoneNoOne()));

                        startActivity(callIntent);
                    }
                });
                

                dialog.show();
            }
        });


    }

    private void fetchingdata() {


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URLofApi.GETPOSTADD, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressDialog.dismiss();

            for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = (JSONObject)response.get(i);
                        UserAdd add = new UserAdd();

                         add.setId(jsonObject.getInt("id"));
                         add.setTitle(jsonObject.getString("title"));
                         add.setDivisions(jsonObject.getString("division"));
                         add.setLocation(jsonObject.getString("location"));
                         add.setGender(jsonObject.getString("gender"));
                         add.setRent(jsonObject.getString("rent"));
                         add.setDateofrent(jsonObject.getString("dateofrent"));
                         add.setDetails(jsonObject.getString("details"));

                        add.setName(jsonObject.getString("name"));
                        add.setPhoneNoOne(jsonObject.getString("phoneNoOne"));
                        add.setPhonenotwo(jsonObject.getString("phoneNoTwo"));
                        add.setEmail(jsonObject.getString("email"));



                        userAddArrayList.add(0,add);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Volley error", error);
            }
        });

        com.weebly.sarikcyber.recylerview.AppController.getInstance().addToRequestQueue(jsonArrayRequest);


  }

    /*

    @Override

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.postadd){
           Intent i = new Intent(MainActivity.this, PostingAdd.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

*/


    public boolean isOnline(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
          if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting()){

              return true;
          }else {

              return false;
          }

    }
}
