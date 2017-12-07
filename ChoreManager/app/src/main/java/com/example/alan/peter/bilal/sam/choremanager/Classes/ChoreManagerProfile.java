package com.example.alan.peter.bilal.sam.choremanager.Classes;

/**
 * class storing the finished and unassigned chores. On top of this stores the pantry, materials and tools.
 *  Methods for calculating the reward points for a chore, resetting the data stored in the application
 *  and sorting the lists of stored items.
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 **/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/** Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: ChoreManagerProfile
 */

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
    private int currentUserId;
    private int serialNumber;


    //Constructor
    public ChoreManagerProfile() {
        this.materials = new ArrayList<String>();
        this.shoplistGroc = new ArrayList<String>();
        this.shopListMat = new ArrayList<String>();
        this.pantry = new ArrayList<String>();
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


    /** Removes a Chore c, from either the unassigned or assigned list directory if the chore exists
     *
     * @param choreId id of a chore
     * @return true if successfully removed, false otherwise
     */
    public boolean removeChore(int choreId){
        for(int i = 0; i< unassignedChores.size(); i++){
            if (unassignedChores.get(i).getChoreId() == choreId){
                unassignedChores.remove(i);
                return true;
            }
        }

        for(int i = 0; i< finishedChores.size(); i++){
            if (finishedChores.get(i).getChoreId() == choreId){
                finishedChores.remove(i);
                return true;
            }
        }

        for(int i = 0; i< regUsers.size(); i++){

            for(int j = 0; j< regUsers.get(i).getAssignedChores().size(); j++ ){
                if (regUsers.get(i).getAssignedChores().get(j).getChoreId() == choreId){
                    regUsers.get(i).getAssignedChores().remove(j);
                    return true;
                }
            }

        }

        for(int i = 0; i< adminUsers.size(); i++){

            for(int j = 0; j< adminUsers.get(i).getAssignedChores().size(); j++ ){
                if (adminUsers.get(i).getAssignedChores().get(j).getChoreId() == choreId){
                    adminUsers.get(i).getAssignedChores().remove(j);
                    return true;
                }
            }

        }
        return false;
    }

    /** Scrubs all of the data stored within the ChoreManagerProfile
     *
     */
    public void resetAppData() {
        this.materials = new ArrayList<String>();
        this.shoplistGroc = new ArrayList<String>();
        this.shopListMat = new ArrayList<String>();
        this.pantry = new ArrayList<String>();
        this.unassignedChores = new ArrayList<Chore>();
        this.finishedChores = new ArrayList<Chore>();
        this.regUsers= new ArrayList<User>();
        this.adminUsers= new ArrayList<AdminUser>();
        this.currentUserId = 0;
        this.serialNumber = 1;
    }


    /** checks if the current user is an admin
     *
     * @return true if current user is an admin false otherwise
     */
    public boolean isCurrentUserAdmin(){
        if (getAdminUserFromId(currentUserId)!=null){
            return true;
        }
        else{
            return false;
        }
    }


    /** Checks if the user is in the adminUser list.
     *
     * @param userId id of a chore
     * @return true if the user with that Id is an admin, false othwerise
     */
    public boolean isUserAdmin(int userId){
        if (getAdminUserFromId(userId)!=null){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * @param username of a user
     * @return the user with that username, null if User is not found
     */
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


    /**
     * @param username of a admin user
     * @return AdminUser with that username, null if AdminUser is not found
     */
    public AdminUser getAdminUserFromName(String username) {
        for (int i = 0; i < adminUsers.size(); i++){

            if (adminUsers.get(i).getUsername().equals(username) ){
                return adminUsers.get(i);
            }
        }
        return null;
    }


    /** Searches all userlists to find a user with the userId
     *
     * @param userId id of a user
     * @return User associated with the userId, null if not user was not found
     */
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


    /** Searches from only regUser to find a user
     *
     * @param userId id of a regular user
     * @return a regular User associated to that id, null if user was not found
     */
    public User getRegUserFromId(int userId){
        for (int i = 0; i < regUsers.size(); i++){
            if (regUsers.get(i).getUserId() == userId){
                return regUsers.get(i);
            }
        }

       return null;
    }


    /** Searches only from the adminUser list to find the user
     * @param userId id of a chore
     * @return a admin user associated to that id, null if user was not found
     */
    public AdminUser getAdminUserFromId(int userId){
        for (int i = 0; i < adminUsers.size(); i++){
            if (adminUsers.get(i).getUserId() == userId){
                return adminUsers.get(i);
            }
        }
        return null;
    }


    /**
     * @return the current user thats logged in
     */
    public User getCurrentUser(){
        return getUserFromId(this.currentUserId);
    }


    /** gets the next unique serialNumber to be given to either a chore or a user
     * @return integer of the next SerialNumber
     */
    public int nextSerialNumber(){
        serialNumber++;
        return serialNumber;
    }


    /** Searches unAssignedChoreList, finishedChoreList, and assignedChoreList of every user to find
     *  a chore associated with that id.
     * @param id id of a chore
     * @return return Chore associated with that Id
     */
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



    /** Sorts the list of chores by alphabetical order
     *
     * @param chores list of chores
     */
    public void sortAZ(List<Chore> chores){
        Collections.sort(chores, new Comparator<Chore>() {
            @Override
            public int compare(Chore chore, Chore t1) {
                return chore.getName().compareTo(t1.getName());
            }
        });
    }



    /** Sorts the list of chore by reverse alphabetical order
     *
     * @param chores list of chores
     */
    //sorts the lit of assigned chores starting by those starting last alphabetically
    public void sortZA(List<Chore> chores){
        Collections.sort(chores, new Comparator<Chore>() {
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

    /** Sorts the chores in the list base on the closest deadline of each chore to the farthest
     *
     * @param chores list of chores
     */
    public void sortDeadline(List<Chore> chores){
        Collections.sort(chores, new Comparator<Chore>() {
            @Override
            public int compare(Chore chore, Chore t1) {
                return chore.getDeadline().compareTo(t1.getDeadline());
            }
        });
    }

    public List<String> getListOfUsernames(){
        List<String> usernameList = new ArrayList<String>();

        for(int i = 0; i< regUsers.size(); i++){
           usernameList.add(regUsers.get(i).getUsername());

        }

        for(int j = 0; j< adminUsers.size(); j++){
            usernameList.add(adminUsers.get(j).getUsername());
        }

        return usernameList;
    }
}