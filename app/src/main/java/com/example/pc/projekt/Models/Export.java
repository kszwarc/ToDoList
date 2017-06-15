package com.example.pc.projekt.Models;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class Export
{

    public static boolean export(Context context)
    {
        List<Task> tasksToShow = TaskEntityQueries.getTasksListToShow(context, "WHERE status='Na liście'", "");
        File root = new File(Environment.getExternalStorageDirectory().toString());
        File file = new File(root, "lista.txt");
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasksToShow)
            {
                writer.write("Nazwa: "+task.name+"\n");
                writer.write("Status: "+task.status+"\n");
                writer.write("Priorytet: "+task.priority+"\n");
                writer.write("Data utworzenia: "+task.getAddedTime()+"\n");
                writer.write("Planowana data ukończenia: "+task.getPlannedTime()+"\n\n");
            }
            writer.flush();
            return true;
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Błąd - "+e.toString(), Toast.LENGTH_LONG);
            return false;
        }
        finally
        {
            try
            {
                if (writer!=null)
                    writer.close();
            }
            catch (Exception e){}
        }
    }

}
