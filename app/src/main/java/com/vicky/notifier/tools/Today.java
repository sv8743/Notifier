package com.vicky.notifier.tools;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by Vignesh Sivakumar on 13-01-2017.
 */
public class Today {

    private int day;
    private int month;
    private int year;

    public Today() {
        Date today = new Date();
        day = Integer.parseInt((String) DateFormat.format("dd", today));
        month = Integer.parseInt((String) DateFormat.format("MM", today)) - 1;
        year = Integer.parseInt((String) DateFormat.format("yyyy", today));
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
