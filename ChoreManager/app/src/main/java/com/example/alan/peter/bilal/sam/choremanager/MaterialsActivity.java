package com.example.alan.peter.bilal.sam.choremanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MaterialsActivity extends AppCompatActivity {

    //variables
    private String toAdd;
    private ArrayList<String> selected = new ArrayList<String>();
    private ArrayList<String> materialList = new ArrayList<>();
    private ListView materialsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        // DO NOT REMOVE
        materialList.add("");
        //TO REMOVE
        materialList.add("Materials 1");
        materialList.add("Materials 2");
        materialList.add("Materials 3");
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
                dialog.cancel();
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
        //if user clicks confirm the selected items are deleted from material list
        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //delete items from material list
                MenuActivity.getManager().getMaterials().removeAll(selected);
                //update view
                //TODO
                //close dialog
                dialogInterface.cancel();
            }
        });
    }
}
