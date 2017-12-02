package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //variables
    private String toAdd;
    private List<String> allMaterials = new ArrayList<>();
    private List<String> allGroceries = new ArrayList<>();
    private ListView groceriesListView,materialsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        //clearing data incase
        allGroceries.clear();
        allMaterials.clear();


        //TO REMOVE
        allGroceries.add("Groceries 1");
        allGroceries.add("Groceries 2");
        allGroceries.add("Groceries 3");
        allMaterials.add("Materials 1");
        allMaterials.add("Materials 2");
        allMaterials.add("Materials 3");
        // get all the materials from pantry and material list
        // allMaterials.addAll();
        //allGroceries.addAll();
        // link listview to xml

        groceriesListView = (ListView) findViewById(R.id.groceriesListView);
        materialsListView = (ListView) findViewById(R.id.materialsListView);
        ArrayList<StateVO> listVOs = new ArrayList<>();
        for (int i = 0; i < allGroceries.size(); i++)
        {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(allGroceries.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        CustomMaterialListAdapter myAdapter = new CustomMaterialListAdapter(ShoppingListActivity.this, 0,
                listVOs);
        groceriesListView.setAdapter(myAdapter);

        ArrayList<StateVO> listVOs2 = new ArrayList<>();
        for (int i = 0; i < allMaterials.size(); i++)
        {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(allMaterials.get(i));
            stateVO.setSelected(false);
            listVOs2.add(stateVO);
        }

        CustomMaterialListAdapter myAdapter2 = new CustomMaterialListAdapter(ShoppingListActivity.this, 0,
                listVOs2);
        materialsListView.setAdapter(myAdapter2);
    }

    //Method to add a new grocery item to the shopping list
    public void addItemOnClick(View view) {

        // Build an alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // create a new view that inflates the custom shoppping list layout
        View newView = getLayoutInflater().inflate(R.layout.new_shopinglist_item_layout, null);
        // declaring objects in the layout and linking them to the xml
        final EditText itemName = (EditText) newView.findViewById(R.id.itemText);
        final Spinner materialSpinner = (Spinner) newView.findViewById(R.id.spinner);
        final ArrayAdapter spinnerAdapter;
        final Button cancelButton = (Button) newView.findViewById(R.id.cancelButton);
        final Button confirmButton = (Button) newView.findViewById(R.id.confirmButton);
        // create adapter from string array in string.xml file for RepeatableSpinner
        spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.shoppingItemTypeSpinner_Options,android.R.layout.simple_spinner_item);
        // set spinner to the one the the xml
        materialSpinner.setAdapter(spinnerAdapter);
        // listen if the spinner is clicked
        materialSpinner.setOnItemSelectedListener(ShoppingListActivity.this);
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
                toAdd = itemName.getText().toString();
                MenuActivity.getManager().addShoppingMaterial(toAdd);
                dialog.cancel();
                //TODO: Get information from spinner aka ALAN TRAN
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}