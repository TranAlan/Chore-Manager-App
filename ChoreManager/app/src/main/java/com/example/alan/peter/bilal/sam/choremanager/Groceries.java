package com.example.alan.peter.bilal.sam.choremanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/** Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: Groceries
 */

public class Groceries extends AppCompatActivity {


    //variables
    private ArrayList<String> groceryList = new ArrayList<>();
    private ListView groceryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);

        // get all the items from pantry
        groceryList.addAll(MenuActivity.getManager().getPantry());
        groceryListView = (ListView) findViewById(R.id.groceryListView);

        ArrayList<String> listVOs = new ArrayList<>();
        for (int i = 0; i < groceryList.size(); i++)
        {
            listVOs.add(groceryList.get(i));
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Groceries.this, android.R.layout.simple_list_item_1, listVOs);
        groceryListView.setAdapter(myAdapter);
        groceryListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                final String item = (String) groceryListView.getItemAtPosition(position);

                //create an alert
                AlertDialog.Builder builder = new AlertDialog.Builder(Groceries.this);
                builder.setTitle("Item Selected");
                builder.setMessage("What would you like to do with this selected item?");

                //buttons

                builder.setPositiveButton("Delete Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        MenuActivity.getManager().getPantry().remove(item);
                        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                        groceryList.clear();
                        groceryList.addAll(MenuActivity.getManager().getPantry());
                        groceryListView = (ListView) findViewById(R.id.groceryListView);

                        ArrayList<String> listVOs = new ArrayList<>();
                        for (int i = 0; i < groceryList.size(); i++)
                        {
                            listVOs.add(groceryList.get(i));
                        }

                        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Groceries.this, android.R.layout.simple_list_item_1, listVOs);
                        groceryListView.setAdapter(myAdapter);
                        dialogInterface.cancel();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                //show dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    //Method to add a new material item to the shopping list
    public void addGroceryOnClick(View view) {
        // Build an alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // create a new view that inflates the custom shoppping list layout
        View newView = getLayoutInflater().inflate(R.layout.add_item_layout, null);
        // declaring objects in the layout and linking them to the xml
        final EditText itemName = (EditText) newView.findViewById(R.id.itemText);
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
        // Listen and close the dialog and submits content if user clicks Confirm
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Adds item to ChoreManagerProfile based on their input
                String itemNameString = itemName.getText().toString().trim();
                MenuActivity.getManager().addPantryItem(itemNameString);
                MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                groceryList.clear();
                groceryList.addAll(MenuActivity.getManager().getPantry());
                groceryListView = (ListView) findViewById(R.id.groceryListView);
                ArrayList<String> listVOs = new ArrayList<>();
                for (int i = 0; i < groceryList.size(); i++)
                {
                    listVOs.add(groceryList.get(i));
                }

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Groceries.this, android.R.layout.simple_list_item_1, listVOs);
                groceryListView.setAdapter(myAdapter);
                dialog.cancel();
            }
        });
    }
}
