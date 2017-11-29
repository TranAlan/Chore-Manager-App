package com.example.alan.peter.bilal.sam.choremanager;

import android.media.Image;

/**
 * Created by Alan on 11/27/2017.
 */

public class AdminUser extends User{

    public AdminUser(String username, String password, int totalPoints, Image profilePic){
        super(username, password, totalPoints, profilePic);
    }
    
    public void assignChore(User user, Chore chore){
        user.addToAssignedChores(chore);
        chore.setAssignedTo(user);
        chore.setStatusActive();
        //Remember about ChoreManagerProfile
    }

    public void deAssignChore(Chore chore){
        chore.getAssignedTo().removeFromAssignedChores(chore);
        chore.setAssignedTo(null);
        chore.setStatusUnassigned();
        //add to unassigned list?
    }

    public void deleteChore(Chore chore){

        if(chore.getAssignedTo()!= null){
            deAssignChore(chore);
        }
        //remove from ChoreManagerProfile


    }
}

