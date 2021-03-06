package com.example.pc.projekt.Models.Queries;
import com.example.pc.projekt.Models.*;
import android.content.*;
import android.database.sqlite.*;
import java.util.*;
import android.database.*;
/**
 * Created by pc on 2017-05-25.
 */

public class StatusEntityQueries {
    public static List<Status> getStatusList(Context context) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        List<Status> statuses = new ArrayList<Status>();
        Cursor cursor = db.rawQuery("SELECT id_status, status FROM status", null);
        if(cursor.moveToFirst())
        {
            do
            {
                Status status = new Status();
                status.id = cursor.getInt(0);
                status.status = cursor.getString(1);
                statuses.add(status);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return statuses;
    }
}
