package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinnerDropdownTheme, spinnerDropDownUsers;
    ArrayAdapter themeAdapter, userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeAdapter = ArrayAdapter.createFromResource(this,R.array.themeSpinner_Options,android.R.layout.simple_spinner_item);
        spinnerDropdownTheme =(Spinner) findViewById(R.id.themeSpinner);
        spinnerDropdownTheme.setAdapter(themeAdapter);
        spinnerDropdownTheme.setOnItemSelectedListener(SettingsActivity.this);

        userAdapter = ArrayAdapter.createFromResource(this,R.array.userSpinner_Options,android.R.layout.simple_spinner_item);
        spinnerDropDownUsers =(Spinner) findViewById(R.id.userSpinner);
        spinnerDropDownUsers.setAdapter(userAdapter);
        spinnerDropDownUsers.setOnItemSelectedListener(SettingsActivity.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this,"You Selected: "+ spinnerDialogText.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }

    protected void resetDataOnClick(View view) {
        MenuActivity.getManager().resetAppData();
        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
    }
}
