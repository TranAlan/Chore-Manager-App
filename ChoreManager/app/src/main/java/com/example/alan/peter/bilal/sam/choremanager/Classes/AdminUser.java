package com.example.alan.peter.bilal.sam.choremanager.Classes;

import java.util.Date;
import java.util.List;


/**
 * Created by Alan on 11/27/2017.
 */

public class AdminUser extends User {

    public AdminUser(){
        super();
    }
    public AdminUser(String username, String password, int userId, int imageID){
        super(username, password, userId, imageID);
    }

    public Chore createChore(String name, String desc, String note, int points, String type, Date due, List<String> reqResources , int choreId, User user){
        Chore chore = new Chore(name, desc, note, points, type, due, reqResources, choreId, user.getUserId());
        if (user!= null){
            assignChore(user, chore);
        }
        return chore;
    }

    public Chore createUnAssignedChore(String name, String desc, String note, int points,String type, Date due, List<String> reqResources, int choreId ){
        return new Chore(name, desc, note, points,type, due, reqResources, choreId);
    }

    //UNIQUE PUBLIC METHODS
    public void assignChore(User user, Chore chore){
        user.addToAssignedChores(chore);
        chore.setAssignedToId(user.getUserId());
        chore.setStatusActive();

    }
    public void deAssignChore(Chore chore){
        chore.setAssignedToId(0);
        chore.setStatusUnassigned();
    }

}

