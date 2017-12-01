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

public class Chore implements Serializable {

    //status' the chore can hold
    private enum Status {COMPLETE,INCOMPLETE,PARTIALLY_COMPLETE,UNASSIGNED,ACTIVE,LATE_COMPLETION};
    public enum Type {CLEANING, COOKING, MISC};


    //Instance variables
    private String name;
    private String description;
    private String notes;
    private int rewardPoints;
    private Repeated repeat;
    private Status completionStatus;
    public Type choreType;
    private Date deadline;
    private User assignedTo;
    private ArrayList reqMat;
    private ArrayList reqGroc;


    //constructor 1 - not assigned to user
    public Chore(String name, String desc, String note, int points, Repeated repeat, Date due, ArrayList materials, ArrayList groceries) {
        this.name=name;
        this.description=desc;
        this.notes=note;
        this.rewardPoints=points;
        this.repeat=repeat;
        this.choreType=Type.MISC;
        this.deadline=due;
        this.reqMat=materials;
        this.reqGroc=groceries;
        this.completionStatus=Status.UNASSIGNED;
    }

    //constructor 2 -  assigned to user
    public Chore(String name, String desc, String note, int points, Repeated repeat, Date due, ArrayList materials, ArrayList groceries, User assigned) {
        this.name=name;
        this.description=desc;
        this.notes=note;
        this.rewardPoints=points;
        this.repeat=repeat;
        this.choreType=Type.MISC;
        this.deadline=due;
        this.reqMat=materials;
        this.reqGroc=groceries;
        this.assignedTo=assigned;
        this.completionStatus=Status.ACTIVE;
    }

    public Chore(){

        this.name=null;
        this.description=null;
        this.notes=null;
        this.rewardPoints= 0;
        this.repeat=null;
        this.choreType=Type.MISC;
        this.deadline=null;
        this.reqMat=null;
        this.reqGroc=null;
        this.assignedTo=null;
        this.completionStatus=Status.ACTIVE;
    }

    public Chore(String name, String desc, String note, int points, Date due, ArrayList materials, ArrayList groceries, User assigned) {
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
    }

    public Chore(String name, String desc, String note, int points, Date due, ArrayList materials, ArrayList groceries) {
        this.name=name;
        this.description=desc;
        this.notes=note;
        this.rewardPoints=points;
        this.choreType=Type.MISC;
        this.deadline=due;
        this.reqMat=materials;
        this.reqGroc=groceries;
        this.assignedTo= null;
        this.completionStatus=Status.UNASSIGNED;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return this.choreType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Repeated getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeated repeat) {
        this.repeat = repeat;
    }

    public Status getCompletionStatus() {
        return completionStatus;
    }


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

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
