package com.example.alan.peter.bilal.sam.choremanager;

/**
 * Created by peter on 2017-11-30.
 */

/** Authors:       Peter Lam ,  Sam Rennie, Bilal Khalid, Alan Tran
 * Student numbers: 8670663 ,   8881891,     8589066,    8580760
 * Course: SEG2105_C
 * Instructor: Dr. Miguel A. Garzón
 * Assignment: Project
 * Class: StateVO
 */

// Adaated from https://stackoverflow.com/questions/19027843/android-get-text-of-all-checked-checkboxes-in-listview
public class StateVO
{
    private String title;
    private boolean selected;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
