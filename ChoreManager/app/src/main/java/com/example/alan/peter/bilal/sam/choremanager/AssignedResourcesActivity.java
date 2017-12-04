package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class AssignedResourcesActivity extends AppCompatActivity {
    private ImageButton filterButton;
    List<String> allUsedMaterials = new ArrayList<>();
    List<String> cookingMaterials = new ArrayList<>();
    List<String> cleaningMaterials = new ArrayList<>();
    List<String> miscMaterials = new ArrayList<>();



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

        //generate lists used in the filters

        //Unassigned
        for(int i=0;  i<MenuActivity.getManager().getUnassignedChores().size(); i++){
            allUsedMaterials.addAll(MenuActivity.getManager().getUnassignedChores().get(i).getReqResources());
            if(MenuActivity.getManager().getUnassignedChores().get(i).isCleaning()){
                cleaningMaterials.addAll(MenuActivity.getManager().getUnassignedChores().get(i).getReqResources());
            }
            if(MenuActivity.getManager().getUnassignedChores().get(i).isMisc()){
                miscMaterials.addAll(MenuActivity.getManager().getUnassignedChores().get(i).getReqResources());
            }
            if(MenuActivity.getManager().getUnassignedChores().get(i).isCooking()){
                cookingMaterials.addAll(MenuActivity.getManager().getUnassignedChores().get(i).getReqResources());
            }
        }

        //Finished
        for(int i=0;  i<MenuActivity.getManager().getFinishedChores().size(); i++){
            allUsedMaterials.addAll(MenuActivity.getManager().getFinishedChores().get(i).getReqResources());
            if(MenuActivity.getManager().getFinishedChores().get(i).isCleaning()){
                cleaningMaterials.addAll(MenuActivity.getManager().getFinishedChores().get(i).getReqResources());
            }
            if(MenuActivity.getManager().getFinishedChores().get(i).isMisc()){
                miscMaterials.addAll(MenuActivity.getManager().getFinishedChores().get(i).getReqResources());
            }
            if(MenuActivity.getManager().getFinishedChores().get(i).isCooking()){
                cookingMaterials.addAll(MenuActivity.getManager().getFinishedChores().get(i).getReqResources());
            }
        }

        //Assigned to reg users
        for(int i=0; i<MenuActivity.getManager().getRegUsers().size(); i++){
            for(int j=0; j<MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().size(); j++){
                allUsedMaterials.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().get(j).getReqResources());
                if(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().get(j).isCleaning()){
                    cleaningMaterials.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().get(j).getReqResources());
                }
                if(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().get(j).isCooking()){
                    cookingMaterials.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().get(j).getReqResources());
                }
                if(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().get(j).isMisc()){
                    miscMaterials.addAll(MenuActivity.getManager().getRegUsers().get(i).getAssignedChores().get(j).getReqResources());
                }
            }

        }

        //assigned to admin users
        for(int i=0; i<MenuActivity.getManager().getAdminUsers().size(); i++){
            for(int j=0; j<MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().size(); j++){
                allUsedMaterials.addAll(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().get(j).getReqResources());
                if(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().get(j).isCleaning()){
                    cleaningMaterials.addAll(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().get(j).getReqResources());
                }
                if(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().get(j).isCooking()){
                    cookingMaterials.addAll(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().get(j).getReqResources());
                }
                if(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().get(j).isMisc()){
                    miscMaterials.addAll(MenuActivity.getManager().getAdminUsers().get(i).getAssignedChores().get(j).getReqResources());
                }
            }
        }


        //  ---- List View Code ---
        ListView choreListView = (ListView) findViewById(R.id.allResourcesList);
        // link buttons to the ones in XML
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
//
//                        if(item.getTitle().equals("All"))
//                        {
//                            //TODO: Implement This
//                        }
//                        else if(item.getTitle().equals("Misc")){
//                            //TODO: Implement This
//                        }
//                        else if(item.getTitle().equals("Cleaning")) {
//                            //TODO: Implement This
//                        }
//                        else if(item.getTitle().equals("Cooking")){
//                            //TODO: Implement This
//                        }
//                        Toast.makeText(AssignedResourcesActivity.this,"Filtered by: " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }


        }); //closing the setOnClickListener method
    }

}
