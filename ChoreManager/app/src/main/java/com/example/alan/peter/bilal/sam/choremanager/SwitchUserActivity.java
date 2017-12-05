package com.example.alan.peter.bilal.sam.choremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SwitchUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);
    }

    public void cancelSelectedOnClick(View view)
    {
        finish();
    }
    // if user presses create new User, create this
    public void switchUserOnClick(View view)
    {
        finish();

    }
}
