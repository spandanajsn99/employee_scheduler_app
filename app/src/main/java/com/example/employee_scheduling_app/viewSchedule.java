package com.example.employee_scheduling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class viewSchedule  extends AppCompatActivity {

    RequestQueue requestQueue;
    String HttpUrl = "http://10.107.39.179/sch.php";
    String id;
    public TextView ms1;
    public TextView ms2;
    public TextView ms3;
    public TextView ts1;
    public TextView ts2;
    public TextView ts3;
    public TextView ws1;
    public TextView ws2;
    public TextView ws3;
    public TextView ths1;
    public TextView ths2;
    public TextView ths3;
    public TextView fs1;
    public TextView fs2;
    public TextView fs3;
    public TextView ss1;
    public TextView ss2;
    public TextView ss3;
    public TextView sns1;
    public TextView sns2;
    public TextView sns3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_view_schedule);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        requestQueue = Volley.newRequestQueue(viewSchedule.this);
        TextView ms1 = (TextView)findViewById(R.id.ms1);
        TextView ms2 = (TextView)findViewById(R.id.ms2);
        TextView ms3 = (TextView)findViewById(R.id.ms3);
        TextView ts1 = (TextView)findViewById(R.id.ts1);
        TextView ts2 = (TextView)findViewById(R.id.ts2);
        TextView ts3 = (TextView)findViewById(R.id.ts3);
        TextView ws1 = (TextView)findViewById(R.id.ws1);
        TextView ws2 = (TextView)findViewById(R.id.ws2);
        TextView ws3 = (TextView)findViewById(R.id.ws3);
        TextView ths1 = (TextView)findViewById(R.id.ths1);
        TextView ths2 = (TextView)findViewById(R.id.ths2);
        TextView ths3 = (TextView)findViewById(R.id.ths3);
        TextView fs1 = (TextView)findViewById(R.id.fs1);
        TextView fs2 = (TextView)findViewById(R.id.fs2);
        TextView fs3 = (TextView)findViewById(R.id.fs3);
        TextView ss1 = (TextView)findViewById(R.id.ss1);
        TextView ss2 = (TextView)findViewById(R.id.ss2);
        TextView ss3 = (TextView)findViewById(R.id.ss3);
        TextView sns1 = (TextView)findViewById(R.id.sns1);
        TextView sns2 = (TextView)findViewById(R.id.sns2);
        TextView sns3 = (TextView)findViewById(R.id.sns3);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {

                    public String convertreq(int s)
                    {
                        if(s==0)
                            return "Req";
                        else if(s==1)
                            return "NReq";
                        else return "null";
                    }

                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            JSONObject jsonobject = new JSONObject(ServerResponse);

                            JSONArray jsonarray = jsonobject.getJSONArray("schedule");

                            JSONObject data0 = jsonarray.getJSONObject(0);
                            String nid0 = data0.getString("nid");
                            int req0 = data0.getInt("req");
                            ms1.setText("N"+nid0+"    "+convertreq(req0));


                            JSONObject data1 = jsonarray.getJSONObject(1);
                            String nid1 = data1.getString("nid");
                            int req1 = data1.getInt("req");
                            ms2.setText("N"+nid1+"    "+convertreq(req1));


                            JSONObject data2 = jsonarray.getJSONObject(2);
                            String nid2 = data2.getString("nid");
                            int req2 = data2.getInt("req");
                            ms3.setText("N"+nid2+"    "+convertreq(req2));


                            JSONObject data3 = jsonarray.getJSONObject(3);
                            String nid3 = data3.getString("nid");
                            int req3 = data3.getInt("req");
                            ts1.setText("N"+nid3+"    "+convertreq(req3));

                            JSONObject data4 = jsonarray.getJSONObject(4);
                            String nid4 = data4.getString("nid");
                            int req4 = data4.getInt("req");
                            ts2.setText("N"+nid4+"    "+convertreq(req4));

                            JSONObject data5 = jsonarray.getJSONObject(5);
                            String nid5 = data5.getString("nid");
                            int req5 = data5.getInt("req");
                            ts3.setText("N"+nid5+"    "+convertreq(req5));

                            JSONObject data6 = jsonarray.getJSONObject(6);
                            String nid6 = data6.getString("nid");
                            int req6 = data6.getInt("req");
                            ws1.setText("N"+nid6+"    "+convertreq(req6));

                            JSONObject data7 = jsonarray.getJSONObject(7);
                            String nid7 = data7.getString("nid");
                            int req7 = data7.getInt("req");
                            ws2.setText("N"+nid7+"    "+convertreq(req7));

                            JSONObject data8 = jsonarray.getJSONObject(8);
                            String nid8 = data8.getString("nid");
                            int req8 = data8.getInt("req");
                            ws3.setText("N"+nid8+"    "+convertreq(req8));

                            JSONObject data9 = jsonarray.getJSONObject(9);
                            String nid9 = data9.getString("nid");
                            int req9 = data9.getInt("req");
                            ths1.setText("N"+nid9+"    "+convertreq(req9));

                            JSONObject data10 = jsonarray.getJSONObject(10);
                            String nid10 = data10.getString("nid");
                            int req10 = data10.getInt("req");
                            ths2.setText("N"+nid10+"    "+convertreq(req10));

                            JSONObject data11 = jsonarray.getJSONObject(11);
                            String nid11 = data11.getString("nid");
                            int req11 = data11.getInt("req");
                            ths3.setText("N"+nid11+"    "+convertreq(req11));

                            JSONObject data12 = jsonarray.getJSONObject(12);
                            String nid12 = data12.getString("nid");
                            int req12 = data12.getInt("req");
                            fs1.setText("N"+nid12+"    "+convertreq(req12));

                            JSONObject data13 = jsonarray.getJSONObject(13);
                            String nid13 = data13.getString("nid");
                            int req13 = data13.getInt("req");
                            fs2.setText("N"+nid13+"    "+convertreq(req13));

                            JSONObject data14 = jsonarray.getJSONObject(14);
                            String nid14 = data14.getString("nid");
                            int req14 = data14.getInt("req");
                            fs3.setText("N"+nid14+"    "+convertreq(req14));

                            JSONObject data15 = jsonarray.getJSONObject(15);
                            String nid15 = data15.getString("nid");
                            int req15 = data15.getInt("req");
                            ss1.setText("N"+nid15+"    "+convertreq(req15));

                            JSONObject data16 = jsonarray.getJSONObject(16);
                            String nid16 = data16.getString("nid");
                            int req16 = data16.getInt("req");
                            ss2.setText("N"+nid16+"    "+convertreq(req16));

                            JSONObject data17 = jsonarray.getJSONObject(17);
                            String nid17 = data17.getString("nid");
                            int req17 = data17.getInt("req");
                            ss3.setText("N"+nid17+"    "+convertreq(req17));

                            JSONObject data18 = jsonarray.getJSONObject(18);
                            String nid18 = data18.getString("nid");
                            int req18 = data18.getInt("req");
                            sns1.setText("N"+nid18+"    "+convertreq(req18));

                            JSONObject data19 = jsonarray.getJSONObject(19);
                            String nid19 = data19.getString("nid");
                            int req19 = data19.getInt("req");
                            sns2.setText("N"+nid19+"    "+convertreq(req19));

                            JSONObject data20 = jsonarray.getJSONObject(20);
                            String nid20 = data20.getString("nid");
                            int req20 = data20.getInt("req");
                            sns3.setText("N"+nid20+"    "+convertreq(req20));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(viewSchedule.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(viewSchedule.this);
        requestQueue.add(stringRequest);
    }
}

