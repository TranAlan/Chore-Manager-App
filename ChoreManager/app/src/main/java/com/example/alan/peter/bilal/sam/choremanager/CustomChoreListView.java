package com.example.alan.peter.bilal.sam.choremanager;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by peter on 2017-11-30.
 */

public class CustomChoreListView extends ArrayAdapter<String> {

    private List<String[]> choreArrayList;
    private Activity context;


    public CustomChoreListView(Activity context, List<String[]> choreArrayList)
    {
        super(context,R.layout.user_layout);
        this.context= context;
        this.choreArrayList = choreArrayList;
    }

    public View getView( int position,@Nullable View convertView, @Nullable ViewGroup parent)
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

        viewHolder.MainText.setText(choreArrayList.get(position)[0]);
        viewHolder.subText.setText(choreArrayList.get(position)[1]);
        return r;
    }

    class ViewHolder
    {
        TextView MainText;
        TextView subText;
        ViewHolder(View v)
        {
            MainText = (TextView) v.findViewById(R.id.MainText);
            subText = (TextView) v.findViewById(R.id.subtext);
        }
    }
}
