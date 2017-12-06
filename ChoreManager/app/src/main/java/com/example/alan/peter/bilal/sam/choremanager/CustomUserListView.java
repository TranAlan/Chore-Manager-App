package com.example.alan.peter.bilal.sam.choremanager;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017-11-29.
 */
/** Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: CustomUserListView
 */

public class CustomUserListView extends ArrayAdapter<User>
{
    private List<User> users = new ArrayList<>();
    private Activity context;

    public CustomUserListView(Activity context, List<User> users)
    {
        super(context,R.layout.user_layout,users);
        this.context= context;
        this.users = users;
    }

    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent)
    {
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.user_layout,null,true);
            viewHolder= new ViewHolder(r);
            r.setTag(viewHolder);
        }

        else
        {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.profilePicture.setImageResource(users.get(position).getImageID());
        viewHolder.userNameTV.setText(users.get(position).getUsername());
         if (MenuActivity.getManager().isUserAdmin(users.get(position).getUserId()))
         {
             viewHolder.roleNameTV.setText("Admin");
         }

         else
         {
             viewHolder.roleNameTV.setText("Child");
         }
        viewHolder.actualPointsTV.setText(Integer.toString(users.get(position).getTotalPoints()));
        return r;
    }

    class ViewHolder
    {
        TextView userNameTV,roleNameTV,actualPointsTV;
        ImageView profilePicture;
        ViewHolder(View v)
        {
            userNameTV = (TextView) v.findViewById(R.id.userNameTV);
            roleNameTV = (TextView) v.findViewById(R.id.roleNameTV);
            actualPointsTV = (TextView) v.findViewById(R.id.actualPointsTV);
            profilePicture = v.findViewById(R.id.profilePicture);
        }
    }
}
