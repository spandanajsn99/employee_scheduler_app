package com.example.employee_scheduling_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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


public class shiftReq extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String id;
    public String[] shiftTimings={"Select Shift","Shift1: 10am - 6pm","Shift2: 6pm - 2am","Shift3: 2am - 10am"};
    public Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7;
    RequestQueue requestQueue;
    String HttpUrl = "http://10.107.39.179/sch.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        setContentView(R.layout.activity_shift_req);
        Button submit = (Button) findViewById(R.id.b1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new submitrequests().execute("");
            }
        });

        spinner1 = (Spinner) findViewById(R.id.spin1);
        spinner1.setOnItemSelectedListener(this);
        spinner2 = (Spinner) findViewById(R.id.spin2);
        spinner2.setOnItemSelectedListener(this);
        spinner3 = (Spinner) findViewById(R.id.spin3);
        spinner3.setOnItemSelectedListener(this);
        spinner4 = (Spinner) findViewById(R.id.spin4);
        spinner4.setOnItemSelectedListener(this);
        spinner5 = (Spinner) findViewById(R.id.spin5);
        spinner5.setOnItemSelectedListener(this);
        spinner6 = (Spinner) findViewById(R.id.spin6);
        spinner6.setOnItemSelectedListener(this);
        spinner7 = (Spinner) findViewById(R.id.spin7);
        spinner7.setOnItemSelectedListener(this);

        requestQueue = Volley.newRequestQueue(shiftReq.this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,shiftTimings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }
   public int convert(String s)
    {
        int k=0;
        if(s=="Select Shift")
            k=0;
        else if(s=="Shift1: 10am - 6pm")
            k=1;
        else if(s=="Shift2: 6pm - 2am")
            k=2;
        else if(s=="Shift3: 2am - 10am")
            k=3;
        else;
        return k;
    }
    private class submitrequests extends AsyncTask<String, Void, String> {
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... params) {
            try {

                int d1 = convert(spinner1.getSelectedItem().toString());
                int d2 = convert(spinner2.getSelectedItem().toString());
                int d3 = convert(spinner3.getSelectedItem().toString());
                int d4 = convert(spinner4.getSelectedItem().toString());
                int d5 = convert(spinner5.getSelectedItem().toString());
                int d6 = convert(spinner6.getSelectedItem().toString());
                int d7 = convert(spinner7.getSelectedItem().toString());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                JSONObject jsonobject = null;
                                JSONArray jsonarray=null;
                                try {
                                    jsonobject = new JSONObject(ServerResponse);

                                    jsonarray = jsonobject.getJSONArray("userid");

                                    JSONObject data = jsonarray.getJSONObject(0);
                                    String id = data.getString("id");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                Toast.makeText(shiftReq.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                Toast.makeText(shiftReq.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("d1", Integer.toString(d1));
                        params.put("d2", Integer.toString(d2));
                        params.put("d3", Integer.toString(d3));
                        params.put("d4", Integer.toString(d4));
                        params.put("d5", Integer.toString(d5));
                        params.put("d6", Integer.toString(d6));
                        params.put("d7", Integer.toString(d7));
                        params.put("d1", id);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(shiftReq.this);
                requestQueue.add(stringRequest);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(shiftReq.this,"Shift requests are added!",Toast.LENGTH_SHORT);
        }


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
