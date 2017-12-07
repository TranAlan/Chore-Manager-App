package com.example.alan.peter.bilal.sam.choremanager.Activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.alan.peter.bilal.sam.choremanager.Classes.ChoreManagerProfile;
import com.example.alan.peter.bilal.sam.choremanager.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: MenuActivity
 */

/**
 * A screen that displays all options for the user to do in the app.
 * This consist of changeing users, viewing and creating chores.
 * Also reseting app data, adding/deleting items, add/delete a shoppingList
 */
public class MenuActivity extends AppCompatActivity {

    private static FirebaseUser fbUser = AppLoginActivity.user; //Database instance
    private static DatabaseReference fbRef = AppLoginActivity.databaseFamilies; //Database reference
    private static String email = AppLoginActivity.emailEscaped; //Email of family google account
    private static ChoreManagerProfile manager = AppLoginActivity.manager; //The manager that stores everything of a family.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
    }

    // all the button listeners that direct to appropriate activities

    //UserMenu Activity
    protected void userImageOnClick(View view){
        Intent intent = new Intent(this, UserMenu.class);
        startActivity(intent);
    }

    //Settings Activity
    protected void settingImageOnClick(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    //Materials Activity
    protected void materialsImageOnClick(View view){
        Intent intent = new Intent(this, MaterialsActivity.class);
        startActivity(intent);
    }

    //ChoreList Activity
    protected void choreListImageOnClick(View view){
        Intent intent = new Intent(this, ChoreListActivity.class);
        startActivity(intent);

    }

    // Returns the ChoreManagerProfile this is required because
    // almost all activities require access to the manager. (Get information or write to database)
    public static ChoreManagerProfile getManager(){
        return manager;
    }

    protected void groceriesImageOnClick(View view){
        Intent intent = new Intent(this, Groceries.class);
        startActivity(intent);
    }

    protected void shoppingImageOnClick(View view){
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    //Get the database reference
    protected static DatabaseReference getFbRef(){
        return fbRef;
    }

    //get email of the family
    protected static String getEmail(){
        return email;
    }

    //The FirebaseUser
    protected static FirebaseUser getFbUser(){
        return fbUser;
    }
}