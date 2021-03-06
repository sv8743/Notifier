package com.vicky.notifier.database.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;

import com.vicky.notifier.database.DatabaseHelper;
import com.vicky.notifier.event.BulkEventDetails;
import com.vicky.notifier.event.EventDetails;
import com.vicky.notifier.tools.Today;

import java.util.Date;
import java.util.Map;

import static com.vicky.notifier.database.DatabaseConstants.EQUALS;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_DAY;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_ID;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_MONTH;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_YEAR;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_NAME;
import static com.vicky.notifier.database.DatabaseConstants.DATABASE_NAME;
import static com.vicky.notifier.database.DatabaseConstants.EVENTS_TABLE_NAME;
import static com.vicky.notifier.database.DatabaseConstants.MONTH_NAMES;

/**
 * Created by Vignesh Sivakumar on 28-10-2016.
 */
public class EventsDBUtil {

    private static DatabaseHelper dbHelper = null;

    public static void init(Context context) {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME);
    }

    public static void insertEvent(EventDetails eventDetails) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_ID, eventDetails.getEventID());
        contentValues.put(EVENT_DAY, eventDetails.getDay());
        contentValues.put(EVENT_MONTH, eventDetails.getMonthIndex());
        contentValues.put(EVENT_YEAR, eventDetails.getYear());
        contentValues.put(EVENT_NAME, eventDetails.getEvent());
        dbHelper.insert(EVENTS_TABLE_NAME, contentValues);
    }

    public static BulkEventDetails getAllEvents() {
        BulkEventDetails bulkEventDetails = new BulkEventDetails();

        Today today = new Today();

        Cursor results = dbHelper.executeQuery("select " + EVENT_DAY + "," + EVENT_MONTH + "," + EVENT_YEAR + "," + EVENT_NAME + " FROM " +
                EVENTS_TABLE_NAME, null);

        /*Cursor results = dbHelper.executeQuery("select " + EVENT_DAY + "," + EVENT_MONTH + "," + EVENT_YEAR + "," + EVENT_NAME + " FROM " +
                EVENTS_TABLE_NAME + " WHERE " + EVENT_DAY + ">=" + today.getDay() + " AND " + EVENT_MONTH + ">=" + today.getMonth() + " AND " + EVENT_YEAR + ">=" +
                today.getYear() + " ORDER BY " + EVENT_YEAR + " ASC, " + EVENT_MONTH + " ASC, " + EVENT_DAY + " ASC", null);*/

        if (results.getCount() > 0) {
            results.moveToFirst();
            do {
                int day = results.getInt(results.getColumnIndex(EVENT_DAY));
                int month = results.getInt(results.getColumnIndex(EVENT_MONTH));
                int year = results.getInt(results.getColumnIndex(EVENT_YEAR));
                bulkEventDetails.addEvent(new EventDetails(day, MONTH_NAMES[month], year, results.getString(results.getColumnIndex(EVENT_NAME))));
            } while (results.moveToNext());
        }
        results.close();
        bulkEventDetails.finalize();
        return bulkEventDetails;
    }

    public static EventDetails getEventDetail(String eventID) {
        EventDetails eventDetails = null;
        Cursor cursor = dbHelper.executeQuery("select " + EVENT_DAY + "," + EVENT_MONTH + "," + EVENT_YEAR + "," + EVENT_NAME + " FROM " +
                EVENTS_TABLE_NAME + " WHERE " + EVENT_MONTH + EQUALS, new String[] {eventID});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int day = cursor.getInt(cursor.getColumnIndex(EVENT_DAY));
            int month = cursor.getInt(cursor.getColumnIndex(EVENT_MONTH));
            int year = cursor.getInt(cursor.getColumnIndex(EVENT_YEAR));
            eventDetails = new EventDetails(cursor.getString(cursor.getColumnIndex(EVENT_ID)),
                    day, MONTH_NAMES[month], year,
                    cursor.getString(cursor.getColumnIndex(EVENT_NAME)));
            cursor.close();
        }
        return eventDetails;
    }

    public static void updateEvent(String eventID, Map<String, String> values) {
        ContentValues contentValues = new ContentValues();
        for(Map.Entry<String, String> entry : values.entrySet()) {
            contentValues.put(entry.getKey(), entry.getValue());
        }
        dbHelper.executeUpdate(EVENTS_TABLE_NAME, contentValues, EVENT_ID + EQUALS, new String[] {eventID});
    }

    public static void deleteEvent(String eventID) {
        dbHelper.executeDelete(EVENTS_TABLE_NAME, EVENT_ID + EQUALS, new String[] {eventID});
    }
}