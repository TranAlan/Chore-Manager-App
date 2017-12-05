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

public class ShoppingListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //variables
    private String toAdd;
    private ArrayList<String> allMaterials = new ArrayList<>();
    private ArrayList<String> allGroceries = new ArrayList<>();
    private ListView groceriesListView,materialsListView;
    private ArrayList<String> checkedMaterials = new ArrayList<String>();
    private ArrayList<String> checkedGroceries = new ArrayList<String>();

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

        CustomMaterialListAdapter myAdapter2 = new CustomMaterialListAdapter(ShoppingListActivity.this, 0, listVOs2);
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
                allGroceries.clear();
                allMaterials.clear();
                allMaterials.addAll(MenuActivity.getManager().getShopListMat());
                allGroceries.addAll(MenuActivity.getManager().getShoplistGroc());
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

                CustomMaterialListAdapter myAdapter2 = new CustomMaterialListAdapter(ShoppingListActivity.this, 0, listVOs2);
                materialsListView.setAdapter(myAdapter2);
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


    public void onDeletionClick(View view) {
        // Build an alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // create a new view that inflates the custom shoppping list layout
        View ts = getLayoutInflater().inflate(R.layout.delete_shopinglist_item_layout, null);
        // declaring objects in the layout and linking them to the xml
        final Button cancelButton = (Button) ts.findViewById(R.id.cancelButton);
        final Button confirmButton = (Button) ts.findViewById(R.id.confirmButton);

        alert.setView(ts);
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
                //TODO: Implement

                //clearing data incase
                allGroceries.clear();
                allMaterials.clear();


                // get all the materials from pantry and material list
                allMaterials.addAll(MenuActivity.getManager().getShopListMat());
                allGroceries.addAll(MenuActivity.getManager().getShoplistGroc());
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

                CustomMaterialListAdapter myAdapter2 = new CustomMaterialListAdapter(ShoppingListActivity.this, 0, listVOs2);
                materialsListView.setAdapter(myAdapter2);


                MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                //update view
                groceriesListView.invalidate();
                materialsListView.invalidate();
                // close dialog
                dialog.cancel();
            }
        });
    }


    public void onPurchaseClick(View view) {
        // Build an alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // create a new view that inflates the custom shoppping list layout
        View ts = getLayoutInflater().inflate(R.layout.delete_shopinglist_item_layout, null);
        // declaring objects in the layout and linking them to the xml
        final Button cancelButton = (Button) ts.findViewById(R.id.cancelButton);
        final Button confirmButton = (Button) ts.findViewById(R.id.confirmButton);

        alert.setView(ts);
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
                //TODO: Implement

                //clearing data incase
                allGroceries.clear();
                allMaterials.clear();


                // get all the materials from pantry and material list
                allMaterials.addAll(MenuActivity.getManager().getShopListMat());
                allGroceries.addAll(MenuActivity.getManager().getShoplistGroc());
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

                CustomMaterialListAdapter myAdapter2 = new CustomMaterialListAdapter(ShoppingListActivity.this, 0, listVOs2);
                materialsListView.setAdapter(myAdapter2);


                MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                //update view
                groceriesListView.invalidate();
                materialsListView.invalidate();
                // close dialog
                dialog.cancel();
            }
        });
    }
}