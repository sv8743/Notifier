package com.vicky.notifier.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vicky.notifier.database.model.DatabaseInterface;
import com.vicky.notifier.database.util.QueryUtil;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseInterface {

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, 1);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        List<String> queries = QueryUtil.getOnCreateQueries(getDatabaseName());
        if(queries != null) {
            for (String query : queries) {
                sqLiteDatabase.execSQL(query);
            }
        }
    }

    @Override
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
        getWritableDatabase().insertOrThrow(tableName, null, contentValues);
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