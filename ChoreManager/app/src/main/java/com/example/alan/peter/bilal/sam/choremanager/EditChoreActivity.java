package com.example.alan.peter.bilal.sam.choremanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import android.view.MenuItem;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

        // create adapter from string array in string.xml file for RepeatableSpinner
        assignToAdapter = ArrayAdapter.createFromResource(this,R.array.userSpinner_Options,android.R.layout.simple_spinner_item);
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

        //UPDATING EXiSTING VIEWS

        EditText grabChoreName = (EditText) findViewById(R.id.choreNameInput); //Chore Name
        grabChoreName.setText(chore.getName());

        Spinner grabAssignedTo = findViewById(R.id.assignToSpiner); //Who the Chore is assigned to

        int AdminUserPosition = 0;
        int regUserPosition = 0;
        Iterator<AdminUser> adminIterator = MenuActivity.getManager().getAdminUsers().iterator();
        while(adminIterator.hasNext()){
            if(adminIterator.next().getUserId() != chore.getAssignedToId()){
                break;
            }
            AdminUserPosition++;
        }
        Iterator<User> regIterator = MenuActivity.getManager().getRegUsers().iterator();
        while(regIterator.hasNext()){

            if(regIterator.next().getUserId() == chore.getAssignedToId()){
                break;
            }
            regUserPosition++;
        }

        grabAssignedTo.setSelection(AdminUserPosition + regUserPosition);

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

        //Requried matierals
        //Spinner grabResources = findViewById(R.id.requiredMaterialsSpinner); //The list of materials
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
                //delete items from material list
                //update view
                //TODO
                AdminUser currentUser = (AdminUser)MenuActivity.getManager().getCurrentUser();
                Intent i = getIntent();
                Chore chore = (Chore) i.getSerializableExtra("ChoreInfo2");
                MenuActivity.getManager().removeChore(chore.getChoreId());
                MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                //close dialog
                dialog.cancel();
                //Intent intent = new Intent(this, NewChoreActivity.class);
                //startActivity(intent);
                finish();

            }
        });
    }
    // listening if Save and Exit button is clicked
    protected void saveExitOnClick(View view){
        finish();

        //TODO
    }


}