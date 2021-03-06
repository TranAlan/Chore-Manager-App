package com.example.alan.peter.bilal.sam.choremanager.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.alan.peter.bilal.sam.choremanager.Classes.Chore;
import com.example.alan.peter.bilal.sam.choremanager.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: AssignedResourcesActivity
 */

/**
 * Activity that allows users to view all resources assigned to a group of chores.
 */
public class AssignedResourcesActivity extends AppCompatActivity {

    private ImageButton filterButton;
    ArrayList<String> allUsedMaterials = new ArrayList<>();
    List<String> cookingMaterials = new ArrayList<>();
    List<String> cleaningMaterials = new ArrayList<>();
    List<String> miscMaterials = new ArrayList<>();
    List<Chore> holding = new ArrayList<>();
    List<String> hotFix = new ArrayList<>();
    private ListView listview;


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if( item.getItemId() == android.R.id.home ){
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_resources);

        listview = (ListView) findViewById(R.id.allResourcesList);

        //populating list of all chores to be used to find materials
        holding.addAll(MenuActivity.getManager().getFinishedChores());
        holding.addAll(MenuActivity.getManager().getUnassignedChores());
        for(int i =0; i<MenuActivity.getManager().getAdminUsers().size(); i++){
            holding.addAll(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores());
        }
        for(int i =0; i<MenuActivity.getManager().getRegUsers().size(); i++){
            holding.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores());
        }

        //populating list containing all materials used by chores
        for(int i=0; i<holding.size(); i++){
            if(holding.get(i).hasMaterials()){
                allUsedMaterials.addAll(holding.get(i).getReqResources());
            }
        }
        //remove dupes
        Set<String> dupRemove = new HashSet<>();
        dupRemove.addAll(allUsedMaterials);
        allUsedMaterials.clear();;
        allUsedMaterials.addAll(dupRemove);

        //populating list containing all materials used by misc chores
        for(int i=0; i<holding.size(); i++){
            if(holding.get(i).hasMaterials() && holding.get(i).isMisc()){
                miscMaterials.addAll(holding.get(i).getReqResources());
            }
        }
        //remove dupes
        Set<String> dupRemove2 = new HashSet<>();
        dupRemove2.addAll(miscMaterials);
        miscMaterials.clear();;
        miscMaterials.addAll(dupRemove2);

        //populating list containing all materials used by cleaning chores
        for(int i=0; i<holding.size(); i++){
            if(holding.get(i).hasMaterials() && holding.get(i).isCleaning()){
                cleaningMaterials.addAll(holding.get(i).getReqResources());
            }
        }

        //remove dupes
        Set<String> dupRemove3 = new HashSet<>();
        dupRemove3.addAll(cleaningMaterials);
        cleaningMaterials.clear();;
        cleaningMaterials.addAll(dupRemove3);

        //populating list containing all materials used by cooking chores
        for(int i=0; i<holding.size(); i++){
            if(holding.get(i).hasMaterials() && holding.get(i).isCooking()){
                cookingMaterials.addAll(holding.get(i).getReqResources());
            }
        }

        //remove dupes
        Set<String> dupRemove4 = new HashSet<>();
        dupRemove4.addAll(cookingMaterials);
        cookingMaterials.clear();;
        cookingMaterials.addAll(dupRemove4);


        //  ---- List View Code ---
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_gallery_item, allUsedMaterials );
        // link buttons to the ones in XML
        listview.setAdapter(adapter);

        //hotfix
        hotFix.addAll(allUsedMaterials);



        filterButton = (ImageButton) findViewById(R.id.filterButton);
        // Onclick action for Filter Button
        filterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(AssignedResourcesActivity.this, filterButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.filter_resources_popup, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getTitle().equals("All"))
                        adapter.clear();
                        adapter.addAll(hotFix);
                        adapter.notifyDataSetChanged();
                        {

                        }
                         if(item.getTitle().equals("Misc")){
                            adapter.clear();
                            adapter.addAll(miscMaterials);
                            adapter.notifyDataSetChanged();
                        }
                        else if(item.getTitle().equals("Cleaning")) {
                            adapter.clear();
                            adapter.addAll(cleaningMaterials);
                            adapter.notifyDataSetChanged();                        }
                        else if(item.getTitle().equals("Cooking")){
                            adapter.clear();
                            adapter.addAll(cookingMaterials);
                            adapter.notifyDataSetChanged();                        }
                        Toast.makeText(AssignedResourcesActivity.this,"Filtered by: " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }


        }); //closing the setOnClickListener method
    }

}
