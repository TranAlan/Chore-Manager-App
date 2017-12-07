package com.example.alan.peter.bilal.sam.choremanager.Classes;

import java.util.Date;
import java.util.List;


/**
 * Created by Alan on 11/27/2017.
 */

public class AdminUser extends User {

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: AdminUser
*/

    public AdminUser(){
        super();
    }
    public AdminUser(String username, String password, int userId, int imageID){
        super(username, password, userId, imageID);
    }

    public Chore createChore(String name, String desc, String note, int points, String type, Date due, List<String> reqResources , int choreId, User user){
    /** Creates a chore and assignes it to a user
     *
     * all attributes of chore needed to create an assigned chore
     * @return the chore created
     */
        Chore chore = new Chore(name, desc, note, points, type, due, reqResources, choreId, user.getUserId());
        if (user!= null){
            assignChore(user, chore);
        }
        return chore;
    }

    /** Creates a chore that does not belong to a User
     *
     * all attributes of chore needed to create a unassigned chore
     * @return the chore created
     */
    public Chore createUnAssignedChore(String name, String desc, String note, int points,String type, Date due, List<String> reqResources, int choreId ){
        return new Chore(name, desc, note, points,type, due, reqResources, choreId);
    }

    /** Assignes a Chore to a user
     *
     * @param user User who will be assigned a chore
     * @param chore Chore to be assigned
     */
    public void assignChore(User user, Chore chore){
        user.addToAssignedChores(chore);
        chore.setAssignedToId(user.getUserId());
        chore.setStatusActive();

    }

    /** Removes chore from user
     *
     * @param chore all attributes of chore needed to create a chore
     */
    public void deAssignChore(Chore chore){
        chore.setAssignedToId(0);
        chore.setStatusUnassigned();
    }

}

