package com.example.pc.projekt.Models;

import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.*;
import android.content.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String dbCreateTables, dbDropTables, dbUpdateTables;

    public DatabaseHelper(Context context, String name, CursorFactory factory, int version, String dbCreateTables, String dbDropTables, String dbUpdateTables) {
        super(context, name, factory, version);
        this.dbCreateTables = dbCreateTables;
        this.dbDropTables = dbDropTables;
        this.dbUpdateTables = dbUpdateTables;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        execOnDelimeter(dbCreateTables, db);
        execOnDelimeter(dbUpdateTables, db);
    }

    private void execOnDelimeter(String text, SQLiteDatabase db)
    {
        String[] textOnDelimeter = text.split(";");
        for (String sql : textOnDelimeter)
            if (sql.length()>0)
                db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        execOnDelimeter(dbDropTables, db);
        onCreate(db);
    }
}