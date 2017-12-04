package com.example.alan.peter.bilal.sam.choremanager;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by peter on 2017-11-30.
 */

public class CustomChoreListView extends ArrayAdapter<Chore> {

    private List<Chore> choreList;
    private Activity context;


    public CustomChoreListView(Activity context, List<Chore> choreList)
    {

        super(context,R.layout.chore_list_item, choreList);
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
        String choreUsername = MenuActivity.getManager().getUserFromId(choreUserId).getUsername();
        viewHolder.subText.setText(choreUsername+"\n "+choreList.get(position).getDeadline().toString());
        viewHolder.statusText.setText("Status: "+choreList.get(position).getStatusString());
        viewHolder.choreTypeText.setText("Chore Type :"+choreList.get(position).getType().toString());

        return r;
    }

    class ViewHolder
    {
        TextView MainText;
        TextView subText;
        TextView statusText;
        TextView choreTypeText;
        ViewHolder(View v)
        {
            MainText = (TextView) v.findViewById(R.id.MainText);
            subText = (TextView) v.findViewById(R.id.subtext);
            statusText= (TextView) v.findViewById(R.id.statusTextView);
            choreTypeText = (TextView) v.findViewById(R.id.choreTypeTextView);
        }
    }
}
