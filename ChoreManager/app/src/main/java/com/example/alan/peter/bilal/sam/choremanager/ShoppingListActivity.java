package com.example.alan.peter.bilal.sam.choremanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }


    public void onCreateItemPress(View view) {
        String[] choices = {"Material","Grocery"};
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Create an item to add to the shopping list.").setItems(choices, new DialogInterface.OnClickListener() {
            @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        alert.setPositiveButton("O0k", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
            @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        alert.show();

    }
}

