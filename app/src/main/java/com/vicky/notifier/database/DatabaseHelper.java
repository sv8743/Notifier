package com.vicky.notifier.database;

import static com.vicky.notifier.database.DatabaseConstants.EVENT_ID;
import static com.vicky.notifier.database.DatabaseConstants.EVENTS_TABLE_NAME;
import static com.vicky.notifier.database.DatabaseConstants.DATABASE_NAME;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_DAY;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_MONTH;
import static com.vicky.notifier.database.DatabaseConstants.EVENT_NAME;
import static com.vicky.notifier.database.DatabaseConstants.MONTH_NAMES;
import static com.vicky.notifier.database.DatabaseConstants.EQUALS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vicky.notifier.database.model.DatabaseInterface;
import com.vicky.notifier.database.util.QueryUtil;
import com.vicky.notifier.view.BulkEventDetails;
import com.vicky.notifier.view.EventDetails;

import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseInterface {

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        List<String> queries = QueryUtil.getOnCreateQueries(getDatabaseName());
        if(queries != null) {
            for (String query : queries) {
                sqLiteDatabase.execSQL(query);
            }
        }
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        List<String> queries = QueryUtil.getOnUpgradeQueries(getDatabaseName());
        if(queries != null) {
            for (String query : queries) {
                sqLiteDatabase.execSQL(query);
            }
        }
    }

    @Override
    public void insert(String tableName, ContentValues contentValues) {
        getWritableDatabase().insert(tableName, null, contentValues);
    }

    @Override
    public Cursor executeQuery(String query, String[] selectionArgs) {
        return getReadableDatabase().rawQuery(query, selectionArgs);
    }

    @Override
    public void executeUpdate(String tableName, ContentValues contentValues, String query, String[] selectionArgs) {
        getWritableDatabase().update(tableName, contentValues, query, selectionArgs);
    }

    @Override
    public void executeDelete(String tableName, String query, String[] selectionArgs) {
        getWritableDatabase().delete(tableName, query, selectionArgs);
    }
}