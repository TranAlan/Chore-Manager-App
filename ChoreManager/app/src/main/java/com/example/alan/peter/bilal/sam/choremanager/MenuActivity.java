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
    private FirebaseUser fbUser = AppLoginActivity.user;
    private DatabaseReference fbRef = AppLoginActivity.databaseFamilies;

    private String email = AppLoginActivity.emailEscaped;

    //TODO: delete the " = new ChoreManagerProfile();"
    public static ChoreManagerProfile manager = new ChoreManagerProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        //TODO: uncomment this block
       /* fbRef.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild("ChoreManager")) {
                    manager = snapshot.child("ChoreManager").getValue(ChoreManagerProfile.class);
                } else {
                    manager = new ChoreManagerProfile();
                    fbRef.child(email).child("ChoreManager").setValue(manager);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */

        AdminUser peter = new AdminUser("Peter Lam", "qwerty");
        //TODO: comment these out
        manager.setCurrentUser(peter);
        manager.addUser(peter);
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
        //To pass:
        // intent = intent.putExtra("Manager", manager);

        // To retrieve object in second Activity
        //getIntent().getSerializableExtra("MyClass");
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
}