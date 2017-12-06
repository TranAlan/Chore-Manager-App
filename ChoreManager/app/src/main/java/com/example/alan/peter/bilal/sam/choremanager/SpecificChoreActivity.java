package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;
import java.util.Calendar;

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: SpecificChoreActivity
 */

/**
 * An activity that provides all information associated to a Chore.
 * In here, Admin users can edit chores or go regular users can find more info about their chore.
 */

public class SpecificChoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        setContentView(R.layout.activity_specific_chore);
        // To retrieve object in second Activity
        Intent i = getIntent();
        Chore chore = (Chore) i.getSerializableExtra("ChoreInfo");

        //Display Description
        TextView description = (TextView) findViewById(R.id.descTextView);
        description.setText(chore.getDescription());

        //Display Chore Note
        TextView note = (TextView) findViewById(R.id.notesTextView2);
        note.setText(chore.getNotes());

        //Diplay Chore Type
        TextView typeView = (TextView) findViewById(R.id.choreTypeTextView);
        String typeText = typeView.getText() + " " + chore.getChoreType().toString();
        typeView.setText(typeText);

        //Display Points
        TextView pointsView = (TextView) findViewById(R.id.pointsTextView2);
        String pointsText = "Points: "+ chore.getRewardPoints();
        pointsView.setText(pointsText);

        //Display Completion Status
        setStatusText(chore.getStatusString());

        //Displaying Chore Name
        TextView nameView = (TextView) findViewById(R.id.choreTitleTextView);
        nameView.setText(chore.getName());


        //Display Assigned Resources
        Spinner assignedResourcesSpinner = (Spinner) findViewById(R.id.assignedResourcesSpinner);
        if(chore.getReqResources() != null){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,chore.getReqResources());
        assignedResourcesSpinner.setAdapter(spinnerArrayAdapter);
        }

        // Parental Control Text and Button
        TextView parentCommandText2 = (TextView) findViewById(R.id.parentCommandText2);
        Button editChoreButton = (Button) findViewById(R.id.editChoreButton);

        if(MenuActivity.getManager().isCurrentUserAdmin()){
            parentCommandText2.setVisibility(View.VISIBLE);
            editChoreButton.setVisibility(View.VISIBLE);
        }
        else
        {
            parentCommandText2.setVisibility(View.GONE);
            editChoreButton.setVisibility(View.GONE);
        }




    }

    //Allows the user to complete their asigned chore.
    public void onClickCompleteChore(View view){

        Intent i = getIntent();
        Chore chore = (Chore) i.getSerializableExtra("ChoreInfo"); //Serilizable Chore
        User currentUser = MenuActivity.getManager().getCurrentUser(); //Getting Current User
        chore = currentUser.getChoreFromId(chore.getChoreId()); //Actual Chore

        if(chore != null){
            if(chore.getStatusString().equals("Complete")){ //IF already done
                Snackbar.make(view, "Already Completed", Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
            else{
                if(Calendar.getInstance().getTime().compareTo(chore.getDeadline()) <= 0){
                    Snackbar.make(view, "You have received "+ currentUser.completeChore(chore) +" points!", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    setStatusText(chore.getStatusString());
                }
                else{
                    Snackbar.make(view, "You completed it late, you get half points: "+ currentUser.completeChoreLate(chore), Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    setStatusText(chore.getStatusString());
                }
                MenuActivity.getManager().addFinishedChores(chore);

            }

        }
        else{
            Snackbar.make(view, "NOT ASSIGNED TO YOU!", Snackbar.LENGTH_LONG).setAction("Action",null).show();
        }

        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
    }

    //users have not completed their chore and will get 0 points
    public void onClickIncompleteChore(View view){

        Intent i = getIntent();
        Chore chore = (Chore) i.getSerializableExtra("ChoreInfo"); //Serilizable Chore
        User currentUser = MenuActivity.getManager().getCurrentUser(); //Getting Current User
        chore = currentUser.getChoreFromId(chore.getChoreId()); //Actual Chore

        if(chore != null){
            if(chore.getStatusString().equals("Complete")){ //IF already done
                Snackbar.make(view, "Already Completed", Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
            else{
                Snackbar.make(view, "Please complete chore next time. You get: "+ currentUser.inCompleteChore(chore), Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                setStatusText(chore.getStatusString());
                MenuActivity.getManager().addFinishedChores(chore);

            }

        }
        else{
            Snackbar.make(view, "NOT ASSIGNED TO YOU!", Snackbar.LENGTH_LONG).setAction("Action",null).show();
        }

        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
    }


    public void setStatusText(String status){

        TextView statusView = (TextView) findViewById(R.id.choreStatusTextView);
        String statusText = "Status: " + status;
        statusView.setText(statusText);

        return;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //Starts next activity where Admin users can edit the info of a chore.
    protected void editChoreOnClick(View view)
    {
        Intent intent = new Intent(this, EditChoreActivity.class);
        Intent i = getIntent();
        Chore chore = (Chore) i.getSerializableExtra("ChoreInfo");
        intent.putExtra("ChoreInfo2", chore);
        startActivity(intent);
        finish();

    }
}
