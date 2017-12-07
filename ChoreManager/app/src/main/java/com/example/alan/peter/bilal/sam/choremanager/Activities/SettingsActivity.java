package com.example.alan.peter.bilal.sam.choremanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alan.peter.bilal.sam.choremanager.R;
import com.google.firebase.auth.FirebaseAuth;

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: SettingsActivity
 */

/**
 * Screen where Admin users can log out or reset all data of the app.
 */
public class SettingsActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinnerDropdownTheme, spinnerDropDownUsers;
    ArrayAdapter themeAdapter, userAdapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, "You Selected: " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    protected void resetDataOnClick(View view) {
        if(MenuActivity.getManager().isCurrentUserAdmin()){
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
                    MenuActivity.getManager().resetAppData();
                    MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                    Toast.makeText(getApplicationContext(), "Data has been reset.",Toast.LENGTH_SHORT).show();
                    dialog.cancel();

                    //Clear Stack and Make MenuScreen and Go back to Chore List
                    Intent mainIntent = new Intent(SettingsActivity.this, AppLoginActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }
            });


        }
        else{
            Snackbar.make(view, "You must be signed in as a Admin", Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
        }

    }
    public void logOutOnClick(View view)
    {
        //Clear Stack and Make MenuScreen and Go back to Chore List
        Intent mainIntent = new Intent(SettingsActivity.this, AppLoginActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        FirebaseAuth.getInstance().signOut();
        startActivity(mainIntent);
        finish();
    }
}
