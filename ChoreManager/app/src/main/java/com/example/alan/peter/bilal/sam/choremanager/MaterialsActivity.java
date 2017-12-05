package com.example.alan.peter.bilal.sam.choremanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MaterialsActivity extends AppCompatActivity {
    //variables
    private ArrayList<String> selected = new ArrayList<String>();
    private ArrayList<String> materialList = new ArrayList<>();
    private ListView materialsListView;

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
        setContentView(R.layout.activity_materials);
        materialList.addAll(MenuActivity.getManager().getMaterials());
        materialsListView = (ListView) findViewById(R.id.materialsListView);

        materialsListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String item = (String) materialsListView.getItemAtPosition(position);

                //create an alert
                AlertDialog.Builder builder = new AlertDialog.Builder(MaterialsActivity.this);
                builder.setTitle("Item Selected");
                builder.setMessage("What would you like to do with this selected item?");

                //buttons
                builder.setNeutralButton("Delete Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //IMPLEMENT
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                //create and show
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        ArrayList<StateVO> listVOs = new ArrayList<>();
        for (int i = 0; i < materialList.size(); i++)
        {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(materialList.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        CustomMaterialListAdapter myAdapter = new CustomMaterialListAdapter(MaterialsActivity.this, 0, listVOs);
        materialsListView.setAdapter(myAdapter);
    }

    //Method to add a new material item to the shopping list
    public void addMaterialOnClick(View view) {
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
                MenuActivity.getManager().addMaterial(itemNameString);
                MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                materialList.add(itemNameString);
                materialList.clear();
                materialList.addAll(MenuActivity.getManager().getMaterials());
                materialsListView = (ListView) findViewById(R.id.materialsListView);
                ArrayList<StateVO> listVOs = new ArrayList<>();
                for (int i = 0; i < materialList.size(); i++)
                {
                    StateVO stateVO = new StateVO();
                    stateVO.setTitle(materialList.get(i));
                    stateVO.setSelected(false);
                    listVOs.add(stateVO);
                }

                CustomMaterialListAdapter myAdapter = new CustomMaterialListAdapter(MaterialsActivity.this, 0, listVOs);
                materialsListView.setAdapter(myAdapter);
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
                MenuActivity.getManager().getMaterials().removeAll(selected);
                //update view
                //TODO
                //close dialog
                dialog.cancel();
            }
        });
    }
}
