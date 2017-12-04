package com.example.alan.peter.bilal.sam.choremanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

/**
 * Created by Alan on 11/27/2017.
 */

public class User implements Serializable{
    private String username;
    private String password;
    private int totalPoints;
    private int userId;
    private List<Chore> assignedChores;

    public User(){
        username = null;
        password = null;
        totalPoints = 0;
        assignedChores = new ArrayList<Chore>();
        userId = 0;
    }

    public User(String username){
        this.username = username;
        password = null;
        this.totalPoints = 0;
        assignedChores = new ArrayList<Chore>();
    }

    public User(String username, String password, int userId){
        this.username = username;
        this.password = password;
        this.totalPoints = 0;
        this.userId = userId;
        assignedChores = new ArrayList<Chore>();
    }

    //GETTERS
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getTotalPoints(){
        return totalPoints;
    }
    public List<Chore> getAssignedChores(){
        return assignedChores;
    }
    public int getUserId(){return userId;}

    //SETTERS
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }
    public void setAssignedChores(ArrayList<Chore> assignedChores){
        this.assignedChores = assignedChores;
    }

    //OTHER PUBLIC METHODS
    public void addToAssignedChores(Chore chore){
        assignedChores.add(chore);
    }

    public void removeFromAssignedChores(Chore chore){
        assignedChores.remove(chore);
    }

    public int completeChore(Chore chore){
        chore.setStatusComplete();
        assignedChores.remove(chore);
        totalPoints =  totalPoints + chore.getRewardPoints();
        return chore.getRewardPoints();
    }

    public int completeChoreLate(Chore chore){
        chore.setStatusLate();
        assignedChores.remove(chore);
        int pointsEarned = chore.getRewardPoints()/2;
        totalPoints =  totalPoints + pointsEarned;
        return pointsEarned;
    }

    public int inCompleteChore(Chore chore){
        chore.setStatusInComplete();
        assignedChores.remove(chore);
        return 0;
    }
    public Chore getChoreFromId(int id){
        Iterator<Chore> i = assignedChores.iterator();
        while(i.hasNext()){
            Chore chore = i.next();
            if(chore.getChoreId() == id){
                return chore;
            }
        }
        return null;
    }

}


