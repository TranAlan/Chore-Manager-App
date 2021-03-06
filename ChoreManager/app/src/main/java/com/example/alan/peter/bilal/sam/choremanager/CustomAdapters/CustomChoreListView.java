package com.example.alan.peter.bilal.sam.choremanager.CustomAdapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alan.peter.bilal.sam.choremanager.Activities.MenuActivity;
import com.example.alan.peter.bilal.sam.choremanager.Classes.Chore;
import com.example.alan.peter.bilal.sam.choremanager.R;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by peter on 2017-11-30.
 */


/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: CustomChoreListView
 */

/**
 * An adapter class for storing Chores in a ListView
 */
public class CustomChoreListView extends ArrayAdapter<Chore> {

    private List<Chore> choreList;
    private Activity context;


    public CustomChoreListView(Activity context, List<Chore> choreList)
    {

        super(context, R.layout.chore_list_item, choreList);
        this.context= context;
        this.choreList = choreList;
    }

    public View getView(int position,@Nullable View convertView, @Nullable ViewGroup parent)
    {
        View r=convertView;
        CustomChoreListView.ViewHolder viewHolder=null;
        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.chore_list_item,null,true);
            viewHolder= new CustomChoreListView.ViewHolder(r);
            r.setTag(viewHolder);
        }

        else
        {
            viewHolder = (ViewHolder) r.getTag();
        }


        viewHolder.MainText.setText(choreList.get(position).getName());
        int choreUserId = choreList.get(position).getAssignedToId();
        String choreUsername;
        if(MenuActivity.getManager().getUserFromId(choreUserId) == null){
            choreUsername = "UNASSIGNED";
        }
        else{
            choreUsername = MenuActivity.getManager().getUserFromId(choreUserId).getUsername();
        }
        DateFormat formatDateTime = DateFormat.getDateTimeInstance();
        String nameDate = choreUsername+"\n "+formatDateTime.format(choreList.get(position).getDeadline());
        viewHolder.subText.setText(nameDate);

        String statusString = "Status: "+choreList.get(position).getStatusString();
        viewHolder.statusText.setText(statusString);


        String typeText = "Type: "+choreList.get(position).getChoreType().toString();
        viewHolder.choreTypeText.setText(typeText);

        return r;
    }

    class ViewHolder
    {
        TextView MainText; //Name
        TextView subText; //AssignedTo  and Deadline
        TextView statusText; //Status
        TextView choreTypeText; //Type of the chore
        ViewHolder(View v)
        {
            MainText = (TextView) v.findViewById(R.id.MainText);
            subText = (TextView) v.findViewById(R.id.subtext);
            statusText= (TextView) v.findViewById(R.id.statusTextView);
            choreTypeText = (TextView) v.findViewById(R.id.choreTypeTextView);
        }
    }
}
