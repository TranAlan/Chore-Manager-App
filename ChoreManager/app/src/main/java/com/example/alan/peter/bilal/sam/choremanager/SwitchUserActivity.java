package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SwitchUserActivity extends AppCompatActivity {
    private TextView passwordText;
    private User specificUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);
        // To retrieve object in second Activity
        Intent i = getIntent();
        specificUser = (User) i.getSerializableExtra("UserSwitch");


        //Display UserName
        TextView usernameText = (TextView) findViewById(R.id.usernameText);
        usernameText.setText(specificUser.getUsername());

        //Display UserName
        ImageView ic_avatar_11 = (ImageView) findViewById(R.id.ic_avatar_11);
        ic_avatar_11.setImageResource(specificUser.getImageID());

        //Create Textview for password
        passwordText= (TextView) findViewById(R.id.passwordText);

        Button deleteUser = (Button) findViewById(R.id.deleteUser);

        //If Current User is Admin, show button
        if(MenuActivity.getManager().isCurrentUserAdmin()){
            deleteUser.setVisibility(View.VISIBLE);
        }
        else{
            deleteUser.setVisibility(View.GONE);
        }

    }

    public void deleteUserOnClick(View view) {
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
                //TODO: Delete The User from database. MenuActivity.getManager().... the user ur looking to delete is calls "specificUser"
                Toast.makeText(getApplicationContext(), "User Has Been Deleted!",Toast.LENGTH_SHORT).show();
                dialog.cancel();
                finish();
            }
        });

    }
    public void cancelSelectedOnClick(View view)
    {
        finish();
    }
    // if user presses create new User, create this
    public void switchUserOnClick(View view)
    {
        if (passwordText.getText() == specificUser)
        {
            //TODO @Bilal Set Current user To this
        }
        finish();

    }
}
