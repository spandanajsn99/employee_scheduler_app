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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;


public class signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public TextView navigate;
    public Button signup;
    public EditText desig;
    public String[] designation={"Select designation","Nurse","HR"};
    public Spinner desgspin;
    public EditText ename;
    public EditText eusername;
    public EditText epassword;
    public EditText erepwd;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    RequestQueue requestQueue;
    String HttpUrl = "http://10.107.39.179/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        navigate = (TextView) findViewById(R.id.li);
        signup = (Button) findViewById(R.id.su);
        desgspin = (Spinner) findViewById(R.id.Edesg);
        desgspin.setOnItemSelectedListener(this);
        ename = (EditText)findViewById(R.id.Ename);
        eusername = (EditText)findViewById(R.id.Eusername);
        epassword = (EditText)findViewById(R.id.Epassword);
        erepwd = (EditText)findViewById(R.id.Erepwd);
        requestQueue = Volley.newRequestQueue(signup.this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,designation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desgspin.setAdapter(adapter);

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ename.getText().toString();
                String username = eusername.getText().toString();
                String password = epassword.getText().toString();
                String repwd = erepwd.getText().toString();
                String d = desgspin.getSelectedItem().toString();
                String desg="Null";
                if(d=="Nurse")
                    desg="N";
                else if(d=="HR")
                    desg="H";
                else;

                new submitsignup().execute(name,username,password,repwd,desg);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class submitsignup extends AsyncTask<String, Void, String> {
        @SuppressLint("WrongThread")

        @Override
        protected String doInBackground(String... params) {


            String name=params[0];
            String desg=params[4];
            String password=params[2];
            String username=params[1];

                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                Toast.makeText(signup.this, "Record inserted!", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                Toast.makeText(signup.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("desg", desg);
                        params.put("username", username);
                        params.put("password", password);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(signup.this);
                requestQueue.add(stringRequest);

           return null;

        }


        @Override
        protected void onPostExecute(String result) {

                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);

        }


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}
