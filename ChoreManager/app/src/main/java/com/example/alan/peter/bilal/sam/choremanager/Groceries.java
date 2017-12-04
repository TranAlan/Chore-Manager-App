package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Groceries extends AppCompatActivity {


    //variables
    private ArrayList<String> selected = new ArrayList<>();
    private ArrayList<String> groceryList = new ArrayList<>();
    private ListView groceryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);

        // get all the items from pantry
        groceryList.addAll(MenuActivity.getManager().getPantry());
        groceryListView = (ListView) findViewById(R.id.groceryListView);
        ArrayList<StateVO> listVOs = new ArrayList<>();
        for (int i = 0; i < groceryList.size(); i++)
        {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(groceryList.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        CustomMaterialListAdapter myAdapter = new CustomMaterialListAdapter(Groceries.this, 0, listVOs);
        groceryListView.setAdapter(myAdapter);
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

                groceryList.add(itemNameString);
                groceryList.clear();
                groceryList.addAll(MenuActivity.getManager().getPantry());
                groceryListView = (ListView) findViewById(R.id.groceryListView);
                ArrayList<StateVO> listVOs = new ArrayList<>();
                for (int i = 0; i < groceryList.size(); i++)
                {
                    StateVO stateVO = new StateVO();
                    stateVO.setTitle(groceryList.get(i));
                    stateVO.setSelected(false);
                    listVOs.add(stateVO);
                }

                CustomMaterialListAdapter myAdapter = new CustomMaterialListAdapter(Groceries.this, 0, listVOs);
                groceryListView.setAdapter(myAdapter);
                dialog.cancel();
            }
        });
    }

    public void deleteSelectedOnClick(View view) {
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
                //delete items from material list
                MenuActivity.getManager().getPantry().removeAll(selected);
                //update view
                //TODO
                //close dialog
                dialog.cancel();
            }
        });
    }







    }
