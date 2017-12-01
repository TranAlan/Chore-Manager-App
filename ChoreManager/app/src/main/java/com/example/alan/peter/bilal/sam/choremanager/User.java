package com.example.alan.peter.bilal.sam.choremanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import android.media.Image;
import android.util.Log;

/**
 * Created by Alan on 11/27/2017.
 */

public class User implements Serializable {
    private String username;
    private String password;
    private int totalPoints;
   // private Image profilePic;
    private ArrayList<Chore> assignedChores;

    public User(){
        username = null;
        password = null;
        totalPoints = 0;
       // profilePic = null;
        assignedChores = new ArrayList<Chore>();
    }
    /*
    public User(String username, String password, int totalPoints, Image profilePic){
        this.username = username;
        this.password = password;
        this.totalPoints = 0;
        this.profilePic = profilePic;
        assignedChores = new ArrayList<Chore>();
    }
    */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.totalPoints = 0;
        assignedChores = new ArrayList<Chore>();
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public int getTotalPoints(){
        return totalPoints;
    }

   /* public Image getProfilePic(){
        return profilePic;
    }
    */

    public ArrayList<Chore> getAssignedChores(){
        return assignedChores;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }

    /*
    public void setProfilePic(Image profilePic){
        this.profilePic = profilePic;
    }
    */

    public void setAssignedChores(ArrayList<Chore> assignedChores){
        this.assignedChores = assignedChores;
    }

    public void addToAssignedChores(Chore chore){
        assignedChores.add(chore);
    }

    public void removeFromAssignedChores(Chore chore){
        assignedChores.remove(chore);
    }

    public int completeChore(Chore chore){
        chore.setStatusComplete();
        assignedChores.remove(chore);
        totalPoints =  totalPoints + chore.getRewardPoints();
        return chore.getRewardPoints();
    }

    public Chore getChoreFromDate(String date){
        for(int i = 0; i < assignedChores.size(); i++){
            if(assignedChores.get(i).getDeadline().toString() == date ){
                return assignedChores.get(i);
            }
            //Log.d("Test", assignedChores.get(i).getName());
        }
        return null;
    }
    /*
    public void sortChoresByDeadline(){

    }

    public void sortChoresByAlphabetical(){

    }
    */
    //sort by categorie?

}

    /*
    EditText grabUsername = (EditText) findViewById(R.id.choreNameInput);
    EditText grabPassword = (EditText) findViewById(R.id.deadlineInput);
    Spinner grabUserType = findViewById(R.id.choreTypeSpinner);

    String userUsername = grabUsername.getText().toString();
    String userPassword = grabPassword.getText().toString();
    String userUserType = (String) grabUserType.getSelectedItem();

    if(userUserType.equals("Child")){
        User newUser = new User(userUsername, userPassword)
            }
            else{
                AdminUser newUser = new AdminUser(userUsername, userPassword)
            }
            MenuActivity.getManager().addUser(newUser);
            */