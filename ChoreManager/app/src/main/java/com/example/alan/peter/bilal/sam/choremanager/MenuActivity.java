package com.example.alan.peter.bilal.sam.choremanager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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
    protected void userImageOnClick(View view){
        Intent intent = new Intent(this, UserMenu.class);
        startActivity(intent);
    }
    protected void settingImageOnClick(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    protected void materialsImageOnClick(View view){
        Intent intent = new Intent(this, MaterialsActivity.class);
        startActivity(intent);
    }


    protected void choreListImageOnClick(View view){
        Intent intent = new Intent(this, ChoreListActivity.class);
        startActivity(intent);

    }

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

    protected static DatabaseReference getFbRef(){
        return fbRef;
    }

    protected static String getEmail(){
        return email;
    }

    protected static FirebaseUser getFbUser(){
        return fbUser;
    }
}