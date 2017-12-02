package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private  Spinner accountTypeSpinner;
    private ArrayAdapter accountTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        // create adapter from string array in string.xml file for totalPointsSpinner

        accountTypeAdapter = ArrayAdapter.createFromResource(this,R.array.accountTypeSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        accountTypeSpinner =(Spinner) findViewById(R.id.accountTypeSpinner);
        accountTypeSpinner.setAdapter(accountTypeAdapter);
        accountTypeSpinner.setOnItemSelectedListener(NewUserActivity.this);
    }

    // if user presses cancel close this activity
    public void cancelSelectedOnClick(View view)
    {
        finish();
    }
    // if user presses create new User, create this
    public void createUserOnClick(View view)
    {
        finish();
        //TODO: CODE THIS
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
