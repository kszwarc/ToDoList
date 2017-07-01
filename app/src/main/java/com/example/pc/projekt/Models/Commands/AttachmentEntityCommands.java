package com.example.pc.projekt.Models.Commands;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.pc.projekt.Models.Database;
import com.example.pc.projekt.Models.Task;

/**
 * Created by pc on 2017-05-25.
 */

public class AttachmentEntityCommands {
    public static void insertNewAttachment(Context context, int taskid, String path) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        SQLiteStatement stmt = db.compileStatement("INSERT INTO file (id_task, path) VALUES ("+
                taskid+", ?)");
        stmt.bindString(1, path);
        stmt.executeInsert();
        db.close();
    }

    public static void deleteAttachment(Context context, int id) {
        SQLiteDatabase db = Database.getOpenedDatabase(context);
        db.execSQL("Delete FROM file WHERE id_file="+id);
        db.close();
    }
}
