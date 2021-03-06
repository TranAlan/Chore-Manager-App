package com.example.alan.peter.bilal.sam.choremanager.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.alan.peter.bilal.sam.choremanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017-11-30.
 */

/* Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: CustomMaterialListAdapter
 */

/**
 * An custom adapter class to store a list of materials in a ListView
 */

public class CustomMaterialListAdapter extends ArrayAdapter<StateVO> {

    private Context mContext;
    private ArrayList<StateVO> listState;
    private CustomMaterialListAdapter customMaterialListAdapter;
    private boolean isFromView = false;

    public CustomMaterialListAdapter(Context context, int resource, List<StateVO> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<StateVO>) objects;
        this.customMaterialListAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.special_materials_spinner, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        if (position == 0) {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }

        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //If box is clicked, set it to true;
                int getPosition = (Integer) buttonView.getTag();
                if (isChecked){
                    listState.get(getPosition).setSelected(true);
                }
                else{
                    listState.get(getPosition).setSelected(false);
                }


            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}

