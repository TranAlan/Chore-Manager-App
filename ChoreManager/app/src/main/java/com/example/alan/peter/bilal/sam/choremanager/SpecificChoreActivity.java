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
        Chore chore = (Chore) i.getSerializableExtra("ChoreInfo");

        TextView description = (TextView) findViewById(R.id.descTextView);
        description.setText(chore.getDescription());

        TextView note = (TextView) findViewById(R.id.notesTextView2);
        note.setText(chore.getNotes());

        TextView typeView = (TextView) findViewById(R.id.choreTypeTextView);
        String typeText = typeView.getText() + " " + chore.choreType.toString();
        typeView.setText(typeText);

        TextView pointsView = (TextView) findViewById(R.id.pointsTextView2);
        String pointsText = "Points: "+ chore.getRewardPoints();
        pointsView.setText(pointsText);

    }
}
