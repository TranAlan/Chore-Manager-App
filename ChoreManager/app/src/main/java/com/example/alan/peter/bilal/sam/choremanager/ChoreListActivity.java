package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChoreListActivity extends AppCompatActivity {

    List<Chore> listOfChores = new ArrayList<>();
    private ImageButton sortButton,filterButton;
    private CustomChoreListView customChoreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);

        //  ---- List View Code ---
        ListView choreListView = (ListView) findViewById(R.id.allResourcesList);
        // making a arraylist of string arrays for the list view

        // filling the arrays with title and the description aka Assign to name along with the deadline

        for(int i  = 0; i < MenuActivity.getManager().getRegUsers().size(); i++){
            listOfChores.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores());
        }
        for(int i  = 0; i < MenuActivity.getManager().getAdminUsers().size(); i++){
            listOfChores.addAll(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores());
        }

        listOfChores.addAll(MenuActivity.getManager().getUnassignedChores());
        listOfChores.addAll(MenuActivity.getManager().getFinishedChores());
        customChoreListView = new CustomChoreListView(this,listOfChores);

        // calling custom chore view to display all the choews
        choreListView.setAdapter(customChoreListView);

        choreListView.setClickable(true);
        choreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //If click on a chore, starts Specific Chore Activity
                Chore item = customChoreListView.getItem(position);
                Intent intent = new Intent(getBaseContext(), SpecificChoreActivity.class);
                intent = intent.putExtra("ChoreInfo", item ); // passes chore to next activity
                startActivity(intent);


            }
        });


        // link buttons to the ones in XML
        filterButton = (ImageButton) findViewById(R.id.filterButton);
        sortButton = (ImageButton) findViewById(R.id.sortButton);
        // set and create OnClickListener for Fitler and Sort buttons

        sortButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ChoreListActivity.this, sortButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.sort_popup, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("A-Z")){
                            //remove current chore view
                            listOfChores.removeAll(listOfChores);

                            //populate a list with chores from all users
                            List<Chore> allChores = new ArrayList<>();

                            //regular user add all chores
                            for(int i =0; i<MenuActivity.getManager().getRegUsers().size(); i++) {
                                allChores.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores());
                            }
                            //admin user add all chores
                            for(int i =0; i<MenuActivity.getManager().getAdminUsers().size(); i++){
                                ArrayList<Chore> toAdd = (ArrayList)MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores();
                                allChores.addAll(toAdd);                            }
                                //sort and repopulate view with all chores
                                MenuActivity.getManager().sortAZ(allChores);
                                customChoreListView.addAll(allChores);
                            }

                        else if(item.getTitle().equals("Deadline")){
                            //remove current chore view
                            listOfChores.removeAll(listOfChores);

                            //populate a list with chores from all users
                            List<Chore> allChores = new ArrayList<>();

                            //regular user add all chores
                            for(int i =0; i<MenuActivity.getManager().getRegUsers().size(); i++) {
                                allChores.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores());
                            }
                            //admin user add all chores
                            for(int i =0; i<MenuActivity.getManager().getAdminUsers().size(); i++){
                                ArrayList<Chore> toAdd = (ArrayList)MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores();
                                allChores.addAll(toAdd);                            }
                            //sort and repopulate view with all chores
                            MenuActivity.getManager().sortDeadline(allChores);
                            customChoreListView.addAll(allChores);
                        }

                        else if (item.getTitle().equals("Z-A")){
                            //remove current chore view
                            listOfChores.removeAll(listOfChores);

                            //populate a list with chores from all users
                            List<Chore> allChores = new ArrayList<>();

                            //regular user add all chores
                            for(int i =0; i<MenuActivity.getManager().getRegUsers().size(); i++) {
                                allChores.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores());
                            }
                            //admin user add all chores
                            for(int i =0; i<MenuActivity.getManager().getAdminUsers().size(); i++){
                                ArrayList<Chore> toAdd = (ArrayList)MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores();
                                allChores.addAll(toAdd);
                            }
                            //sort and repopulate view with all chores
                            MenuActivity.getManager().sortZA(allChores);
                            customChoreListView.addAll(allChores);
                        }

                        Toast.makeText(ChoreListActivity.this,"Sorted by: " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        }
        ); //closing the setOnClickListener method

        filterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ChoreListActivity.this, filterButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.filter_popup, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getTitle().equals("My Chores Only")){
                            //remove current chore view
                            listOfChores.removeAll(listOfChores);
                            List<Chore> allChores = new ArrayList<>();
                            allChores.addAll(MenuActivity.getManager().getCurrentUser().getAssignedChores());
                            customChoreListView.addAll(allChores);

                        }
                        else if(item.getTitle().equals("Finished")){
                            listOfChores.removeAll(listOfChores);
                            List<Chore> allChores = new ArrayList<>();
                            allChores.addAll(MenuActivity.getManager().getFinishedChores());
                            customChoreListView.addAll(allChores);

                        }
                        else if(item.getTitle().equals("Unassigned Chores")) {
                            listOfChores.removeAll(listOfChores);
                            List<Chore> allChores = new ArrayList<>();
                            allChores.addAll(MenuActivity.getManager().getUnassignedChores());
                            customChoreListView.addAll(allChores);
                        }
                        else if(item.getTitle().equals("All Chores")){
                            listOfChores.removeAll(listOfChores);
                            List<Chore> allChores = new ArrayList<>();
                            for(int i  = 0; i < MenuActivity.getManager().getRegUsers().size(); i++){
                                allChores.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores());
                            }
                            for(int i  = 0; i < MenuActivity.getManager().getAdminUsers().size(); i++){
                                allChores.addAll(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores());
                            }
                            customChoreListView.addAll(allChores);
                        }
                        Toast.makeText(ChoreListActivity.this,"Filtered by: " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }


        }); //closing the setOnClickListener method

    }
    protected void createNewChoreButton(View view){
        Intent intent = new Intent(this, NewChoreActivity.class);
        startActivity(intent);
        finish();
    }

    protected void viewAssignedResourcesButton(View view){
        Intent intent = new Intent(this, AssignedResourcesActivity.class);
        startActivity(intent);
        finish();
    }


}
