package com.example.alan.peter.bilal.sam.choremanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ShoppingListActivity extends AppCompatActivity {

    //variables
    private String toAdd = "";
    private ChoreManagerProfile main = MenuActivity.getManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

    //Method to add a new grocery item to the shopping list
    public void onAddGroceryClick(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Item to add:");
        alert.setTitle("Add a grocery to Shopping List");
        final EditText name = new EditText(this);
        alert.setView(name);

        //buttons
        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
                public void onClick(DialogInterface dialog, int which){
                    toAdd = name.getText().toString();
                    main.addShoppingGrocery(toAdd);
                    //TODO: Find out how to update ChoreManagerProfile
                }
            });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });
        alert.show();
        }

    //Method to add a new Material  to the shopping list
    public void onAddMaterialClock(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Item to add:");
        alert.setTitle("Add a material to Shopping List");
        final EditText name = new EditText(this);
        alert.setView(name);

        //buttons
        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                toAdd = name.getText().toString();
                main.addShoppingMaterial(toAdd);
                //TODO: Find out how to update ChoreManagerProfile
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });
        alert.show();
    }
}