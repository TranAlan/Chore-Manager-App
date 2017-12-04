package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class SpecialAdminUserCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_admin_user_creation);
    }
    public void setAvatarButtonOnClick(View view)
    {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), ChooseProfilePictureActivity.class);
        startActivityForResult (intent,0);
    }
    // Adapted from SEG 2105 Lab - Sports Manager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.ic_avatar_11);
        //Figuring out the correct image
        String drawableName = "ic_avatar_11_2";
        switch (data.getIntExtra("imageID",R.id.ic_avatar_11)) {
            case R.id.ic_avatar_11:
                drawableName = "ic_avatar_11_2";
                break;
            case R.id.ic_avatar_00:
                drawableName = "ic_avatar_00_2";
                break;
            case R.id.ic_avatar_01:
                drawableName = "ic_avatar_01_2";
                break;
            case R.id.ic_avatar_02:
                drawableName = "ic_avatar_02_2";
                break;
            case R.id.ic_avatar_03:
                drawableName = "ic_avatar_03_2";
                break;
            case R.id.ic_avatar_04:
                drawableName = "ic_avatar_04_2";
                break;
            case R.id.ic_avatar_05:
                drawableName = "ic_avatar_05_2";
                break;
            case R.id.ic_avatar_06:
                drawableName = "ic_avatar_06_2";
                break;
            case R.id.ic_avatar_07:
                drawableName = "ic_avatar_07_2";
                break;
            case R.id.ic_avatar_08:
                drawableName = "ic_avatar_08_2";
                break;
            case R.id.ic_avatar_09:
                drawableName = "ic_avatar_09_2";
                break;
            case R.id.ic_avatar_10:
                drawableName = "ic_avatar_10_2";
                break;
            default:
                drawableName = "ic_avatar_11_2";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable",
                getPackageName());
        avatarImage.setImageResource(resID);
    }

    // if user presses create new User, create this
    public void createUserOnClick(View view)
    {
        finish();
        //TODO: Code This @Bilal
    }
}
