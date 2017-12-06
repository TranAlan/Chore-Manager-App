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
import android.widget.Spinner;

import java.util.ArrayList;

/** Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: ShoppingListActivity
 */

public class ShoppingListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //variables
    private String toAdd;
    private ArrayList<String> allMaterials = new ArrayList<>();
    private ArrayList<String> allGroceries = new ArrayList<>();
    private ListView groceriesListView,materialsListView;


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if( item.getItemId() == android.R.id.home ){
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        //clearing data incase
        allGroceries.clear();
        allMaterials.clear();

        // get all the materials from pantry and material list
         allMaterials.addAll(MenuActivity.getManager().getShopListMat());
         allGroceries.addAll(MenuActivity.getManager().getShoplistGroc());

        // link listview to xml
        groceriesListView = (ListView) findViewById(R.id.groceriesListView);
        materialsListView = (ListView) findViewById(R.id.materialsListView);
        groceriesListView.setItemsCanFocus(true);
        materialsListView.setItemsCanFocus(true);

        groceriesListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                final String item = (String) groceriesListView.getItemAtPosition(position);

                //create an alert
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingListActivity.this);
                builder.setTitle("Item Selected");
                builder.setMessage("What would you like to do with this selected item?");

                //buttons

                builder.setPositiveButton("Purchase Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MenuActivity.getManager().getShoplistGroc().remove(item);
                        MenuActivity.getManager().getPantry().add(item);
                        allGroceries.clear();
                        allGroceries.addAll(MenuActivity.getManager().getShoplistGroc());
                        //creates a list of the groceries on the shopping list
                        ArrayList<String> listVOs = new ArrayList<>();
                        for (int j = 0; j < allGroceries.size(); j++)
                        {
                            listVOs.add(allGroceries.get(j));
                        }
                        //creates a list of the materials on the shopping list
                        ArrayList<String> listVOs2 = new ArrayList<>();
                        for (int j = 0; j < allMaterials.size(); j++)
                        {
                            listVOs2.add(allMaterials.get(j));
                        }
                        ArrayAdapter<String> myNewAdapter = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs);
                        groceriesListView.setAdapter(myNewAdapter);
                        ArrayAdapter<String> myNewAdapter2 = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs2);
                        materialsListView.setAdapter(myNewAdapter2);
                        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                    }
                });
                builder.setNegativeButton("Delete Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MenuActivity.getManager().getShoplistGroc().remove(item);
                        allGroceries.clear();
                        allGroceries.addAll(MenuActivity.getManager().getShoplistGroc());
                        //creates a list of the groceries on the shopping list
                        ArrayList<String> listVOs = new ArrayList<>();
                        for (int j = 0; j < allGroceries.size(); j++)
                        {
                            listVOs.add(allGroceries.get(j));
                        }
                        //creates a list of the materials on the shopping list
                        ArrayList<String> listVOs2 = new ArrayList<>();
                        for (int j = 0; j < allMaterials.size(); j++)
                        {
                            listVOs2.add(allMaterials.get(j));
                        }
                        ArrayAdapter<String> myNewAdapter = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs);
                        groceriesListView.setAdapter(myNewAdapter);
                        ArrayAdapter<String> myNewAdapter2 = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs2);
                        materialsListView.setAdapter(myNewAdapter2);
                        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
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


        materialsListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
               final String item = (String) materialsListView.getItemAtPosition(position);

                //create an alert
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingListActivity.this);
                builder.setTitle("Item Selected");
                builder.setMessage("What would you like to do with this selected item?");

                //buttons

                builder.setPositiveButton("Purchase Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MenuActivity.getManager().getShopListMat().remove(item);
                        MenuActivity.getManager().getMaterials().add(item);
                        allMaterials.clear();
                        allMaterials.addAll(MenuActivity.getManager().getShopListMat());
                        //creates a list of the groceries on the shopping list
                        ArrayList<String> listVOs = new ArrayList<>();
                        for (int j = 0; j < allGroceries.size(); j++)
                        {
                            listVOs.add(allGroceries.get(j));
                        }
                        //creates a list of the materials on the shopping list
                        ArrayList<String> listVOs2 = new ArrayList<>();
                        for (int j = 0; j < allMaterials.size(); j++)
                        {
                            listVOs2.add(allMaterials.get(j));
                        }
                        ArrayAdapter<String> myNewAdapter = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs);
                        groceriesListView.setAdapter(myNewAdapter);
                        ArrayAdapter<String> myNewAdapter2 = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs2);
                        materialsListView.setAdapter(myNewAdapter2);
                        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                    }
                });
                builder.setNegativeButton("Delete Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MenuActivity.getManager().getShopListMat().remove(item);
                        allMaterials.clear();
                        allMaterials.addAll(MenuActivity.getManager().getShopListMat());
                        //creates a list of the groceries on the shopping list
                        ArrayList<String> listVOs = new ArrayList<>();
                        for (int j = 0; j < allGroceries.size(); j++)
                        {
                            listVOs.add(allGroceries.get(j));
                        }
                        //creates a list of the materials on the shopping list
                        ArrayList<String> listVOs2 = new ArrayList<>();
                        for (int j = 0; j < allMaterials.size(); j++)
                        {
                            listVOs2.add(allMaterials.get(j));
                        }
                        ArrayAdapter<String> myNewAdapter = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs);
                        groceriesListView.setAdapter(myNewAdapter);
                        ArrayAdapter<String> myNewAdapter2 = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs2);
                        materialsListView.setAdapter(myNewAdapter2);
                        MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
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
        //creates a list of the groceries on the shopping list
        ArrayList<String> listVOs = new ArrayList<>();
        for (int i = 0; i < allGroceries.size(); i++)
        {
            listVOs.add(allGroceries.get(i));
        }
        //creates a list of the materials on the shopping list
        ArrayList<String> listVOs2 = new ArrayList<>();
        for (int i = 0; i < allMaterials.size(); i++)
        {
            listVOs2.add(allMaterials.get(i));
        }
        ArrayAdapter<String> myNewAdapter = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs);
        groceriesListView.setAdapter(myNewAdapter);
        ArrayAdapter<String> myNewAdapter2 = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs2);
        materialsListView.setAdapter(myNewAdapter2);
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
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.shoppingItemTypeSpinner_Options, android.R.layout.simple_spinner_item);
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
                        allMaterials.add(itemNameString);
                    }
                    else{
                        MenuActivity.getManager().addShoppingGrocery(itemNameString);
                        allGroceries.add(itemNameString);
                    }
                    MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                }
                //creates a list of the groceries on the shopping list
                ArrayList<String> listVOs = new ArrayList<>();
                for (int i = 0; i < allGroceries.size(); i++)
                {
                    listVOs.add(allGroceries.get(i));
                }
                //creates a list of the materials on the shopping list
                ArrayList<String> listVOs2 = new ArrayList<>();
                for (int i = 0; i < allMaterials.size(); i++)
                {
                    listVOs2.add(allMaterials.get(i));
                }
                ArrayAdapter<String> myNewAdapter = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs);
                groceriesListView.setAdapter(myNewAdapter);
                ArrayAdapter<String> myNewAdapter2 = new ArrayAdapter<String>(ShoppingListActivity.this, android.R.layout.simple_list_item_1, listVOs2);
                materialsListView.setAdapter(myNewAdapter2);
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