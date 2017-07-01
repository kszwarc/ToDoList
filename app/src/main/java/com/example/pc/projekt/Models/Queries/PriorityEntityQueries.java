package com.example.pc.projekt.Models.Queries;
import com.example.pc.projekt.Models.*;
import android.content.*;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;
import android.database.*;
/**
 * Created by pc on 2017-05-25.
 */

public class PriorityEntityQueries {
    public static List<Priority> getPriorities(Context context) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        List<Priority> priorities = new ArrayList<Priority>();
        Cursor cursor = db.rawQuery("SELECT id_priority, priority FROM priority", null);
        if(cursor.moveToFirst())
        {
            do
            {
                Priority priority = new Priority();
                priority.id = cursor.getInt(0);
                priority.priority = cursor.getString(1);
                priorities.add(priority);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return priorities;
    }
}
