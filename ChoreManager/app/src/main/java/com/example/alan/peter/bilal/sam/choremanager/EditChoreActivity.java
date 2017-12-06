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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: EditChoreActivity
 */

/**
 * A screen where users can edit any information of a pre-existing chore or delete the chore.
 */
public class EditChoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
        setContentView(R.layout.activity_edit_chore);
        Intent intent = getIntent();
        Chore chore = (Chore) intent.getSerializableExtra("ChoreInfo2");
        //allows textfields to move above keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // create adapter from string array in string.xml file for ChoreTypeSpinner
        choreAdapter = ArrayAdapter.createFromResource(this,R.array.choreTypeSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        choreTypeSpinner =(Spinner) findViewById(R.id.choreTypeSpinner);
        choreTypeSpinner.setAdapter(choreAdapter);
        choreTypeSpinner.setOnItemSelectedListener(EditChoreActivity.this);

        List<String> listOfUsernames = new ArrayList<String>();
        listOfUsernames.add("None");
        listOfUsernames.addAll(MenuActivity.getManager().getListOfUsernames());
        // create adapter from string array in string.xml file for RepeatableSpinner
        assignToAdapter = new ArrayAdapter<String>(EditChoreActivity.this,android.R.layout.simple_spinner_item, listOfUsernames );

        // set spinner to the one the the xml
        repeatableSpinner =(Spinner) findViewById(R.id.assignToSpiner);
        repeatableSpinner.setAdapter(assignToAdapter);
        repeatableSpinner.setOnItemSelectedListener(EditChoreActivity.this);

        // create adapter from string array in string.xml file for totalPointsSpinner

        totalPointsAdapter = ArrayAdapter.createFromResource(this,R.array.totalPointsSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        totalPointsSpinner =(Spinner) findViewById(R.id.totalPointsSpinner);
        totalPointsSpinner.setAdapter(totalPointsAdapter);
        totalPointsSpinner.setOnItemSelectedListener(EditChoreActivity.this);


        // linking deadline button and text to xml
        deadlineButton = (Button)findViewById(R.id.deadlineButton);
        actualDeadlineTextView = (TextView)findViewById(R.id.actualDeadlineTextView);
        actualDeadlineTextView.setText(formatDateTime.format(chore.getDeadline()));

        deadlineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                updateDate();
            }
        });
        updateDeadLineText();

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

        CustomMaterialListAdapter myAdapter = new CustomMaterialListAdapter(EditChoreActivity.this, 0,
                listVOs);

        spinner.setAdapter(myAdapter);

        //UPDATING EXiSTING VIEWS---------------------------------------------------------------------------------
        EditText grabChoreName = (EditText) findViewById(R.id.choreNameInput); //Chore Name
        grabChoreName.setText(chore.getName());

        Spinner grabAssignedTo = findViewById(R.id.assignToSpiner); //Who the Chore is assigned to

        //PUTTING EXISITNG ASSIGN TO
        for(int j = 0; j < listOfUsernames.size(); j++ ){
            if(chore.getAssignedToId() == 0){
                grabAssignedTo.setSelection(0);
            }
            else{
                User assignedUser = MenuActivity.getManager().getUserFromId(chore.getAssignedToId());
                if (!(assignedUser == null)){
                    if(grabAssignedTo.getItemAtPosition(j).equals(MenuActivity.getManager().getUserFromId(chore.getAssignedToId()).getUsername())){
                        grabAssignedTo.setSelection(j);
                    }
                }


            }

        }

        Spinner grabChoreType = findViewById(R.id.choreTypeSpinner); // THe type of chore
        if(chore.isMisc()){
            grabChoreType.setSelection(0);
        }
        else if (chore.isCleaning()){
            grabChoreType.setSelection(1);
        }
        else{
            grabChoreType.setSelection(2);
        }

        Spinner grabPoints = findViewById(R.id.totalPointsSpinner); // The points the chore is worth
        grabPoints.setSelection(chore.getRewardPoints()-1);

        EditText grabDesc = (EditText) findViewById(R.id.descTextView2); //Description of Chore
        grabDesc.setText(chore.getDescription());

        EditText grabNote = (EditText) findViewById(R.id.notesTextView); //Note of Chore
        grabNote.setText(chore.getNotes());
        actualDeadlineTextView.setText(formatDateTime.format(chore.getDeadline()));

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

    // listening if Delete button is clicked
    protected void deleteOnClick(View view){
        // Build an alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // create a new view that inflates the custom shoppping list layout
        View newView = getLayoutInflater().inflate(R.layout.delete_item_layout, null);
        // declaring objects in the layout and linking them to the xml
        final Button cancelButton = (Button) newView.findViewById(R.id.cancelButton);
        final Button confirmButton = (Button) newView.findViewById(R.id.confirmButton);
        alert.setView(newView);
        //create Alert Dialog
        final AlertDialog dialog = alert.create();
        dialog.show();
        // Listen and close the dialog if user clicks Cancel
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        // Listen and close the dialog and deletes content if user clicks Confirm
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdminUser currentUser = (AdminUser)MenuActivity.getManager().getCurrentUser();
                Intent i = getIntent();
                Chore chore = (Chore) i.getSerializableExtra("ChoreInfo2");
                MenuActivity.getManager().removeChore(chore.getChoreId());
                MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                //close dialog
                dialog.cancel();
                //Clear Stack and Make MenuScreen and Go back to Chore List
                Intent mainIntent = new Intent(EditChoreActivity.this, MenuActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
                Intent intent = new Intent(EditChoreActivity.this, ChoreListActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    // listening if Save and Exit button is clicked
    protected void saveExitOnClick(View view){
        //Clear Stack and Make MenuScreen and Go back to Chore List
        Intent mainIntent = new Intent(EditChoreActivity.this, MenuActivity.class);


        // Linking objects to XML
        EditText grabChoreName = (EditText) findViewById(R.id.choreNameInput); //Chore Name
        Spinner grabAssignedTo = findViewById(R.id.assignToSpiner); //Who the Chore is assigned to
        Spinner grabChoreType = findViewById(R.id.choreTypeSpinner); // THe type of chore
        Spinner grabPoints = findViewById(R.id.totalPointsSpinner); // The points the chore is worth
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

        Date choreDeadline = dateTime.getTime(); //Deadline
        //GETTING OLD CHORE TO CHECK IF TIME IS SAME AND TO REMOVE OLDCHORE LATER.
        Intent choreIntent = getIntent();
        Chore oldChore = (Chore) choreIntent.getSerializableExtra("ChoreInfo2");

        //Checking if User did not change chore time.
        if(formatDateTime.format(oldChore.getDeadline()).equals(actualDeadlineTextView.getText().toString())){
           choreDeadline = oldChore.getDeadline();
        }

        //Gets the user the chore is assigne to and the current user.
        User assignedUser = MenuActivity.getManager().getUserFromName(choreAssignedTo);

        //Administrator user
        AdminUser currentUser = MenuActivity.getManager().getAdminUserFromId(MenuActivity.getManager().getCurrentUserId());

        Chore newChore; //Chore thats being created

        if (assignedUser == null || assignedUser.getUsername().equals("None")){ //UNASSIGNED CHORE

            newChore = currentUser.createUnAssignedChore(choreName, choreDesc, choreNote, choreTotalPoints, choreType,
                    choreDeadline,resources , MenuActivity.getManager().nextSerialNumber() );

            MenuActivity.getManager().addUnassignedChores(newChore);
        }
        else{
            newChore = currentUser.createChore(choreName, choreDesc, choreNote, choreTotalPoints, choreType, //If theres a User to assign
                    choreDeadline, resources, MenuActivity.getManager().nextSerialNumber(), assignedUser);
        }


        MenuActivity.getManager().removeChore(oldChore.getChoreId()); //Delete old chore
        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager()); //Write to Database


        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        Intent intent = new Intent(EditChoreActivity.this, ChoreListActivity.class);
        startActivity(intent);
        finish();

    }


}