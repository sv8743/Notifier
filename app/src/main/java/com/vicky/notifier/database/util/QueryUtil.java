package com.vicky.notifier.database.util;

import com.vicky.notifier.database.DatabaseConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vicky.notifier.database.DatabaseConstants.DATABASE_NAME;
import static com.vicky.notifier.database.DatabaseConstants.EVENTS_TABLE_NAME;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_DAY;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_ID;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_MONTH;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_NAME;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_YEAR;

/**
 * Created by Vignesh Sivakumar on 28-10-2016.
 */
public class QueryUtil {

    public static Map<String, List<String>> onCreateQueries = new HashMap<String, List<String>>();
    public static Map<String, List<String>> onUpgradeQueries = new HashMap<String, List<String>>();

    static {
        List<String> queries = new ArrayList<String>();
        queries.add("create table " + EVENTS_TABLE_NAME + " (" +
                EVENT_ID + " text primary key," +
                EVENT_DAY + " integer," +
                EVENT_MONTH + " integer," +
                EVENT_YEAR + " integer," +
                EVENT_NAME + " text)");
        onCreateQueries.put(DATABASE_NAME, queries);
    }

    public static List<String> getOnCreateQueries(String databaseName) {
        return onCreateQueries.get(databaseName);
    }

    public static List<String> getOnUpgradeQueries(String databaseName) {
        return onUpgradeQueries.get(databaseName);
    }
}
