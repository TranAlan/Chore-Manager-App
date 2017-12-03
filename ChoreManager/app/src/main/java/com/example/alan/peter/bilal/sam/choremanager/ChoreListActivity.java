package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);

        //  ---- List View Code ---
        ListView choreListView = (ListView) findViewById(R.id.choreListView);
        // making a arraylist of string arrays for the list view

        // filling the arrays with title and the description aka Assign to name along with the deadline
        for(int i  = 0; i < MenuActivity.getManager().getUsers().size(); i++)
        {
            listOfChores.addAll(MenuActivity.getManager().getUsers().get(i).getAssignedChores());

        }
        // calling custom chore view to display all the choews
        final CustomChoreListView customChoreListView = new CustomChoreListView(this,listOfChores);
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
                            //sort all users chores alphabetically
                            listOfChores.removeAll(listOfChores);
                            for(int i =0; i<MenuActivity.getManager().getUsers().size(); i++){
                                MenuActivity.getManager().getUsers().get(i).sortChoresByAlphabetical();
                                //update view
                                listOfChores.addAll(MenuActivity.getManager().getUsers().get(i).getAssignedChores());

                            }

                        }
                        else if(item.getTitle().equals("Deadline")){
                            //sort all users chores by deadline
                            listOfChores.removeAll(listOfChores);
                            for(int i =0; i<MenuActivity.getManager().getUsers().size(); i++){
                                MenuActivity.getManager().getUsers().get(i).sortChoresByDeadline();
                                //update view
                                listOfChores.addAll(MenuActivity.getManager().getUsers().get(i).getAssignedChores());
                            }
                        }

                        else if (item.getTitle().equals("Z-A")){
                            //sort all users chores Z-A
                            listOfChores.removeAll(listOfChores);
                            for(int i =0; i<MenuActivity.getManager().getUsers().size(); i++){
                                MenuActivity.getManager().getUsers().get(i).sortChoresByReverseAlphabetical();
                                //update view
                                listOfChores.addAll(MenuActivity.getManager().getUsers().get(i).getAssignedChores());
                            }
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
                            listOfChores.removeAll(listOfChores);
                            for(int i = 0; i < MenuActivity.getManager().getCurrentUser().getAssignedChores().size(); i ++){
                                listOfChores.add(MenuActivity.getManager().getCurrentUser().getAssignedChores().get(i));
                            }
                        }
                        else if(item.getTitle().equals("Finished")){
                            listOfChores.removeAll(listOfChores);

                            for(int i  = 0; i < MenuActivity.getManager().getFinishedChores().size(); i++)
                            {
                                //TODO FIX
                                listOfChores.add(MenuActivity.getManager().getFinishedChores().get(i));
                            }
                        }
                        else if(item.getTitle().equals("Unassigned Chores")) {
                            listOfChores.removeAll(listOfChores);
                            for(int i = 0; i < MenuActivity.getManager().getUnassignedChores().size(); i++){
                                //update view to show only unassigned chores
                                listOfChores.add(MenuActivity.getManager().getUnassignedChores().get(i));
                            }
                        }
                        else if(item.getTitle().equals("All Chores")){
                            //populate list with all assigned chores
                            listOfChores.removeAll(listOfChores);
                            for(int i  = 0; i < MenuActivity.getManager().getUsers().size(); i++)
                            {
                                listOfChores.addAll(MenuActivity.getManager().getUsers().get(i).getAssignedChores());

                            }
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
        Log.d("test", MenuActivity.getManager().getCurrentUser().getUsername());
        Log.d("test", "Going into newChore!");
        startActivity(intent);
        finish();

    }

}
