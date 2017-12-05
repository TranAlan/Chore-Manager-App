package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMenu extends AppCompatActivity {

    // declaring vars
    private List<User> users = new ArrayList<>();
    ListView userListView;
    private DatabaseReference fbRef = AppLoginActivity.databaseFamilies;
    private String email = AppLoginActivity.emailEscaped;
    long admins = 0;
    long regs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        //Listeners for reading users from database
        fbRef.addListenerForSingleValueEvent(countListener);
        fbRef.addListenerForSingleValueEvent(usersListener);
    }

    protected void onCreateUser(View view){
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }

    public void func(List<User> users) {
        // link listview to xml
        userListView = (ListView) findViewById(R.id.userListView);
        CustomUserListView customUserListView = new CustomUserListView(this,users);
        userListView.setAdapter(customUserListView);
    }

    private ValueEventListener countListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            admins = dataSnapshot.child(email).child("ChoreManager").child("adminUsers").getChildrenCount();
            regs = dataSnapshot.child(email).child("ChoreManager").child("regUsers").getChildrenCount();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    private ValueEventListener usersListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(!(dataSnapshot.child(email).child("ChoreManager").child("adminUsers").getChildrenCount()==0)){
                Iterator<DataSnapshot> adminIterator = dataSnapshot.child(email).child("ChoreManager").child("adminUsers").getChildren().iterator();
                for(int i = 0;i<admins;i++){
                    users.add(adminIterator.next().getValue(AdminUser.class));
                }
            }
            if(!(dataSnapshot.child(email).child("ChoreManager").child("regUsers").getChildrenCount()==0)){
                Iterator<DataSnapshot> regIterator = dataSnapshot.child(email).child("ChoreManager").child("regUsers").getChildren().iterator();
                for(int j = (int)admins;j<(admins+regs);j++){
                    users.add(regIterator.next().getValue(User.class));
                }
            }
            func(users);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}