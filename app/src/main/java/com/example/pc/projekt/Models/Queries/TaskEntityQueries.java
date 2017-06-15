package com.example.pc.projekt.Models.Queries;
import com.example.pc.projekt.Models.*;
import android.content.*;
import android.database.sqlite.*;

import java.text.SimpleDateFormat;
import java.util.*;
import android.database.*;

/**
 * Created by pc on 2017-05-25.
 */

public class TaskEntityQueries {
    public static List<Task> getTasksListToShow(Context context, String condition, String orderBy)
    {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        List<Task> tasks = new ArrayList<Task>();
        Cursor cursor = db.rawQuery("SELECT id_task, status, priority, task, added_date, finished_date FROM task JOIN status USING (id_status) JOIN priority USING (id_priority)"+condition+orderBy, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Task task = new Task();
                task.id = cursor.getInt(0);
                task.status = cursor.getString(1);
                task.priority = cursor.getString(2);
                task.name = cursor.getString(3);
                task.setAddedTime(convertDateFromDB(cursor.getString(4)));
                task.setPlannedTime(convertDateFromDB(cursor.getString(5)));
                tasks.add(task);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }



    public static Task getTask(Context context, int id)
    {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        Cursor cursor = db.rawQuery("SELECT id_task, id_status, id_priority, status, priority, task, added_date, finished_date FROM task JOIN status USING (id_status) JOIN priority USING (id_priority) WHERE id_task="+id, null);
        Task task = new Task();
        if(cursor.moveToFirst())
        {
            do
            {
                task.id = cursor.getInt(0);
                task.statusId = cursor.getInt(1);
                task.priorityId = cursor.getInt(2);
                task.status = cursor.getString(3);
                task.priority = cursor.getString(4);
                task.name = cursor.getString(5);
                task.setAddedTime(convertDateFromDB(cursor.getString(6)));
                task.setPlannedTime(convertDateFromDB(cursor.getString(7)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return task;
    }

    private static String convertDateFromDB(String date)
    {
        try
        {
            java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            String[] dateTime = date.split(" ");
            String[] dateFragments = dateTime[0].split("-");
            String[] timeFragments = dateTime[1].split(":");
            return dateFragments[2]+"/"+dateFragments[1]+"/"+dateFragments[0]+" - "+timeFragments[0]+":"+timeFragments[1];
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
