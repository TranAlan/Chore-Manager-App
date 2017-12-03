package com.example.alan.peter.bilal.sam.choremanager;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Alan on 11/27/2017.
 */

public class AdminUser extends User{

    public AdminUser(){
        super();
    }
    public AdminUser(String username, String password, int userId){
        super(username, password, userId);
    }

    public Chore createChore(String name, String desc, String note, int points, Date due, ArrayList materials, ArrayList groceries, int choreId, User user){
        Chore chore = new Chore(name, desc, note, points, due, materials, groceries, choreId, user.getUserId());
        if (user!= null){
            assignChore(user, chore);
        }
        return chore;
    }

    public Chore createUnAssignedChore(String name, String desc, String note, int points, Date due, ArrayList materials, ArrayList groceries, int choreId ){
        return new Chore(name, desc, note, points, due, materials, groceries, choreId);
    }

    //UNIQUE PUBLIC METHODS
    public void assignChore(User user, Chore chore){
        user.addToAssignedChores(chore);
        chore.setAssignedToId(user.getUserId());
        chore.setStatusActive();
        //Remember about ChoreManagerProfile
    }
    public void deAssignChore(Chore chore){
        //chore.getAssignedToId().removeFromAssignedChores(chore); <<<<<<<<<<<<<gotta do manually
        chore.setAssignedToId(0);
        chore.setStatusUnassigned();
        //add to unassigned list?
    }
    public void deleteChore(Chore chore){

        if(chore.getAssignedToId()!= 0){
            deAssignChore(chore);
        }
        //remove from ChoreManagerProfile


    }
}

