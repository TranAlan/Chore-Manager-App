package com.example.alan.peter.bilal.sam.choremanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;

public class AssignedResourcesActivity extends AppCompatActivity {
    private ImageButton filterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_resources);
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
