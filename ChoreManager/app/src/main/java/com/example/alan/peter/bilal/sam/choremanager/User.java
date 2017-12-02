package com.example.alan.peter.bilal.sam.choremanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Alan on 11/27/2017.
 */

public class User{
    private String username;
    private String password;
    private int totalPoints;
    private ArrayList<Chore> assignedChores;

    public User(){
        username = null;
        password = null;
        totalPoints = 0;
        assignedChores = new ArrayList<Chore>();
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.totalPoints = 0;
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

    public ArrayList<Chore> getAssignedChores(){
        return assignedChores;
    }

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
        //assignedChores.remove(chore);
        totalPoints =  totalPoints + chore.getRewardPoints();
        return chore.getRewardPoints();
    }

    public Chore getChoreFromId(int id){
        Iterator<Chore> i = assignedChores.iterator();
        while(i.hasNext()){
            Chore chore = i.next();
            if(chore.getId() == id){
                return chore;
            }
        }
        return null;
    }
    /*
    public void sortChoresByDeadline(){

    }

    public void sortChoresByAlphabetical(){

    }
    */
    //sort by categorie?

}
