package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpecificChoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chore);
        /*
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
        String typeText = typeView.getText() + " " + chore.getType().toString();
        typeView.setText(typeText);

        //Display Points
        TextView pointsView = (TextView) findViewById(R.id.pointsTextView2);
        String pointsText = "Points: "+ chore.getRewardPoints();
        pointsView.setText(pointsText);

        //Display Completion Status
        setStatusText(chore.getStatusString());

        //Displaying Chore Name

        */
    }

    public void onClickCompleteChore(View view){
        /*
        Intent i = getIntent();
        Chore chore = (Chore) i.getSerializableExtra("ChoreInfo"); //Serilizable Chore
        User currentUser = MenuActivity.getManager().getCurrentUser(); //Getting Current User
        chore = currentUser.getChoreFromId(chore.getId()); //Actual Chore

        if(chore != null){
            if(chore.getStatusString().equals("Complete")){ //IF already done
                Snackbar.make(view, "Already Completed", Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
            else{
                Snackbar.make(view, "You have received "+ currentUser.completeChore(chore) +" points!", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                setStatusText(chore.getStatusString());
            }

        }
        else{
            Snackbar.make(view, "NOT ASSIGNED TO YOU!", Snackbar.LENGTH_LONG).setAction("Action",null).show();
        }
        */

    }

    public void setStatusText(String status){
        /*
        TextView statusView = (TextView) findViewById(R.id.choreStatusTextView);
        String statusText = "Status: " + status;
        statusView.setText(statusText);
        */
        return;
    }
}
