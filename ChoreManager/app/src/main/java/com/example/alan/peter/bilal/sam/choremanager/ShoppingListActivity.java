package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ShoppingListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //variables
    private String toAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

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
                //Adds item to ChoreManagerProfile based on their input
                String itemNameString = itemName.getText().toString().trim();
                String shoppingListType = (String) materialSpinner.getSelectedItem();
                if(!(itemNameString.equals(""))){
                    if (shoppingListType.equals("Material")){
                        MenuActivity.getManager().addShoppingMaterial(itemNameString);
                    }
                    else{
                        MenuActivity.getManager().addShoppingGrocery(itemNameString);
                    }
                }
                dialog.cancel();
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