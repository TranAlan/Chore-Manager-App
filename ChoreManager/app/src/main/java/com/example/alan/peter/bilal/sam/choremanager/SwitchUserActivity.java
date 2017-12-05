package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
