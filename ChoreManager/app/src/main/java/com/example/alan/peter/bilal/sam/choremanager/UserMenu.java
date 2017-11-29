package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class UserMenu extends AppCompatActivity {

    // declaring vars
    ListView userListView;
    String [] userName = {"Peter","Bilal","Sam","Alan"};
    String [] roleName = {"Admin", "Child","Child","Child"};
    Integer[] imageIDs = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        // link listview to xml
        userListView = (ListView) findViewById(R.id.userListView);
        CustomUserListView customUserListView = new CustomUserListView(this,userName, roleName, imageIDs);
        userListView.setAdapter(customUserListView);


    }
}
