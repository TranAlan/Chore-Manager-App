package com.example.alan.peter.bilal.sam.choremanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }


    public Dialog onCreateItemPress() {
        String[] choices = {"Material", "Grocery"};
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Create an item to add to the shopping list.").setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return alert.create();
    }
}

