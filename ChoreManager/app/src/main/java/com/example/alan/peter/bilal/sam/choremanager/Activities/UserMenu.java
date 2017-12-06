package com.example.alan.peter.bilal.sam.choremanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alan.peter.bilal.sam.choremanager.Classes.AdminUser;
import com.example.alan.peter.bilal.sam.choremanager.CustomAdapters.CustomUserListView;
import com.example.alan.peter.bilal.sam.choremanager.R;
import com.example.alan.peter.bilal.sam.choremanager.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserMenu extends AppCompatActivity {

    // declaring vars
    private List<User> users = new ArrayList<>();
    private ListView userListView;
    private DatabaseReference fbRef = AppLoginActivity.databaseFamilies;
    private String email = AppLoginActivity.emailEscaped;
    private TextView currentUserTV;
    static long admins = 0;
    static long regs = 0;
    private CustomUserListView customUserListView;

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        userListView = (ListView) findViewById(R.id.userListView);
        currentUserTV.setText(MenuActivity.getManager().getCurrentUser().getUsername());
        customUserListView = new CustomUserListView(this,users);
        userListView.setAdapter(customUserListView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        //Listeners for reading users from database
        fbRef.addListenerForSingleValueEvent(countListener);
        fbRef.addListenerForSingleValueEvent(usersListener);

        // link listview to xml and title for current user
        currentUserTV = (TextView) findViewById(R.id.currentUserTV);

        userListView = (ListView) findViewById(R.id.userListView);
        currentUserTV.setText(MenuActivity.getManager().getCurrentUser().getUsername());

        customUserListView = new CustomUserListView(this,users);
        userListView.setAdapter(customUserListView);
        //creating ability to swipe and refresh
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        // refresh the adapter that shows all users
                        ((CustomUserListView) userListView.getAdapter()).notifyDataSetChanged();

                    }
                },3000);
            }
        });
        // calling custom chore view to display all the choews
        userListView.setClickable(true);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //If click on a user, SwitchUser Activity Runs
                User specificUser = customUserListView.getItem(position);
                Intent intent = new Intent(getBaseContext(), SwitchUserActivity.class);
                intent = intent.putExtra("UserSwitch", specificUser ); // passes chore to next activity
                //finish();
                startActivity(intent);

            }
        });

        ((CustomUserListView) userListView.getAdapter()).notifyDataSetChanged();

    }

    protected void onCreateUser(View view){
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
        ((CustomUserListView) userListView.getAdapter()).notifyDataSetChanged();
        finish();
    }
    private ValueEventListener countListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            admins = dataSnapshot.child(email).child("ChoreManager").child("adminUsers").getChildrenCount();
            regs = dataSnapshot.child(email).child("ChoreManager").child("regUsers").getChildrenCount();
            ((CustomUserListView) userListView.getAdapter()).notifyDataSetChanged();
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
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


}