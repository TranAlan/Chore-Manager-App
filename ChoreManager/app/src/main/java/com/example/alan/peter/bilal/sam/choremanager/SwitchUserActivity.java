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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

/** Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: SwitchUserActivity
 */

public class SwitchUserActivity extends AppCompatActivity {
    private TextView passwordText;
    private User specificUser;
    private ChoreManagerProfile manager = MenuActivity.getManager();
    private static DatabaseReference fbRef = AppLoginActivity.databaseFamilies;
    private static String email = AppLoginActivity.emailEscaped;
    long admins = UserMenu.admins;
    long regs = UserMenu.regs;
    private boolean found = false;
//    @Override
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//        startActivity(new Intent(SwitchUserActivity.this, UserMenu.class));
//        finish();
//
//    }
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
        if(MenuActivity.getManager().isCurrentUserAdmin()&&!(manager.getCurrentUser().getUsername().equals(specificUser.getUsername()))){
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
                fbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!(dataSnapshot.child(email).child("ChoreManager").child("adminUsers").getChildrenCount()==0)){
                            Iterator<DataSnapshot> adminIterator = dataSnapshot.child(email).child("ChoreManager").child("adminUsers").getChildren().iterator();
                            for(int i = 0;i<admins;i++){
                                DatabaseReference tempRef = adminIterator.next().getRef();
                                DataSnapshot tempShot = dataSnapshot.child(email).child("ChoreManager").child("adminUsers").child(tempRef.getKey());
                                if(tempShot.getValue(AdminUser.class).getUsername().equals(specificUser.getUsername())){
                                    tempRef.removeValue();
                                    manager.getAdminUsers().remove(i);
                                    fbRef.child(email).child("ChoreManager").setValue(manager);
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if(!(dataSnapshot.child(email).child("ChoreManager").child("regUsers").getChildrenCount()==0)&&!found){
                            Iterator<DataSnapshot> regIterator = dataSnapshot.child(email).child("ChoreManager").child("regUsers").getChildren().iterator();
                            for(int j = (int)admins;j<(admins+regs);j++){
                                DatabaseReference tempRef = regIterator.next().getRef();
                                DataSnapshot tempShot = dataSnapshot.child(email).child("ChoreManager").child("regUsers").child(tempRef.getKey());
                                if(tempShot.getValue(User.class).getUsername().equals(specificUser.getUsername())){
                                    tempRef.removeValue();
                                    manager.getRegUsers().remove((j-(int)admins));
                                    fbRef.child(email).child("ChoreManager").setValue(manager);
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                fbRef.child(email).child("ChoreManager").child(specificUser.getUsername()).removeValue();
                Toast.makeText(getApplicationContext(), "User Has Been Deleted!",Toast.LENGTH_SHORT).show();
                dialog.cancel();
                //Clear the activity stack and remake MainMenu
                Intent mainIntent = new Intent(SwitchUserActivity.this, MenuActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
                //Send user to User Menu
                startActivity(new Intent(SwitchUserActivity.this, UserMenu.class));
                finish();
            }
        });

    }

    public void cancelSelectedOnClick(View view)
    {
        //Send user to User Menu
        startActivity(new Intent(SwitchUserActivity.this, UserMenu.class));
        finish();
    }
    // if user presses create new User, create this
    public void switchUserOnClick(View view)
    {
        TextView updatedPasswordText= (TextView) findViewById(R.id.passwordText);
        if (updatedPasswordText.getText().toString().equals(specificUser.getPassword().toString()))
        {
            MenuActivity.getManager().setCurrentUserId(specificUser.getUserId());
            fbRef.child(email).child("ChoreManager").setValue(manager);
            Snackbar.make(view, "Account Switched to: "+ specificUser.getUsername(), Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
            startActivity(new Intent(SwitchUserActivity.this, UserMenu.class));
            finish();
        }
        else{
            Snackbar.make(view, "Password Invalid!", Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
        }


    }
}
