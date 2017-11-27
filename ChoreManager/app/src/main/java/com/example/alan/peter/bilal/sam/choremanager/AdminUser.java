package com.example.alan.peter.bilal.sam.choremanager;

import android.media.Image;

import java.util.Date;

/**
 * Created by Alan on 11/27/2017.
 */

public class AdminUser extends User{

    public AdminUser(String username, String password, int totalPoints, Image profilePic){
        super(username, password, totalPoints, profilePic);
    }

    public Chore createChore(String name, String description, String note, int pointsWorth, Repeated isRepeated, Date deadline){
        return new Chore(name, description, note, pointsWorth, isRepeated, deadline);
    }

    public void assignChore(User user, Chore chore){
        user.addToAssignedChores(chore);
        chore.setAssignedTo(user);
        //Remember about ChoreManagerProfile
    }

    public void deAssignChore(Chore chore){
        chore.getAssignedTo().removeFromAssignedChores(chore);
        chore.setAssignedTo(null);
        //add to unassigned list?
    }

    public void deleteChore(Chore chore){

        if(chore.getAssignedTo()!= null){
            deAssignChore(chore);
        }
        //remove from ChoreManagerProfile
        

    }
}

