package com.example.pc.projekt.Models;

import android.content.*;
import android.database.SQLException;
import android.database.sqlite.*;

public class Database {

    private DatabaseHelper dbHelper;
    private static Context classContext;
    private static Database instance;
    private static final int DB_VERSION = 4;
    private static final String DB_NAME = "task.db";
    private static final String DB_TASK_TABLE = "task";
    private static final String KEY_TASK_ID = "id_task";
    private static final String KEY_TASK = "task";
    private static final String KEY_ADDED_DATE = "added_date";
    private static final String KEY_FINISHED_DATE = "finished_date";
    private static final String DB_PRIORITY_TABLE = "priority";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_PRIORITY_ID = "id_priority";
    private static final String DB_STATUS_TABLE = "status";
    private static final String KEY_STATUS_ID = "id_status";
    private static final String KEY_STATUS = "status";
    private static final String DB_FILE_TABLE = "file";
    private static final String KEY_FILE_ID = "id_file";
    private static final String KEY_PATH = "path";
    private static final String INTEGER_OPTIONS = "INTEGER DEFAULT 0";
    private static final String DATETIME_OPTIONS = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP";
    private static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TEXT_OPTIONS = "TEXT NOT NULL";
    private static final String FOREIGN_KEY_CASCADE = "ON DELETE CASCADE";

    private static final String DB_CREATE_TASK_TABLE =
            "CREATE TABLE " + DB_TASK_TABLE + "( " +
                    KEY_TASK_ID + " " + ID_OPTIONS + ", " +
                    KEY_STATUS_ID + " " + INTEGER_OPTIONS + " REFERENCES " + DB_STATUS_TABLE + "("+KEY_STATUS_ID+") "+FOREIGN_KEY_CASCADE+", "+
                    KEY_PRIORITY_ID + " " + INTEGER_OPTIONS + " REFERENCES " + DB_PRIORITY_TABLE + "("+KEY_PRIORITY_ID+") "+FOREIGN_KEY_CASCADE+", "+
                    KEY_TASK + " " + TEXT_OPTIONS + ", " +
                    KEY_ADDED_DATE + " " + DATETIME_OPTIONS + ", " +
                    KEY_FINISHED_DATE + " " + DATETIME_OPTIONS +
                    ");";

    private static final String DB_CREATE_PRIORITY_TABLE=
            "CREATE TABLE " + DB_PRIORITY_TABLE + "( " +
                    KEY_PRIORITY_ID + " " + ID_OPTIONS + ", " +
                    KEY_PRIORITY + " " + TEXT_OPTIONS +
                    ");";

    private static final String DB_CREATE_STATUS_TABLE =
            "CREATE TABLE " + DB_STATUS_TABLE + "( " +
                    KEY_STATUS_ID + " " + ID_OPTIONS + ", " +
                    KEY_STATUS + " " + TEXT_OPTIONS +
                    ");";

    private static final String DB_CREATE_FILE_TABLE =
            "CREATE TABLE " + DB_FILE_TABLE + "( " +
                    KEY_FILE_ID + " " + ID_OPTIONS + ", " +
                    KEY_TASK_ID + " " + INTEGER_OPTIONS + " REFERENCES " + DB_TASK_TABLE + "("+KEY_TASK_ID+") "+FOREIGN_KEY_CASCADE+", "+
                    KEY_PATH + " " + TEXT_OPTIONS +
                    ");";

    private static final String DB_UPDATE_TABLES =
            "INSERT INTO " + DB_STATUS_TABLE + "("+ KEY_STATUS + ") VALUES ('Dodane'), ('Na liście'), ('Ukończone');" +
                    "INSERT INTO " + DB_PRIORITY_TABLE + "("+ KEY_PRIORITY + ") VALUES ('Niski'), ('Średni'), ('Wysoki');";

    private static final String DB_CREATE_TABLES = DB_CREATE_STATUS_TABLE + DB_CREATE_TASK_TABLE + DB_CREATE_FILE_TABLE + DB_CREATE_PRIORITY_TABLE;
    private static final String DB_DROP_TABLES = "DROP TABLE IF EXISTS " + DB_STATUS_TABLE +";DROP TABLE IF EXISTS "+ DB_TASK_TABLE +";DROP TABLE IF EXISTS "+ DB_FILE_TABLE +";DROP TABLE IF EXISTS "+ DB_PRIORITY_TABLE;

    private Database(){};

    public static SQLiteDatabase getOpenedDatabase(Context context) {
        classContext = context;
        if (instance == null)
        {
            synchronized (Database.class) {
                if (instance == null)
                    instance = new Database();
            }
        }
        return instance.open();
    }

    private SQLiteDatabase open(){
        SQLiteDatabase db;
        dbHelper = new DatabaseHelper(classContext, DB_NAME, null, DB_VERSION, DB_CREATE_TABLES, DB_DROP_TABLES, DB_UPDATE_TABLES);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return db;
    }

    public void close() {
        dbHelper.close();
    }
}
