package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewChoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chore);
    }

    protected void saveExitOnClick(View view){
        Intent intent = new Intent(this, ChoreListActivity.class);
        startActivity(intent);
    }
}
