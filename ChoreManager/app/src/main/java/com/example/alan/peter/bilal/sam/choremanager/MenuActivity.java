package com.example.alan.peter.bilal.sam.choremanager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    //https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
public static ChoreManagerProfile manager = new ChoreManagerProfile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        manager = new ChoreManagerProfile();
        manager.setCurrentUser(new User());
        manager.getCurrentUser().setUsername("Alan");
       // Log.d("cooltag",cool.getCurrentUser().getUsername());
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
        intent = intent.putExtra("Manager", manager);

        // To retrieve object in second Activity
        //getIntent().getSerializableExtra("MyClass");
        startActivity(intent);

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