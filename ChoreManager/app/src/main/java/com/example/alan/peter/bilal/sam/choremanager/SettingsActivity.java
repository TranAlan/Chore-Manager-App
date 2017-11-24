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
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        adapter = ArrayAdapter.createFromResource(this,R.array.themeSpinner_Options,android.R.layout.simple_spinner_item);
        spinnerDropdownTheme =(Spinner) findViewById(R.id.themeSpinner);
        spinnerDropdownTheme.setAdapter(adapter);
        spinnerDropdownTheme.setOnItemSelectedListener(SettingsActivity.this);

        spinnerDropDownUsers =(Spinner) findViewById(R.id.userSpinner);
        spinnerDropDownUsers.setAdapter(adapter);
        spinnerDropDownUsers.setOnItemSelectedListener(SettingsActivity.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this,"You Selected"+ spinnerDialogText.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
