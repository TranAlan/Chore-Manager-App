package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChoreListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);
    }

    protected void createNewChoreButton(View view){
        Intent intent = new Intent(this, NewChoreActivity.class);
        startActivity(intent);
    }
}
