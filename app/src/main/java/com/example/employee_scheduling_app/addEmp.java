package com.example.employee_scheduling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addEmp extends AppCompatActivity {

    public Button seeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);

        seeList = (Button) findViewById(R.id.listView);
        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addEmp.this, empList.class);
                startActivity(intent);
            }
        });
    }
}
