package com.example.pc.projekt.Models;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;
import java.text.*;

/**
 * Created by pc on 2017-05-25.
 */

public class Task{

    public int id, priorityId, statusId;
    public String name, priority, status;
    private String addedTime, plannedTime;

    public void setAddedTime(String time)
    {
        if (isDateTimeValid(time))
            addedTime = time;
    }

    public String getAddedTime()
    {
        return addedTime;
    }

    public void setPlannedTime(String time)
    {
        if (isDateTimeValid(time))
            plannedTime = time;
    }

    public String getPlannedTime()
    {
        return plannedTime;
    }

    public static boolean validateFormData(String name, int priorityId, int statusId, String date)
    {
        return name.length()>0 && priorityId>0 && statusId>0 && isDateTimeValid(date);
    }

    private static boolean isDateTimeValid(String date)
    {
        String[] dateAndTime = date.split(" - ");
        if (dateAndTime.length!=2)
            return false;
        else if (!isDateValid(dateAndTime[0]))
            return false;
        return isTimeValid(dateAndTime[1]);
    }

    private static boolean isTimeValid(String text)
    {
        String[] time = text.split(":");
        if (time.length!=2)
            return false;
        return areHoursValid(time[0]) && areMinutesValid(time[1]);
    }

    private static boolean areMinutesValid(String minutes)
    {
        try
        {
            int hour = Integer.parseInt(minutes);
            return hour >=0 && hour<60;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    private static boolean areHoursValid(String hours)
    {
        try
        {
            int hour = Integer.parseInt(hours);
            return hour >=0 && hour<=24;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    private static boolean isDateValid(String date)
    {
        String format = "dd/mm/yyyy";
        try {
            DateFormat df = new SimpleDateFormat(format);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
