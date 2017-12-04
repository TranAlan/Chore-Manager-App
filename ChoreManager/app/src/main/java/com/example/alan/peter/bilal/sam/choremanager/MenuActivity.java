package com.example.alan.peter.bilal.sam.choremanager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    //https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
    public static ChoreManagerProfile manager;
    private static FirebaseUser fbUser = AppLoginActivity.user;
    private static DatabaseReference fbRef = AppLoginActivity.databaseFamilies;
    private static String email = AppLoginActivity.emailEscaped;
    private ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d("test", "On data change");
            if (dataSnapshot.hasChild("ChoreManager")) {
                manager = dataSnapshot.child("ChoreManager").getValue(ChoreManagerProfile.class);
            } else {
                manager = new ChoreManagerProfile();
                int userId = manager.nextSerialNumber();
                AdminUser peter = new AdminUser("Peter Lam", "qwerty", userId);
                manager.setCurrentUserId(userId);
                manager.addAdminUser(peter);
                fbRef.child(email).child("ChoreManager").setValue(manager);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        fbRef.child(email).addListenerForSingleValueEvent(listener);
    }

    protected void userImageOnClick(View view){
        Intent intent = new Intent(this, UserMenu.class);
        startActivity(intent);
    }
    protected void settingImageOnClick(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    protected void materialsImageOnClick(View view){
        Intent intent = new Intent(this, MaterialsActivity.class);
        startActivity(intent);
    }


    protected void choreListImageOnClick(View view){
        Intent intent = new Intent(this, ChoreListActivity.class);
        //fbRef.child(email).child("ChoreManager").setValue(manager);
        startActivity(intent);

    }

    public static ChoreManagerProfile getManager(){
        return manager;
    }

    protected void groceriesImageOnClick(View view){
        Intent intent = new Intent(this, Groceries.class);
        startActivity(intent);
    }

    protected void shoppingImageOnClick(View view){
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    protected static DatabaseReference getFbRef(){
        return fbRef;
    }

    protected static String getEmail(){
        return email;
    }

    protected static FirebaseUser getFbUser(){
        return fbUser;
    }
}