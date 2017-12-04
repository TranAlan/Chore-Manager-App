package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMenu extends AppCompatActivity {

    private List<User> users = new ArrayList<>();
    // declaring vars
    private List<String> userName = new ArrayList<>(Arrays.asList("Peter","Bilal","Sam","Alan"));
    private List<String> roleName  = new ArrayList<>(Arrays.asList("Admin", "Child","Child","Child")) ;
    private  List<Integer> imageIDs = new ArrayList<>(Arrays.asList(R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher));
    private  List<Integer> points = new ArrayList<>(Arrays.asList(10,20,30,40));
    ListView userListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        // link listview to xml
        userListView = (ListView) findViewById(R.id.userListView);
        CustomUserListView customUserListView = new CustomUserListView(this,users);
        userListView.setAdapter(customUserListView);
    }
    protected void createUserOnClick(View view){
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }

}
