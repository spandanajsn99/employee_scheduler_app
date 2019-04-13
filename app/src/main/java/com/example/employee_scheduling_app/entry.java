package com.example.employee_scheduling_app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class entry extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_entry);

         new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(entry.this,login.class);
                    entry.this.startActivity(mainIntent);
                    entry.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }

