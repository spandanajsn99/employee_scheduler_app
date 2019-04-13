package com.example.employee_scheduling_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    public EditText uPW,userID;
    public TextView navigate;
    public Button login;
    public String id;
    public String desg;
    public int x=-1;
    RequestQueue requestQueue;
    String HttpUrl = "http://10.107.39.179/checklogin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uPW=(EditText)findViewById(R.id.uPW);
        userID=(EditText)findViewById(R.id.userID);
        requestQueue = Volley.newRequestQueue(login.this);


        navigate = (TextView) findViewById(R.id.linkSU);
        login = (Button) findViewById(R.id.li);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suPW=uPW.getText().toString();
                String suserID=userID.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                JSONObject jsonobject = null;
                                JSONArray jsonarray=null;
                                try {
                                    jsonobject = new JSONObject(ServerResponse);
                                    jsonarray = jsonobject.getJSONArray("userdetails");
                                    JSONObject data = jsonarray.getJSONObject(0);
                                    id = data.getString("id");
                                    desg=data.getString("desg");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(login.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                Toast.makeText(login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", suserID);
                        params.put("password", suPW);
                        return params;
                    }
                };
                    Intent intent = new Intent(login.this, viewSchedule.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
            }
        });
    }
}
