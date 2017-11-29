package com.example.alan.peter.bilal.sam.choremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChoreListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);
        ListView choreListView = (ListView) findViewById(R.id.choreListView);
        // making a hashmap for the list view
        HashMap<String, String> choresHash = new HashMap<>();
        // temp place holders -For real implemenation you need to take values of title, deadline and assignto and put it into the hash
        choresHash.put("Do the Dishes", "Peter\n10/21/17 @12:59");
        choresHash.put("!Drop Tables", "Kevin\n09/21/17 @12:59");
        choresHash.put("Clean your room", "Bilal\n08/25/17 @11:59");
        choresHash.put("Finish This App", "Alan\n11/28/17 @11:59");
        choresHash.put("Boost Peter to Plat", "Sam\n11/30/17 @2:00");
        choresHash.put("Make More Chores", "Vinh\n02/12/48 @1:06");

        // USE THIS TO IMPLEMNET
//        for (Chore i: ChoreList)
//        {
//            choresHash.put(i.getDescription(),i.getAssignedTo()+"\n"+i.getDeadline());
//        }

        // created Arraylist with nested hasp map
        List<HashMap<String, String>> listItems = new ArrayList<>();
        // creating a simpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.chore_list_item,
                // keys that adapters will looks for
                new String[]{"First Line", "Second Line"},
                //getting format from xml file
                new int[]{R.id.MainText, R.id.subtext});
        // iterate through a hashmap
        Iterator it = choresHash.entrySet().iterator();
        //loop until there aren o more values
        while (it.hasNext())
        {
            // creating a new hashmap
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        choreListView.setAdapter(adapter);
        // To retrieve object in second Activity
        //Intent i = getIntent();
        //ChoreManagerProfile s = (ChoreManagerProfile) i.getSerializableExtra("Manager");
        //Log.d("Testtag",s.getCurrentUser().getUsername());

       // MenuActivity.getManager();
        Log.d("Testtag",MenuActivity.getManager().getCurrentUser().getUsername());
    }

    protected void createNewChoreButton(View view){
        Intent intent = new Intent(this, NewChoreActivity.class);
        startActivity(intent);

    }
}
