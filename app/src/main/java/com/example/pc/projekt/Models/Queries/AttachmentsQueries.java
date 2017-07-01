package com.example.pc.projekt.Models.Queries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.pc.projekt.Models.Attachement;
import com.example.pc.projekt.Models.Database;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class AttachmentsQueries {

    public static int getLastAttachmentId(Context context, int taskId) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        SQLiteStatement stmt = db.compileStatement("SELECT MAX(id_file) FROM file WHERE id_task="+taskId);
        int id;
        try
        {
            id = Integer.parseInt(stmt.simpleQueryForString());
        }
        catch (Exception e)
        {
            id = 0;
        }
        db.close();
        return id;
    }

    public static List<Attachement> getAttachementsListToShow(Context context, int taskId) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        List<Attachement> attachements = new ArrayList<Attachement>();
        Cursor cursor = db.rawQuery("SELECT id_file, path FROM file WHERE id_task="+taskId, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Attachement attachement = new Attachement();
                attachement.id = cursor.getInt(0);
                attachement.path = cursor.getString(1);
                attachements.add(attachement);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return attachements;
    }
}
