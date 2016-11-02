package com.vicky.notifier.database.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Vignesh Sivakumar on 28-10-2016.
 */
public interface DatabaseInterface {

    public void insert(String tableName, ContentValues contentValues);

    public Cursor executeQuery(String query, String[] selectionArgs);

    public void executeUpdate(String tableName, ContentValues contentValues, String query, String[] selectionArgs);

    public void executeDelete(String tableName, String query, String[] selectionArgs);
}
