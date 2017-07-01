package com.example.pc.projekt.Models.DTO;

import com.example.pc.projekt.Models.Priority;
import java.util.*;

/**
 * Created by pc on 2017-05-25.
 */

public class PrioritiesToShow {
    public static String[] getPrioritiesNames(List<Priority> priorities) {
        String[] names = new String[priorities.size()];
        for(int i=0; i<priorities.size(); i++)
            names[i] = priorities.get(i).priority;
        return names;
    }

    public static Integer[] getPrioritiesId(List<Priority> priorities) {
        Integer[] ids = new Integer[priorities.size()];
        for(int i=0; i<priorities.size(); i++)
            ids[i] = priorities.get(i).id;
        return ids;
    }
}
