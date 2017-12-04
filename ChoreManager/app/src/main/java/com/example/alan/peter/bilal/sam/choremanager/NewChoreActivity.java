package com.example.alan.peter.bilal.sam.choremanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class NewChoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // creating variables to link with xml
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private Spinner choreTypeSpinner,repeatableSpinner,totalPointsSpinner;
    private ArrayAdapter choreAdapter, assignToAdapter, totalPointsAdapter;
    private Button deadlineButton;
    private TextView actualDeadlineTextView;
    private List<String> allMaterials = new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if( item.getItemId() == android.R.id.home ){
            onBackPressed();
            return true;
        }
        return false;
    }


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
        assignToAdapter = ArrayAdapter.createFromResource(this,R.array.userSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        repeatableSpinner =(Spinner) findViewById(R.id.assignToSpiner);
        repeatableSpinner.setAdapter(assignToAdapter);
        repeatableSpinner.setOnItemSelectedListener(NewChoreActivity.this);

        // create adapter from string array in string.xml file for totalPointsSpinner

        totalPointsAdapter = ArrayAdapter.createFromResource(this,R.array.totalPointsSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        totalPointsSpinner =(Spinner) findViewById(R.id.totalPointsSpinner);
        totalPointsSpinner.setAdapter(totalPointsAdapter);
        totalPointsSpinner.setOnItemSelectedListener(NewChoreActivity.this);


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

        //To Remove
        allMaterials.add("Item 1");
        allMaterials.add("Item 2");
        allMaterials.add("Item 3");

        // get all the materials from pantry and material list
        allMaterials.addAll(MenuActivity.getManager().getMaterials());
        allMaterials.addAll(MenuActivity.getManager().getPantry());

        Spinner spinner = (Spinner) findViewById(R.id.requiredMaterialsSpinner);
        ArrayList<StateVO> listVOs = new ArrayList<>();
        for (int i = 0; i < allMaterials.size(); i++)
        {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(allMaterials.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        CustomMaterialListAdapter myAdapter = new CustomMaterialListAdapter(NewChoreActivity.this, 0,
                listVOs);

        spinner.setAdapter(myAdapter);
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

        EditText grabChoreName = (EditText) findViewById(R.id.choreNameInput); //Chore Name
        Spinner grabAssignedTo = findViewById(R.id.assignToSpiner); //Who the Chore is assigned to
        Spinner grabChoreType = findViewById(R.id.choreTypeSpinner); // THe type of chore
        Spinner grabPoints = findViewById(R.id.totalPointsSpinner); // The points the chore is worth
        //Requried matierals
        Spinner grabResources = findViewById(R.id.requiredMaterialsSpinner); //The list of materials
        EditText grabDesc = (EditText) findViewById(R.id.descTextView2); //Description of Chore
        EditText grabNote = (EditText) findViewById(R.id.notesTextView); //Note of Chore


        //Simple variables from newChoreActivity
        String choreName = grabChoreName.getText().toString();
        String choreAssignedTo = (String) grabAssignedTo.getSelectedItem();
        String choreType = (String) grabChoreType.getSelectedItem();
        String choreDesc = grabDesc.getText().toString();
        String choreNote = grabNote.getText().toString();
        int choreTotalPoints = Integer.parseInt((String)grabPoints.getSelectedItem());

        //FIND ALL MATERIALS THAT WERE SELECTED
        List<String> resources= new ArrayList<String>();
        for(int i = 0; i < allMaterials.size(); i++){
            StateVO currentItem = (StateVO)grabResources.getItemAtPosition(i);
            if (   currentItem.isSelected() ){
                resources.add(currentItem.getTitle());

            }
        }

        //Gets the user the chore is assigne to and the current user.
        User assignedUser = MenuActivity.getManager().getUserFromName(choreAssignedTo);
        //Administrator user
        AdminUser currentUser = MenuActivity.getManager().getAdminUserFromId(MenuActivity.getManager().getCurrentUserId());
        Chore newChore;

        if (assignedUser == null){ //UNASSIGNED CHORE

            newChore = currentUser.createUnAssignedChore(choreName, choreDesc, choreNote, choreTotalPoints, choreType,
                    dateTime.getTime(),resources , MenuActivity.getManager().nextSerialNumber() );
            MenuActivity.getManager().addUnassignedChores(newChore);
        }
        else{
            newChore = currentUser.createChore(choreName, choreDesc, choreNote, choreTotalPoints, choreType, //If theres a User to assign
                    dateTime.getTime(), resources, MenuActivity.getManager().nextSerialNumber(), assignedUser);
        }


        //Changing the type of Chore
        if (choreType.equals("Misc")){
            newChore.setTypeMisc();
        }
        else if (choreType.equals("Cooking")){
            newChore.setTypeCooking();
        }
        else{
            newChore.setTypeCleaning();
        }


       MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());

        startActivity(intent);
        finish();
    }


}
