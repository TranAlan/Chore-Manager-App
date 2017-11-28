package com.example.alan.peter.bilal.sam.choremanager;

/**
 * class storing the finished and unassigned chores. On top of this stores the pantry, materials and tools.
 *  Methods for calculating the reward points for a chore, resetting the data stored in the application
 *  and sorting the lists of stored items.
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 **/

import java.util.Date;
import java.util.Arrays;


public class ChoreManagerProfile {

    //instance Variables
    private String[] materials;
    private String[] pantry;
    private String[] tools;
    private int pantrySize,toolSize,materialSize,numUsers, numFChores, numUChores;
    private int incrementSize;
    private User[] users;
    private Chore[] unassignedChores;
    private Chore[] finishedChores;
    private Date currentDate;


    //Constructor
    public ChoreManagerProfile(int growthSize) {
        this.incrementSize = growthSize;
        this.materials = new String[incrementSize];
        this.pantry = new String[incrementSize];
        this.tools = new String[incrementSize];
        this.unassignedChores = new Chore[incrementSize];
        this.finishedChores = new Chore[incrementSize];
        this.pantrySize =0;
        this.toolSize =0;
        this.materialSize = 0;
        this.numUsers =0;
    }



    //Getters and Setters
    public String[] getMaterials() {
        return this.materials;
    }


    public void addMaterial(String material) {
        //if full increase array size and add
        if(this.materialSize==materials.length) {
            String[] temp = new String[materials.length+incrementSize];
            for (int i=0 ; i>materials.length; i++) {
                temp[i]=materials[i];
            }
            materials=temp;
            materials[materialSize]=material;
            materialSize++;
        }
        else {
            materials[materialSize]=material;
            materialSize++;
        }
    }



    public String[] getPantry() {
        return pantry;
    }


    public void addPantryItem(String item) {
        //if full increase array size and add
        if(this.pantrySize==pantry.length) {
            String[] temp = new String[pantry.length+incrementSize];
            for (int i=0 ; i>pantry.length; i++) {
                temp[i]=pantry[i];
            }
            pantry=temp;
            pantry[pantrySize]=item;
            pantrySize++;
        }
        else {
            pantry[pantrySize]=item;
            pantrySize++;
        }
    }

    public String[] getTools() {
        return tools;
    }


    public void addTools(String tool) {
        //if full increase array size and add
        if(this.toolSize==tools.length) {
            String[] temp = new String[tools.length+incrementSize];
            for (int i=0 ; i>tools.length; i++) {
                temp[i]=tools[i];
            }
            tools=temp;
            tools[toolSize]=tool;
            toolSize++;
        }
        else {
            tools[toolSize]=tool;
            toolSize++;
        }
    }


    public int getIncrementSize() {
        return incrementSize;
    }


    public void setIncrementSize(int incrementSize) {
        this.incrementSize = incrementSize;
    }


    public User[] getUsers() {
        return users;
    }


    public void addUser(User toAdd) {
        //if full increase array size and add
        if(this.numUsers==users.length) {
            User[] temp = new User[users.length+incrementSize];
            for (int i=0 ; i>users.length; i++) {
                temp[i]=users[i];
            }
            users=temp;
            users[numUsers]=toAdd;
            numUsers++;
        }
        else {
            users[numUsers]=toAdd;
            numUsers++;
        }
    }


    public Chore[] getUnassignedChores() {
        return unassignedChores;
    }


    public void addUnassignedChores(Chore unassigned) {
        //if full increase array size and add
        if(this.numUChores==unassignedChores.length) {
            Chore[] temp = new Chore[unassignedChores.length+incrementSize];
            for (int i=0 ; i>unassignedChores.length; i++) {
                temp[i]=unassignedChores[i];
            }
            unassignedChores=temp;
            unassignedChores[numUChores]=unassigned;
            numUChores++;
        }
        else {
            unassignedChores[numUChores]=unassigned;
            numUChores++;
        }
    }


    public Chore[] getFinishedChores() {
        return finishedChores;
    }


    public void addFinishedChores(Chore finished) {
        //if full increase array size and add
        if(this.numFChores==finishedChores.length) {
            Chore[] temp = new Chore[finishedChores.length+incrementSize];
            for (int i=0 ; i>finishedChores.length; i++) {
                temp[i]=finishedChores[i];
            }
            finishedChores=temp;
            finishedChores[numFChores]=finished;
            numUChores++;
        }
        else {
            finishedChores[numFChores]=finished;
            numFChores++;
        }
    }

    public Date getDate() {
        //update date to be current
        this.currentDate = new Date();
        return this.currentDate;
    }


    //other methods

    public void sortMaterials() {
        Arrays.sort(this.materials);
    }

    public void sortPantry() {
        Arrays.sort(this.pantry);
    }

    public void sortTools() {
        Arrays.sort(this.tools);
    }



    public void changeTheme() {
        //TO BE IMPLEMENTED
    }

    //removes a Chore c, from either the unassigned or assigned list directory
    //if the chore exists
    public void removeChore(Chore c,Chore[] directory) {
        //TO BE IMPLEMENTED
    }

    public int[] getStats() {
        int[] stats = new int[users.length];
        for(int i=0; i<users.length; i++) {
            stats[i]=users[i].getTotalPoints();
        }
        return stats;
    }

    public void resetAppData() {
        //TO BE IMPLEMENTED
    }
}
