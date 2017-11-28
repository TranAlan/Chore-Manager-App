/**
 * Chore class containing all the information about a created chore
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 */
package com.example.alan.peter.bilal.sam.choremanager;

import java.util.Date;

public class Chore {

    //status' the chore can hold
    private enum Status {COMPLETE,INCOMPLETE,PARTIALLY_COMPLETE,UNASSIGNED,ACTIVE,LATE_COMPLETION};


    //Instance variables
    private String name;
    private String description;
    private String notes;
    private int rewardPoints;
    private Repeated repeat;
    private Status completionStatus;
    private Date deadline;
    private User assignedTo;

    //constructor 1 - user not specified during chore creation, is repeated
    public Chore(String name, String description, String note, int pointsWorth, Repeated isRepeated, Date deadline) {
        this.name=name;
        this.description=description;
        this.notes=note;
        this.rewardPoints=pointsWorth;
        this.repeat=isRepeated;
        this.assignedTo=null;
        this.deadline=deadline;
        this.completionStatus= Status.ACTIVE;
    }

    //constructor 2 - user specified
    public Chore(String name, String description, String note, int pointsWorth, Repeated isRepeated, Date deadline, User assigned) {
        this.name=name;
        this.description=description;
        this.notes=note;
        this.rewardPoints=pointsWorth;
        this.repeat=isRepeated;
        this.assignedTo=assigned;
        this.deadline=deadline;
        this.completionStatus= Status.ACTIVE;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
