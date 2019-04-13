package com.example.employee_scheduling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class empList extends AppCompatActivity {

    public Button all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_list);

        all = (Button) findViewById(R.id.slctall);

        String listEmp[]={"Subasri","Shriram"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listEmp);
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_emp_list,menu);
        all.setVisibility(View.GONE);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(empList.this, addEmp.class);
                startActivity(intent);
                all.setVisibility(View.GONE);
                break;
            case R.id.del:
                all.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }
}
