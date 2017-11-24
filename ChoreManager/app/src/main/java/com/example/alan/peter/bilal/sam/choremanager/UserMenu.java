package com.example.alan.peter.bilal.sam.choremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        // Add back button to activity

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    //check for back button click
//    @Override
//    public boolean menuBarSelected(MenuItem item)
//    {
//        int id = item.getItemId();
//        if (id == andriod.R.id.home) return super.menuBarSelected(item)
//    }

}
