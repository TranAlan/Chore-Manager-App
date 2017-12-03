/**
 * Chore class containing all the information about a created chore
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 */
package com.example.alan.peter.bilal.sam.choremanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chore implements Serializable{

    //status' the chore can hold
    private enum Status {
        COMPLETE,
        INCOMPLETE,
        PARTIALLY_COMPLETE,
        UNASSIGNED,
        ACTIVE,
        LATE_COMPLETION;
        @Override
        public String toString(){
            switch(this) {
                case COMPLETE: return "Complete";
                case INCOMPLETE: return "Incomplete";
                case PARTIALLY_COMPLETE: return "Partially Complete";
                case UNASSIGNED: return "Unassigned";
                case ACTIVE: return "Active";
                case LATE_COMPLETION: return "Late Completion";
                default: throw new IllegalArgumentException();
            }
        }
    }
    public enum Type {CLEANING, COOKING, MISC};


    //Instance variables
    private String name;
    private String description;
    private String notes;
    private int rewardPoints;
    private Status completionStatus;
    private Type choreType;
    private Date deadline;
    private User assignedTo;
    private List<String> reqMat;
    private List<String> reqGroc;
    private int id;

    public Chore(){

        this.name=null;
        this.description=null;
        this.notes=null;
        this.rewardPoints= 0;
        this.choreType=Type.MISC;
        this.deadline=null;
        this.reqMat=null;
        this.reqGroc=null;
        this.assignedTo=null;
        this.completionStatus=Status.ACTIVE;
    }

    //ASSIGNED
    public Chore(String name, String desc, String note, int points, Date due, ArrayList materials, ArrayList groceries,int id, User assigned) {
        this.name=name;
        this.description=desc;
        this.notes=note;
        this.rewardPoints=points;
        this.choreType=Type.MISC;
        this.deadline=due;
        this.reqMat=materials;
        this.reqGroc=groceries;
        this.assignedTo=assigned;
        this.completionStatus=Status.ACTIVE;
        this.id = id;
    }

    //UNASSIGNED
    public Chore(String name, String desc, String note, int points, Date due, ArrayList materials, ArrayList groceries,int id) {
        this.name=name;
        this.description=desc;
        this.notes=note;
        this.rewardPoints=points;
        this.choreType=Type.MISC;
        this.deadline=due;
        this.reqMat=materials;
        this.reqGroc=groceries;
        User emptyUser = new User("UNASSIGNED", "");
        this.assignedTo= emptyUser;
        this.completionStatus=Status.UNASSIGNED;
        this.id = id;
    }

    //Getters and Setters

    //GETTERS
    public String getName() {
        return name;
    }
    /*
    public Type getType() {
        return this.choreType;
    }
    */
    public String getNotes() {
        return notes;
    }
    public String getDescription() {
        return description;
    }
    public int getRewardPoints() {
        return rewardPoints;
    }
    public Status getCompletionStatus() {
        return this.completionStatus;
    }
    public Date getDeadline() {
        return deadline;
    }
    public User getAssignedTo() {
        return assignedTo;
    }
    public int getId(){return id;}
    public String getStatusString(){
        return completionStatus.toString();
    }
    public List<String> getReqMat(){ return reqMat;}
    public List<String> getReqGroc(){return reqGroc;}

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }


    //STATUS SETTERS
    public void setStatusComplete(){
        completionStatus = Status.COMPLETE;
    }
    public void setStatusInComplete(){
        completionStatus = Status.INCOMPLETE;
    }
    public void setStatusPartial(){
        completionStatus = Status.PARTIALLY_COMPLETE;
    }
    public void setStatusUnassigned(){
        completionStatus = Status.UNASSIGNED;
    }
    public void setStatusActive(){
        completionStatus = Status.ACTIVE;
    }
    public void setStatusLate(){
        completionStatus = Status.LATE_COMPLETION;
    }

    //TYPE SETTERS
    public void setTypeCleaning(){
        choreType = Type.CLEANING;
    }
    public void setTypeCooking(){
        choreType = Type.COOKING;
    }
    public void setTypeMisc(){
        choreType = Type.MISC;
    }


        //rewards full points if the chore is completed before the deadline and
        //half points if completed afterwards
        public int calcRewardPoints() {
            Date current = new Date();
            int points=0;
            int possible = this.getRewardPoints();
            Date toCompare = this.getDeadline();
            if(toCompare.before(current)) {
                points = possible;
            }
            else if(toCompare.after(current)) {
                points = possible/2;
            }
            return points;
        }

}
