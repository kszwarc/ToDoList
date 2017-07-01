package com.example.pc.projekt.Models;

import com.example.pc.projekt.Models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class TaskToAdapter {
    public static ArrayList<HashMap<String,String>> generateTasksHashMapList(List<Task> tasksToShow) {
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        for (Task task : tasksToShow)
        {
            HashMap<String, String> map = new HashMap<>();
            map.put("1", "Nazwa: "+task.name);
            map.put("2", "Status: "+task.status);
            map.put("3", "Priorytet: "+task.priority);
            map.put("4", "Data dodania: "+task.getAddedTime());
            map.put("5", "Data ukończenia: "+task.getPlannedTime());
            list.add(map);
        }
        return list;
    }
}
