package com.weebly.sarikcyber.recylerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        post=(Button) findViewById(R.id.postadd);
        userAddArrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listview);

        adapter = new Custom_adapter(MainActivity.this, userAddArrayList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent data = new Intent(MainActivity.this,DataDetailsActivity.class);

                data.putExtra("title", userAddArrayList.get(position).getTitle());
                data.putExtra("division", userAddArrayList.get(position).getDivisions());
                data.putExtra("location", userAddArrayList.get(position).getLocation());
                data.putExtra("gender", userAddArrayList.get(position).getGender());
                data.putExtra("date", userAddArrayList.get(position).getDateofrent());
                data.putExtra("details", userAddArrayList.get(position).getDetails());
                startActivity(data);

            }
        });

        fetchingdata();
    }

    private void fetchingdata() {

        String myURL = "https://cybersarik.000webhostapp.com/tolet/gettingnews.php";

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
           for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = (JSONObject)response.get(i);
                        UserAdd add = new UserAdd();

                         add.setId(jsonObject.getInt("id"));
                         add.setTitle(jsonObject.getString("title"));
                         add.setDivisions(jsonObject.getString("division"));
                         add.setLocation(jsonObject.getString("location"));
                         add.setGender(jsonObject.getString("gender"));
                         add.setDateofrent(jsonObject.getString("dateofrent"));
                         add.setDetails(jsonObject.getString("details"));

                        userAddArrayList.add(add);

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

        Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
  }


    public void postingAdd(View view) {

        Intent i = new Intent(MainActivity.this,PostingAdd.class);
        startActivity(i);
    }
}
