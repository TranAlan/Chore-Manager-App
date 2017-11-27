package com.example.alan.peter.bilal.sam.choremanager;

import java.util.ArrayList;
import android.media.Image;

/**
 * Created by Alan on 11/27/2017.
 */

public class User {
    String username;
    String password;
    int totalPoints;
    Image profilePic;
    ArrayList<Chore> assignedChores;

    public User(){
        username = null;
        password = null;
        totalPoints = 0;
        profilePic = null;
        assignedChores = new ArrayList<Chore>();
    }
    public User(String username, String password, int totalPoints, Image profilePic){
        this.username = username;
        this.password = password;
        this.totalPoints = 0;
        this.profilePic = profilePic;
        assignedChores = new ArrayList<Chore>();
    }


}
