package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Date;

public class NewChoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chore);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    protected void saveExitOnClick(View view){
        Intent intent = new Intent(this, ChoreListActivity.class);
        /*
        if (not assigned){

            currentUser.createChore(name, desc, note, points, repeat, due, materials, groceries);
        }
        else{

        }
        */
        startActivity(intent);
    }
}
