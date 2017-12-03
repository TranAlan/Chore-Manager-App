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
    //TODO: delete the " = new ChoreManagerProfile();"
    public static ChoreManagerProfile manager = new ChoreManagerProfile();
    private FirebaseUser fbUser = AppLoginActivity.user;
    private DatabaseReference fbRef = AppLoginActivity.databaseFamilies;
    private String email = AppLoginActivity.emailEscaped;
    private ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d("test", "HELLO!");
            manager = dataSnapshot.child("ChoreManager").getValue(ChoreManagerProfile.class);
            if (dataSnapshot.hasChild("ChoreManager")) {
                manager = dataSnapshot.child("ChoreManager").getValue(ChoreManagerProfile.class);
                Log.d("test", "TSHIRT");
            } else {
                manager = new ChoreManagerProfile();
                //fbRef.child(email).child("ChoreManager").setValue(manager);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("test", "START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        //TODO: uncomment this block
        fbRef.child(email).addValueEventListener(listener);

        //AdminUser peter = new AdminUser("Peter Lam", "qwerty");
        //TODO: comment these out
        //Log.d("test",manager.getCurrentUser().getUsername());
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
        AdminUser peter = new AdminUser("Peter Lam", "qwerty");
        manager.setCurrentUser(peter);
        manager.addUser(peter);
        fbRef.child(email).child("ChoreManager").setValue(manager);

        if (manager == null){
            Log.d("test", "null!");
        }
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