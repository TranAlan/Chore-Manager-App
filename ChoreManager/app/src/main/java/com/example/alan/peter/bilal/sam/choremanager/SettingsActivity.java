package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.alan.peter.bilal.sam.choremanager.R.string.menuText;

public class SettingsActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinnerDropdownTheme, spinnerDropDownUsers;
    ArrayAdapter themeAdapter, userAdapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeAdapter = ArrayAdapter.createFromResource(this, R.array.themeSpinner_Options, android.R.layout.simple_spinner_item);
        spinnerDropdownTheme = (Spinner) findViewById(R.id.themeSpinner);
        spinnerDropdownTheme.setAdapter(themeAdapter);
        spinnerDropdownTheme.setOnItemSelectedListener(SettingsActivity.this);

        userAdapter = ArrayAdapter.createFromResource(this, R.array.userSpinner_Options, android.R.layout.simple_spinner_item);
        spinnerDropDownUsers = (Spinner) findViewById(R.id.userSpinner);
        spinnerDropDownUsers.setAdapter(userAdapter);
        spinnerDropDownUsers.setOnItemSelectedListener(SettingsActivity.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, "You Selected: " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    protected void resetDataOnClick(View view) {
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
                MenuActivity.getManager().resetAppData();
                int userId = MenuActivity.getManager().nextSerialNumber();
                AdminUser peter = new AdminUser("Peter Lam", "qwerty", userId);
                MenuActivity.getManager().setCurrentUserId(userId);
                MenuActivity.getManager().addAdminUser(peter);
                MenuActivity.getFbRef().child(MenuActivity.getEmail()).child("ChoreManager").setValue(MenuActivity.getManager());
                dialog.cancel();
            }
        });
    }
}
