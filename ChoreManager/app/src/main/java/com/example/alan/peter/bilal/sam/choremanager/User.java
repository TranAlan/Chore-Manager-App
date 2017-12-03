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
    private List<Chore> assignedChores;

    public User(){
        username = null;
        password = null;
        totalPoints = 0;
        assignedChores = new ArrayList<Chore>();
    }

    public User(String username){
        this.username = username;
        password = null;
        this.totalPoints = 0;
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

    public Chore getChoreFromName(String name){
        Iterator<Chore> i = assignedChores.iterator();
        while(i.hasNext()){
            Chore chore = i.next();
            if(chore.getName().equals(name)){
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


    /*public void spaghettiSort() {
        //public Chore(String name, String desc, String note, int points, Date due, ArrayList materials, ArrayList groceries,int id, User assigned)
        Date date = new Date();
        ArrayList<String> temp = new ArrayList<String>();
        User temp2 = new User();
        Chore one = new Chore("ABC", "", "", 5, date, temp, temp, 1, temp2);
        Chore two = new Chore("CBA", "", "", 5, date, temp, temp, 1, temp2);
        Chore three = new Chore("BAC", "", "", 5, date, temp, temp, 1, temp2);
        this.assignedChores.add(one);
        this.assignedChores.add(two);
        this.assignedChores.add(three);
    }
    }*/

    //sorts the Users assignedChores
    public void sortAZ(){
        Collections.sort(assignedChores, new Comparator<Chore>() {
            @Override
            public int compare(Chore chore, Chore t1) {
                return chore.getName().compareTo(t1.getName());
            }
        });
    }


    //sorts the lit of assigned chores starting by those starting last alphabetically
    public void sortZA(){
        Collections.sort(assignedChores, new Comparator<Chore>() {
            @Override
            public int compare(Chore chore, Chore t1) {
                int toReturn;
                if (chore.getName().compareTo(t1.getName()) > 0) {
                    toReturn = -1;
                } else if (chore.getName().compareTo(t1.getName()) < 0) {
                    toReturn = 1;
                } else {
                    toReturn = 0;
                }
                return toReturn;
            }
        });
    }

    //sorts chore from those due first, to those with the furthest away deadline
    public void sortDeadline(){
        Collections.sort(assignedChores, new Comparator<Chore>() {
            @Override
            public int compare(Chore chore, Chore t1) {
                return chore.getDeadline().compareTo(t1.getDeadline());
            }
        });
    }


}

    //sort by categorie?


