package com.example.pc.projekt.Models.Commands;
import com.example.pc.projekt.Models.*;
import com.example.pc.projekt.Models.Queries.StatusEntityQueries;
import android.content.*;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.*;
import android.database.*;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by pc on 2017-05-25.
 */

public class TaskEntityCommands {
    public static void insertNewTask(Context context, Task task) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        SQLiteStatement stmt = db.compileStatement("INSERT INTO task (id_status, id_priority, task, finished_date) VALUES ("+
                task.statusId+", "+task.priorityId+", ?, ?)");
        stmt.bindString(1, task.name);
        stmt.bindString(2, convertDateToDB(task.getPlannedTime()));
        stmt.executeInsert();
        db.close();
    }

    public static void updateTask(Context context, Task task) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        SQLiteStatement stmt = db.compileStatement("UPDATE task SET id_status="+task.statusId+", id_priority="+task.priorityId+"," +
                " task=?, finished_date=? WHERE id_task="+task.id);
        stmt.bindString(1, task.name);
        stmt.bindString(2, convertDateToDB(task.getPlannedTime()));
        stmt.executeInsert();
        db.close();
    }

    public static void deleteTask(Context context, int id) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        db.execSQL("Delete FROM task WHERE id_task="+id);
        db.close();
    }

    private static String convertDateToDB(String date) {
        String[] dateTime = date.split(" ");
        String[] dateFragments = dateTime[0].split("/");
        String[] timeFragments = dateTime[2].split(":");
        return dateFragments[2]+"-"+dateFragments[1]+"-"+dateFragments[0]+" "+timeFragments[0]+":"+timeFragments[1]+":00";
    }
}
