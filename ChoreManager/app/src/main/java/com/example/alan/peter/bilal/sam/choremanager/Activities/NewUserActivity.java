package com.example.alan.peter.bilal.sam.choremanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.alan.peter.bilal.sam.choremanager.Classes.AdminUser;
import com.example.alan.peter.bilal.sam.choremanager.Classes.ChoreManagerProfile;
import com.example.alan.peter.bilal.sam.choremanager.R;
import com.example.alan.peter.bilal.sam.choremanager.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: NewUserActivity
 */

/**
 * Screen where users can write information, choose a profile pic, to create a new user.
 */
public class NewUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // Creating variables to link to XML and other private variables
    private Spinner accountTypeSpinner;
    private ArrayAdapter accountTypeAdapter;
    private DatabaseReference fbRef = AppLoginActivity.databaseFamilies;
    private String email = AppLoginActivity.emailEscaped;
    private String name;
    private String pass;
    private User newUser;
    private AdminUser newAdminUser;
    private ChoreManagerProfile manager = MenuActivity.getManager();
    private int resID = 2131230876;
    // Overides and If back button is pressed, it will send user to the menu screen
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(NewUserActivity.this, UserMenu.class));
        finish();

    }
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

        setContentView(R.layout.activity_new_user);

        // create adapter from string array in string.xml file for totalPointsSpinner
        accountTypeAdapter = ArrayAdapter.createFromResource(this,R.array.accountTypeSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        accountTypeSpinner =(Spinner) findViewById(R.id.accountTypeSpinner);
        accountTypeSpinner.setAdapter(accountTypeAdapter);
        accountTypeSpinner.setOnItemSelectedListener(NewUserActivity.this);
    }
    // Creates new activrty that allows user to pick a profile picture
    public void setAvatarButtonOnClick(View view)
    {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), ChooseProfilePictureActivity.class);
        startActivityForResult (intent,0);
    }
    // Adapted from SEG 2105 Lab - Sports Manager
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.ic_avatar_11);
        //Figuring out the correct image
        String drawableName = "ic_avatar_11_2";
        switch (data.getIntExtra("imageID",R.id.ic_avatar_11)) {
            case R.id.ic_avatar_11:
                drawableName = "ic_avatar_11_2";
                break;
            case R.id.ic_avatar_00:
                drawableName = "ic_avatar_00_2";
                break;
            case R.id.ic_avatar_01:
                drawableName = "ic_avatar_01_2";
                break;
            case R.id.ic_avatar_02:
                drawableName = "ic_avatar_02_2";
                break;
            case R.id.ic_avatar_03:
                drawableName = "ic_avatar_03_2";
                break;
            case R.id.ic_avatar_04:
                drawableName = "ic_avatar_04_2";
                break;
            case R.id.ic_avatar_05:
                drawableName = "ic_avatar_05_2";
                break;
            case R.id.ic_avatar_06:
                drawableName = "ic_avatar_06_2";
                break;
            case R.id.ic_avatar_07:
                drawableName = "ic_avatar_07_2";
                break;
            case R.id.ic_avatar_08:
                drawableName = "ic_avatar_08_2";
                break;
            case R.id.ic_avatar_09:
                drawableName = "ic_avatar_09_2";
                break;
            case R.id.ic_avatar_10:
                drawableName = "ic_avatar_10_2";
                break;
            default:
                drawableName = "ic_avatar_11_2";
                break;
        }
        resID = getResources().getIdentifier(drawableName, "drawable",
                getPackageName());
        Log.d("test", Integer.toString(resID));
        avatarImage.setImageResource(resID);
    }
    // if user presses cancel close this activity
    public void cancelSelectedOnClick(View view){
    startActivity(new Intent(NewUserActivity.this, UserMenu.class));
    finish();
    }
    // if user presses create new User, create this
    public void createUserOnClick(View view)
    {   EditText username = (EditText)findViewById(R.id.usernameText);
        name = username.getText().toString();
        List<String> listOfUsernames = MenuActivity.getManager().getListOfUsernames();
        if(listOfUsernames.contains(name)){
            Snackbar.make(view, "Name already exists! Please choose a different name.", Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
        }
        else{
            fbRef.addListenerForSingleValueEvent(listener);
            finish();
            startActivity(new Intent(NewUserActivity.this, UserMenu.class));
        }

    }

    // Creates new user and updates ChoreManager in database
    public ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            EditText username = (EditText)findViewById(R.id.usernameText);
            name = username.getText().toString();
            EditText password = (EditText)findViewById(R.id.passwordText);
            pass = password.getText().toString();
            Spinner grabAccountType = findViewById(R.id.accountTypeSpinner);
            String accountType = (String) grabAccountType.getSelectedItem();
            if (!(dataSnapshot.child(email).hasChild(name))) {
                if (accountType.equals("Child")) {
                    newUser = new User(name, pass, MenuActivity.getManager().nextSerialNumber(), resID);
                    manager.setCurrentUserId(newUser.getUserId());
                    manager.addRegUser(newUser);
                    fbRef.child(email).child("ChoreManager").setValue(manager);
                } else {
                    newAdminUser = new AdminUser(name, pass, MenuActivity.getManager().nextSerialNumber(), resID);
                    manager.setCurrentUserId(newAdminUser.getUserId());
                    manager.addAdminUser(newAdminUser);
                    fbRef.child(email).child("ChoreManager").setValue(manager);
                }
            }
           finish();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}