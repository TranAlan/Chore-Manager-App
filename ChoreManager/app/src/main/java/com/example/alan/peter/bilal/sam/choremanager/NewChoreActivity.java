package com.example.alan.peter.bilal.sam.choremanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewChoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    // creating variables to link with xml
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private Spinner choreTypeSpinner,repeatableSpinner,totalPointsSpinner;
    private ArrayAdapter choreAdapter,repeatbleAdapter, assignToAdapter, totalPointsAdapter;
    private NumberPicker numberPicker = null;
    private Button deadlineButton;
    private TextView actualDeadlineTextView;
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

        // create adapter from string array in string.xml file for totalPointsSpinner
        List points = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            points.add(Integer.toString(i));
        }
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, points);
        totalPointsAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // set spinner to the one the the xml
        totalPointsSpinner =(Spinner) findViewById(R.id.totalPointsSpinner);
        totalPointsSpinner.setAdapter(totalPointsAdapter);
        totalPointsSpinner.setOnItemSelectedListener(NewChoreActivity.this);




        // Num Picker
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);

        // linking deadline button and text to xml
        deadlineButton = (Button)findViewById(R.id.deadlineButton);
        actualDeadlineTextView = (TextView)findViewById(R.id.actualDeadlineTextView);

        deadlineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                updateDate();
            }
        });
        updateDeadLineText();

    }
    public void updateDate()
    {
        new DatePickerDialog(this,d,dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void updateTime()
    {
        new TimePickerDialog(this,t,dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),true).show();

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
        dateTime.set(Calendar.YEAR,year);
        dateTime.set(Calendar.MONTH,monthofYear);
        dateTime.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        updateDeadLineText();
        updateTime();
        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener(){
    public  void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        dateTime.set(Calendar.HOUR_OF_DAY,hourOfDay);
        dateTime.set(Calendar.MINUTE,minute);
        updateDeadLineText();
    }
    };

    public void updateDeadLineText(){
        actualDeadlineTextView.setText(formatDateTime.format(dateTime.getTime()));
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

        EditText grabChoreName = (EditText) findViewById(R.id.choreNameInput);
        //EditText grabAssignedTo = (EditText) findViewById(R.id.assignToSpinner);
        Spinner grabChoreType = findViewById(R.id.choreTypeSpinner);
        //Requried matierals
        //NumberPicker grabRepeatValue = findViewById(R.id.numberPicker);
        EditText grabDesc = (EditText) findViewById(R.id.descTextView2);
        EditText grabNote = (EditText) findViewById(R.id.notesTextView);



        String choreName = grabChoreName.getText().toString();
        //String choreAssignedTo = grabAssignedTo.getText().toString();
        String choreType = (String) grabChoreType.getSelectedItem();
        //int choreRepeatValue = grabRepeatValue.getValue();
        //String choreRepeatType = (String) grabRepeatType.getSelectedItem();
        String choreDesc = grabDesc.getText().toString();
        String choreNote = grabDesc.getText().toString();
        //int choreTotalPoints = grabPoints.getValue();

        /*
        if (choreRepeatType.equals("Daily")){
            Repeated repeat = new Repeated(choreRepeatValue);
            repeat.setTypeDaily();
        }
        else if(choreRepeatType.equals("Weekly")){
            Repeated repeat = new Repeated(choreRepeatValue);
            repeat.setTypeMonthly();
        }

        else if(choreRepeatType.equals("Monthly")){
            Repeated repeat = new Repeated(choreRepeatValue);
            repeat.setTypeMonthly();
        }
        */
        //Log.d("testTag",test);
        //Log.d("testTag",test2);
        //Log.d("testTag",test3);
        //Log.d("testTag",test4);

        /*
        if (not assigned){

            currentUser.createChore(name, desc, note, points, repeat, due, materials, groceries);
        }
        else{
            AdminUser user = (AdminUser) MenuActivity.getManager().getCurrentUser();
            user.createChore()
        }



        if (choreType.equals("Misc"))
        }
        else if (choreType.equals("Cooking")){

        }
        else{

        }
        */

        startActivity(intent);
    }
}
