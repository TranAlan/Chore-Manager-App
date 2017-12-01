package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpecificChoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chore);

        // To retrieve object in second Activity
        Intent i = getIntent();
        String choreNameDate = (String) i.getSerializableExtra("ChoreInfo");
        choreNameDate =  choreNameDate.trim();
        String[] choreInfo = choreNameDate.split(" ", 3);
        String firstName = choreInfo[0];
        String lastName = choreInfo[1];
        String date = choreInfo[2];

        User userOfChore = MenuActivity.getManager().getUserFromName("Peter Lam");

        //Chore chore = userOfChore.getChoreFromDate(date);
        //Log.d("TestTag0", chore.getName());

        //TextView description = (TextView) findViewById(R.id.descTextView);
        //description.setText(chore.getDescription());
        /*
        TextView note = (TextView) findViewById(R.id.descTextView2);
        note.setText(chore.getNotes());
        */

        Log.d("TestTag1",firstName);
        Log.d("TestTag2",lastName);
        Log.d("TestTag3",date);

    }
}
