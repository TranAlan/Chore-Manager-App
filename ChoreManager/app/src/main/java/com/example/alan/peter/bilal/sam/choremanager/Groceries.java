package com.example.alan.peter.bilal.sam.choremanager;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Groceries extends AppCompatActivity {


    //variables
    private String toAdd;
    private ArrayList<String> selected = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);
    }


        //Method to add a new grocery item to the shopping list
        public void addGroceryOnClick(View view) {

            // Build an alert dialog
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Please enter the name of the item you wish to add.");
            alert.setTitle("Add and item");
            final EditText input = new EditText(this);
            alert.setView(input);


            //If user clicks cancel the dialogue closes without adding item to materialList
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            //if user clicks confirm the selected items are added to the material list
            alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //set string to add to user input
                    toAdd=input.getText().toString();
                    //add this item to the Material Arraylist
                    MenuActivity.getManager().addShoppingGrocery(toAdd);
                    //update view
                    //TODO
                    //close dialog
                    dialogInterface.cancel();
                }
            });
        }

        public void deleteSelectedOnClick(View view) {

            //build an alert dialog
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Are you sure you want to delete the selected items? This cannot be undone.");
            alert.setTitle("Delete checked items");

            //if user clicks cancel the dialogue closes without deleting checked items
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            //if user clicks confirm the selected items are deleted from pantry list
            alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //delete items from material list
                    MenuActivity.getManager().getPantry().removeAll(selected);
                    //update view
                    //TODO
                    //close dialog
                    dialogInterface.cancel();
                }
            });
        }
    }
