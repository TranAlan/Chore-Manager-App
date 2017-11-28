
/**
 * class to store necessary information for a repeated chore
 * @author Sam Rennie 
 * SN: 8881891
 * Email: srenn096@uottawa.ca
 *
 */
package com.example.alan.peter.bilal.sam.choremanager;

public class Repeated {

    //defines when the chore is to be repeated
    public enum RepeatType{DAILY,WEEKLY,MONTHLY};

    //instance variables
    private int numRepeats;
    private RepeatType repeatStatus;

    //constructor
    public Repeated(int numRepeats) {
        this.numRepeats=numRepeats;
    }

    //getters and setters
    public void setTypeDaily() {
        repeatStatus = RepeatType.DAILY;
    }

    public void setTypeWeekly(){ repeatStatus = RepeatType.WEEKLY;}

    public void setTypeMonthly(){ repeatStatus = RepeatType.MONTHLY; }

    public void setNumRepeats(int repeat) {
        this.numRepeats=repeat;
    }

    public int getRepeats() {
        return this.numRepeats;
    }

    public RepeatType getType() {
        return this.repeatStatus;
    }
}