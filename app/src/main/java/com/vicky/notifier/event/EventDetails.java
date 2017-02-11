package com.vicky.notifier.event;

import com.vicky.notifier.database.DatabaseConstants;

public class EventDetails {

    private String eventID = null;
    private final int day;
    private final String event;
    private final String month;
    private final int year;

    public EventDetails(String eventID, int day, String month, int year, String event) {
        this.eventID = eventID;
        this.day = day;
        this.month = month;
        this.year = year;
        this.event = event;
    }

    public EventDetails(int day, String month, int year, String event) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.event = event;
    }

    public String getEventID() {
        return eventID;
    }

    public int getDayAsInt() {
        return this.day;
    }

    public String getDay() {
        return Integer.toString(this.day);
    }

    public String getMonth() {
        return this.month;
    }

    public String getYear() {
        return Integer.toString(year);
    }

    public String getEvent() {
        return this.event;
    }

    public int getMonthIndex() {
        int i = 0;
        for(String month : DatabaseConstants.MONTH_NAMES) {
            if(month.equals(this.month)) {
                return i;
            }
            ++i;
        }
        return 0;
    }

    public String toString() {
        return eventID + " " + day + " " + month + " " + year + " - " + event;
    }
}
