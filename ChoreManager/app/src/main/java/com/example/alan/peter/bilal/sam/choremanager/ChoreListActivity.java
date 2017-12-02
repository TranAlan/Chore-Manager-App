package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
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
        for(int i  = 0; i < MenuActivity.getManager().getUsers().size(); i++){
            listOfChores.addAll(MenuActivity.getManager().getUsers().get(i).getAssignedChores());
        }
        // calling custom chore view to display all the choews
        final CustomChoreListView customChoreListView = new CustomChoreListView(this,listOfChores);
        choreListView.setAdapter(customChoreListView);
        choreListView.setClickable(true);
        choreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "At onclick");
                Chore item = customChoreListView.getItem(position);

                Log.d("test", item.getAssignedTo().getUsername());

                Intent intent = new Intent(getBaseContext(), SpecificChoreActivity.class);
                intent = intent.putExtra("ChoreInfo", item );
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
        //finish();

    }

    protected void clickOnSpecificChore( View view){
        Intent intent = new Intent(this, SpecificChoreActivity.class);

        //Layout triangle = choreLayout;
       // String a = triangle.toString();
        Log.d("test", "ALIVE");
                /*
        TextView usedView = (TextView) view;
        intent = intent.putExtra("ChoreInfo", usedView.getText().toString());
        */
        //Log.d("test", view.toString());
        //Log.d("test",usedView.getText().toString());
        startActivity(intent);

    }
}
