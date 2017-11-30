package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewChoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    // creating spinner for choretpye and adapter
    Spinner choreTypeSpinner,repeatableSpinner,repeatableTimesSpinner;
    ArrayAdapter choreAdapter,repeatbleAdapter, assignToAdapter;
    NumberPicker numberPicker = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chore);
        //allows textfields to move above keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // create adapter from string array in string.xml file for ChoreTypeSpinner
        choreAdapter = ArrayAdapter.createFromResource(this,R.array.choreTypeSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        choreTypeSpinner =(Spinner) findViewById(R.id.choreTypeSpinner);
        choreTypeSpinner.setAdapter(choreAdapter);
        choreTypeSpinner.setOnItemSelectedListener(NewChoreActivity.this);

        // create adapter from string array in string.xml file for RepeatableSpinner
        repeatbleAdapter = ArrayAdapter.createFromResource(this,R.array.repeatableSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        repeatableSpinner =(Spinner) findViewById(R.id.repeatableSpinner);
        repeatableSpinner.setAdapter(repeatbleAdapter);
        repeatableSpinner.setOnItemSelectedListener(NewChoreActivity.this);


        // create adapter from string array in string.xml file for RepeatableSpinner
        assignToAdapter = ArrayAdapter.createFromResource(this,R.array.userSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        repeatableSpinner =(Spinner) findViewById(R.id.assignToSpiner);
        repeatableSpinner.setAdapter(assignToAdapter);
        repeatableSpinner.setOnItemSelectedListener(NewChoreActivity.this);

        // Num Picker
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this,"You Selected: "+ spinnerDialogText.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {
        // Not using this
    }

    // listening if Save and Exit button is clicked
    protected void saveExitOnClick(View view){
        Intent intent = new Intent(this, ChoreListActivity.class);
        /*
        if (not assigned){

            currentUser.createChore(name, desc, note, points, repeat, due, materials, groceries);
        }
        else{
            AdminUser user = (AdminUser) MenuActivity.getManager().getCurrentUser();
            user.createChore()
        }

        */

        startActivity(intent);
    }
}
