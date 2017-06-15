package com.example.pc.projekt.Models.DTO;

import com.example.pc.projekt.Models.Priority;
import com.example.pc.projekt.Models.Status;

import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class StatusesToShow {
    public static String[] getStatusesNames(List<Status> statuses)
    {
        String[] names = new String[statuses.size()];
        for(int i=0; i<statuses.size(); i++)
            names[i] = statuses.get(i).status;
        return names;
    }

    public static Integer[] getStatusesId(List<Status> statuses)
    {
        Integer[] ids = new Integer[statuses.size()];
        for(int i=0; i<statuses.size(); i++)
            ids[i] = statuses.get(i).id;
        return ids;
    }
}
