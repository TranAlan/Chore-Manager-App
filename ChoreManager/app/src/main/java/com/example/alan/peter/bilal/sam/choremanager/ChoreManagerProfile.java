package com.example.alan.peter.bilal.sam.choremanager;

/**
 * class storing the finished and unassigned chores. On top of this stores the pantry, materials and tools.
 *  Methods for calculating the reward points for a chore, resetting the data stored in the application
 *  and sorting the lists of stored items.
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 **/

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ChoreManagerProfile implements Serializable{

    //instance Variables
    private List<String> materials;
    private List<String> pantry;
    private List<String> shopListMat;
    private List<String> shoplistGroc;
    private List<User> regUsers;
    private List<AdminUser> adminUsers;
    private List<Chore> unassignedChores;
    private List<Chore> finishedChores;
    private Date currentDate;
    private int currentUserId;
    private int serialNumber;


    //Constructor
    public ChoreManagerProfile() {
        this.materials = new ArrayList<String>();
        this.shoplistGroc = new ArrayList<String>();
        this.shopListMat = new ArrayList<String>();
        this.pantry = new ArrayList<String>();
        this.currentDate = new Date();
        this.unassignedChores = new ArrayList<Chore>();
        this.finishedChores = new ArrayList<Chore>();
        this.regUsers = new ArrayList<User>();
        this.adminUsers = new ArrayList<AdminUser>();
        this.serialNumber = 1;
        this.currentUserId = 0;
    }



    //Getters
    public List<String> getShopListMat() { return this.shopListMat;}
    public List<String> getShoplistGroc() { return this.shoplistGroc;}
    public List<String> getMaterials() {
        return this.materials;
    }
    public List<String> getPantry() {
        return pantry;
    }
    public List<User> getRegUsers() {
        return regUsers;
    }
    public List<AdminUser> getAdminUsers() {return adminUsers;}
    public List<Chore> getUnassignedChores() {
        return unassignedChores;
    }
    public List<Chore> getFinishedChores() {
        return finishedChores;
    }
    public int getCurrentUserId(){
        return currentUserId;
    }
    public int getSerialNumber(){return serialNumber;}
    public Date getDate() {
        //update date to be current
        this.currentDate = new Date();
        return this.currentDate;
    }
    //SETTERS
    public void setCurrentUserId(int currentUserId){
        this.currentUserId = currentUserId;
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
    public void addRegUser(User toAdd) {
        regUsers.add(toAdd);
    }
    public void addAdminUser(AdminUser toAdd){adminUsers.add(toAdd);}
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



    public void changeTheme() {
        //TO BE IMPLEMENTED
    }

    //removes a Chore c, from either the unassigned or assigned list directory
    //if the chore exists
    public void removeChore(Chore c,ArrayList directory) {
        if(directory.contains(c)){
            directory.remove(c);
        }
    }


    //scrubs all of the data stored within the ChoreManagerProfile
    public void resetAppData() {
        this.materials = new ArrayList<String>();
        this.shoplistGroc = new ArrayList<String>();
        this.shopListMat = new ArrayList<String>();
        this.pantry = new ArrayList<String>();
        this.unassignedChores = new ArrayList<Chore>();
        this.finishedChores = new ArrayList<Chore>();
        this.regUsers= new ArrayList<User>();
        this.adminUsers= new ArrayList<AdminUser>();
        this.serialNumber = 1;
    }

    public boolean isCurrentUserAdmin(){
        if (getAdminUserFromId(currentUserId)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public User getUserFromName(String username) {
        for (int i = 0; i < regUsers.size(); i++){
            if (regUsers.get(i).getUsername().equals(username) ){
                return regUsers.get(i);
            }
        }

        for (int i = 0; i < adminUsers.size(); i++){
            if (adminUsers.get(i).getUsername().equals(username) ){
                return adminUsers.get(i);
             }
        }
        return null;
    }

    public User getAdminUserFromName(String username) {
        for (int i = 0; i < adminUsers.size(); i++){

            if (adminUsers.get(i).getUsername().equals(username) ){
                return adminUsers.get(i);
            }
        }
        return null;
    }

    public User getUserFromId(int userId){
        for (int i = 0; i < regUsers.size(); i++){
            if (regUsers.get(i).getUserId() == userId){
                return regUsers.get(i);
            }
        }

        for (int i = 0; i < adminUsers.size(); i++){
            if (adminUsers.get(i).getUserId() == userId){
                return adminUsers.get(i);
            }
        }
        return null;
    }

    public AdminUser getAdminUserFromId(int userId){
        for (int i = 0; i < adminUsers.size(); i++){
            if (adminUsers.get(i).getUserId() == userId){
                return adminUsers.get(i);
            }
        }
        return null;
    }

    public User getCurrentUser(){
        return getUserFromId(this.currentUserId);
    }

    public int nextSerialNumber(){
        serialNumber++;
        return serialNumber;
    }

    public Chore findChoreId(int id){
        //Checking All chore Lists

        //Unassigned List
        Iterator<Chore> choreIterator = unassignedChores.iterator();
        while (choreIterator.hasNext()){
            Chore chore = choreIterator.next();
            if(chore.getChoreId() == id){
                return chore;
            }

        }


        //FINISHED Chores
        choreIterator = finishedChores.iterator();
        while (choreIterator.hasNext()){
            Chore chore = choreIterator.next();
            if(chore.getChoreId() == id){
                return chore;
            }

        }

        //Checking all users
        for (int i = 0; i < regUsers.size(); i++){
            regUsers.get(i).getChoreFromId(id);
        }

        for (int i = 0; i < adminUsers.size(); i++){
            adminUsers.get(i).getChoreFromId(id);
        }
        return null;
    }
}