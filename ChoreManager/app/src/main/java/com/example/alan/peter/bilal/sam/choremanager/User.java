package com.example.alan.peter.bilal.sam.choremanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alan on 11/27/2017.
 */

public class User implements Serializable{
    private String username;
    private String password;
    private int totalPoints;
    private List<Chore> assignedChores;

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
    public List<Chore> getAssignedChores(){
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
        //chore.setStatusComplete();
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


    //sorts the list of assigned chores starting by those due at the earliest date
    public void sortChoresByDeadline(){
        if(assignedChores.size()!=0) {
            int numChores = this.assignedChores.size();
            List<Chore> sort = new ArrayList<>();
            List<Chore> copy = assignedChores;
            sort.add(copy.remove(0));
            for (int i = 0; i < numChores - 1; i++) {
                addChoreByDate(sort, copy.get(i));
            }
            assignedChores = sort;
        }
    }

    //method which takes a list of chores, and a lone chore, and inserts the chore in
    //the appropriate place in the list based on date
    public void addChoreByDate(List<Chore> list, Chore toAdd){
        Chore current = list.get(0);
        boolean added = false;
        int size = list.size();
        int pos = 1;
        while(added==false && pos<size){
            if(toAdd.getDeadline().before(current.getDeadline())){
                list.add(0,toAdd);
                added=true;
            }
            else if(toAdd.getDeadline().after(current.getDeadline())){
                if (pos==(size-1)){
                    list.add(toAdd);
                    added=true;
                }
                else{
                    current=list.get(pos);
                    pos++;
                    }
                }
            }
        }

    //sorts the lit of assigned chores starting by those due at the earliest date
    public void sortChoresByAlphabetical(){
        if(assignedChores.size()!=0) {
            int numChores = this.assignedChores.size();
            List<Chore> sort = new ArrayList<>();
            List<Chore> copy = assignedChores;
            sort.add(copy.remove(0));
            for (int i = 0; i < numChores - 1; i++) {
                addChoreByAlphabetical(sort, copy.get(i));
            }
            assignedChores = sort;
        }
    }

    //sorts the lit of assigned chores starting by those starting last alphabetically
    public void sortChoresByReverseAlphabetical(){
        if(assignedChores.size()!=0) {
            int numChores = this.assignedChores.size();
            List<Chore> sort = new ArrayList<>();
            List<Chore> copy = assignedChores;
            sort.add(copy.remove(0));
            for (int i = 0; i < numChores - 1; i++) {
                addChoreByAlphabetical(sort, copy.get(i));
            }
            assignedChores = sort;
        }
    }

    public void addChoreByAlphabetical(List<Chore> list, Chore toAdd){
        Chore current = list.get(0);
        boolean added = false;
        int size = list.size();
        int pos = 1;

        while(added==false && pos<size){
            if(toAdd.getName().compareTo(toAdd.getName())>0){
                list.add(0,toAdd);
                added=true;
            }
        else if(toAdd.getName().compareTo(toAdd.getName())<0){
            if (pos==(size-1)){
                list.add(toAdd);
                added=true;
                }
            else{
                current=list.get(pos);
                pos++;
                 }
             }
          }
    }

    public void addChoreByReverseAlphabetical(List<Chore> list, Chore toAdd){
        Chore current = list.get(0);
        boolean added = false;
        int size = list.size();
        int pos = 1;

        while(added==false && pos<size){
            if(toAdd.getName().compareTo(toAdd.getName())<0){
                list.add(0,toAdd);
                added=true;
            }
            else if(toAdd.getName().compareTo(toAdd.getName())>0){
                if (pos==(size-1)){
                    list.add(toAdd);
                    added=true;
                }
                else{
                    current=list.get(pos);
                    pos++;
                }
            }
        }
    }
}

    //sort by categorie?


