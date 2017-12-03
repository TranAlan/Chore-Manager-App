package com.example.alan.peter.bilal.sam.choremanager;

/**
 * class storing the finished and unassigned chores. On top of this stores the pantry, materials and tools.
 *  Methods for calculating the reward points for a chore, resetting the data stored in the application
 *  and sorting the lists of stored items.
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 **/

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.Iterator;


public class ChoreManagerProfile{

    //instance Variables
    private ArrayList<String> materials;
    private ArrayList<String> pantry;
    private ArrayList<String> tools;
    private ArrayList<String> shopListMat;
    private ArrayList<String> shoplistGroc;
    private ArrayList<User> users;
    private ArrayList<Chore> unassignedChores;
    private ArrayList<Chore> finishedChores;
    private Date currentDate;
    private User currentUser;
    private int id;


    //Constructor
    public ChoreManagerProfile() {
        this.materials = new ArrayList<String>();
        this.shoplistGroc = new ArrayList<String>();
        this.shopListMat = new ArrayList<String>();
        this.pantry = new ArrayList<String>();
        this.tools = new ArrayList<String>();
        this.unassignedChores = new ArrayList<Chore>();
        this.finishedChores = new ArrayList<Chore>();
        this.users = new ArrayList<User>();
        this.id = 0;
    }



    //Getters
    public ArrayList<String> getShopListMat() { return this.shopListMat;}
    public ArrayList<String> getShoplistGroc() { return this.shoplistGroc;}
    public ArrayList<String> getMaterials() {
        return this.materials;
    }
    public ArrayList<String> getPantry() {
        return pantry;
    }
    public ArrayList<String> getTools() {
        return tools;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<Chore> getUnassignedChores() {
        return unassignedChores;
    }
    public ArrayList<Chore> getFinishedChores() {
        return finishedChores;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public int getId(){return id;}
    public Date getDate() {
        //update date to be current
        this.currentDate = new Date();
        return this.currentDate;
    }
    public int[] getStats() {
        return new int[1];//TO BE IMPLEMENTED
    }

    //SETTERS
    public void setCurrentUser(User user){
        currentUser = user;
    }

    //HALF SETTERS
    public void addShoppingMaterial(String material){shopListMat.add(material);}
    public void addShoppingGrocery(String item) { shoplistGroc.add(item);}
    public void addMaterial(String material) {
        materials.add(material);
    }
    public void addPantryItem(String item) {
        pantry.add(item);
    }
    public void addTools(String tool) {
        tools.add(tool);
    }
    public void addUser(User toAdd) {
        users.add(toAdd);
    }
    public void addUnassignedChores(Chore unassigned) {
        unassignedChores.add(unassigned);
    }
    public void addFinishedChores(Chore finished) {
        finishedChores.add(finished);
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


    public void resetAppData() {
        //TO BE IMPLEMENTED
    }

    public boolean isUserAdmin(){
        return currentUser instanceof AdminUser;
    }

    public User getUserFromName(String username) {
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUsername().equals(username) ){
                return users.get(i);
            }
        }
        return null;
    }

    public int nextId(){
        id = id + 1;
        return id;
    }

    public Chore findChoreID(int id){
        //Checking All chore Lists

        //Unassigned List
        Iterator<Chore> choreIterator = unassignedChores.iterator();
        while (choreIterator.hasNext()){
            Chore chore = choreIterator.next();
            if(chore.getId() == id){
                return chore;
            }

        }

        //FINISHED Chores
        choreIterator = finishedChores.iterator();
        while (choreIterator.hasNext()){
            Chore chore = choreIterator.next();
            if(chore.getId() == id){
                return chore;
            }

        }

        //Checking all users
        for (int i = 0; i < users.size(); i++){
            users.get(i).getChoreFromId(id);
        }
        return null;
    }

}