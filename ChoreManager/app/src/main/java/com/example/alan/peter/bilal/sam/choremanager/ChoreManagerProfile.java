package com.example.alan.peter.bilal.sam.choremanager;

/**
 * class storing the finished and unassigned chores. On top of this stores the pantry, materials and tools.
 *  Methods for calculating the reward points for a chore, resetting the data stored in the application
 *  and sorting the lists of stored items.
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 **/

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;


public class ChoreManagerProfile {

    //instance Variables
    private ArrayList<String> materials;
    private ArrayList<String> pantry;
    private ArrayList<String> tools;
    private ArrayList<User> users;
    private ArrayList<Chore> unassignedChores;
    private ArrayList<Chore> finishedChores;
    private Date currentDate;


    //Constructor
    public ChoreManagerProfile() {
        this.materials = new ArrayList<String>();
        this.pantry = new ArrayList<String>();
        this.tools = new ArrayList<String>();
        this.unassignedChores = new ArrayList<Chore>();
        this.finishedChores = new ArrayList<Chore>();
        this.users = new ArrayList<User>();
    }



    //Getters and Setters
    public ArrayList<String> getMaterials() {
        return this.materials;
    }


    public void addMaterial(String material) {
        materials.add(material);
    }


    public ArrayList<String> getPantry() {
        return pantry;
    }


    public void addPantryItem(String item) {
        pantry.add(item);
    }

    public ArrayList<String> getTools() {
        return tools;
    }


    public void addTools(String tool) {
        tools.add(tool);
    }


    public ArrayList<User> getUsers() {
        return users;
    }


    public void addUser(User toAdd) {
        users.add(toAdd);
    }


    public ArrayList<Chore> getUnassignedChores() {
        return unassignedChores;
    }


    public void addUnassignedChores(Chore unassigned) {
        unassignedChores.add(unassigned);
    }


    public ArrayList<Chore> getFinishedChores() {
        return finishedChores;
    }


    public void addFinishedChores(Chore finished) {
        finishedChores.add(finished);
    }

    public Date getDate() {
        //update date to be current
        this.currentDate = new Date();
        return this.currentDate;
    }


    //other methods

    public void sortMaterials() {
        Collections.sort(materials);
    }

    public void sortPantry() {
        Collections.sort(pantry);
    }

    public void sortTools() {
        Collections.sort(tools);
    }



    public void changeTheme() {
        //TO BE IMPLEMENTED
    }

    //removes a Chore c, from either the unassigned or assigned list directory
    //if the chore exists
    public void removeChore(Chore c,ArrayList directory) {
        //TO BE IMPLEMENTED
    }

    public int[] getStats() {
        return new int[1];//TO BE IMPLEMENTED
    }

    public void resetAppData() {
        //TO BE IMPLEMENTED
    }
}