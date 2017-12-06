/**
 * Chore class containing all the information about a created chore
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 */
package com.example.alan.peter.bilal.sam.choremanager.Classes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Chore implements Serializable, Comparable<Chore>{

    //status' the chore can hold
    private enum Status {
        COMPLETE,
        INCOMPLETE,
        UNASSIGNED,
        ACTIVE,
        LATE_COMPLETION;
        @Override
        public String toString(){
            switch(this) {
                case COMPLETE: return "Complete";
                case INCOMPLETE: return "Incomplete";
                case UNASSIGNED: return "Unassigned";
                case ACTIVE: return "Active";
                case LATE_COMPLETION: return "Late Completion";
                default: throw new IllegalArgumentException();
            }
        }
    }
    public enum CatType {
        CLEANING,
        COOKING,
        MISC;
        @Override
        public String toString(){
            switch(this) {
                case MISC: return "Misc";
                case CLEANING: return "Cleaning";
                case COOKING: return "Cooking";
                default: throw new IllegalArgumentException();
            }
        }
    };


    //Instance variables
    private String name;
    private String description;
    private String notes;
    private int rewardPoints;
    private Status completionStatus;
    private CatType choreType;
    private Date deadline;
    private int assignedToId;
    private List<String> reqResources;
    private int choreId;


    public int compareTo(Chore chore){
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if(this.getName().compareTo(chore.getName())<0){
            return BEFORE;
        }
        else if(this.getName().compareTo(chore.getName())==0){
            return EQUAL;
        }
        else{
            return AFTER;
        }
    }

    public Chore(){

        this.name=null;
        this.description=null;
        this.notes=null;
        this.rewardPoints= 0;
        this.choreType= CatType.MISC;
        this.assignedToId = 0;
        this.deadline=null;
        this.reqResources=null;
        this.completionStatus=Status.ACTIVE;
    }

    //ASSIGNED
    public Chore(String name, String desc, String note, int points,String type, Date due, List<String> reqResources,int choreId, int assignedToId) {
        this.name=name;
        this.description=desc;
        this.notes=note;
        this.rewardPoints=points;
        this.choreType=typeFromString(type);
        this.deadline=due;
        this.reqResources=reqResources;
        this.assignedToId = assignedToId;
        this.completionStatus=Status.ACTIVE;
        this.choreId = choreId;
    }

    //UNASSIGNED
    public Chore(String name, String desc, String note, int points, String type, Date due, List<String> reqResources, int choreId) {
        this.name=name;
        this.description=desc;
        this.notes=note;
        this.rewardPoints=points;
        this.choreType=typeFromString(type);
        this.deadline=due;
        this.reqResources=reqResources;
        this.assignedToId = 0;
        this.completionStatus=Status.UNASSIGNED;
        this.choreId = choreId;
    }

    //Getters and Setters

    //GETTERS
    public String getName() {
        return name;
    }

    public CatType getChoreType() {
        return this.choreType;
    }

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
    public int getAssignedToId() {
        return assignedToId;
    }
    public int getChoreId(){return choreId;}
    public String getStatusString(){
        return completionStatus.toString();
    }
    public List<String> getReqResources(){ return reqResources;}

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
    public void setAssignedToId(int assignedToId) {
        this.assignedToId = assignedToId;
    }
    public void setChoreId(int choreId){ this.choreId = choreId;}
    public void setReqResources(List<String> reqResources){this.reqResources = reqResources;}

    //STATUS SETTERS
    public void setStatusComplete(){
        completionStatus = Status.COMPLETE;
    }
    public void setStatusInComplete(){
        completionStatus = Status.INCOMPLETE;
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
        choreType = CatType.CLEANING;
    }
    public void setTypeCooking(){
        choreType = CatType.COOKING;
    }
    public void setTypeMisc(){
        choreType = CatType.MISC;
    }

    //Type checker
    public boolean isCleaning(){
        if(choreType.equals(CatType.CLEANING)){
            return true;
        }
        return false;
    }

    public boolean isCooking(){
        if(choreType.equals(CatType.COOKING)){
            return true;
        }
        return false;
    }
    public boolean isMisc(){
        if(choreType.equals(CatType.MISC)){
            return true;
        }
        return false;
    }

    public CatType typeFromString(String s){
        if(s.equals(CatType.CLEANING.toString())){
            return CatType.CLEANING;
        }

        else if(s.equals(CatType.MISC.toString())){
            return CatType.MISC;
        }
        else{
            return CatType.COOKING;
        }
    }

    //MISC
    public boolean hasMaterials(){
        if(this.reqResources==null){
            return false;
        }
        return true;
    }



}
