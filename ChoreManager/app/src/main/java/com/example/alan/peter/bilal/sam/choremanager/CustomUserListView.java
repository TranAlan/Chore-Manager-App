package com.example.alan.peter.bilal.sam.choremanager;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by peter on 2017-11-29.
 */

public class CustomUserListView extends ArrayAdapter<String>
{
    private String [] userName;
    private String [] roleName ;
    private Integer[] imageIDs;
    private Activity context;

    public CustomUserListView(Activity context,String [] userName, String [] roleName,Integer[] imageIDs )
    {
        super(context,R.layout.chore_list_item,userName);
    this.context= context;
    this.userName = userName;
    this.roleName = roleName;
    this.imageIDs = imageIDs;
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

        viewHolder.profilePicture.setImageResource(imageIDs[position]);
        viewHolder.userNameTV.setText(userName[position]);
        viewHolder.roleNameTV.setText(roleName[position]);
        return r;
    }

    class ViewHolder
    {
        TextView userNameTV;
        TextView roleNameTV;
        ImageView profilePicture;
        ViewHolder(View v)
        {
            userNameTV = (TextView) v.findViewById(R.id.userNameTV);
            roleNameTV = (TextView) v.findViewById(R.id.roleNameTV);
            profilePicture = v.findViewById(R.id.profilePicture);
        }
    }
}
