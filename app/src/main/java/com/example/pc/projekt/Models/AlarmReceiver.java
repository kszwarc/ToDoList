package com.example.pc.projekt.Models;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.pc.projekt.Controllers.MainActivity;
import com.example.pc.projekt.Controllers.Notify;
import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.R;

import java.util.List;

/**
 * Created by pc on 2017-05-26.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        List<Task> tasksToShow = TaskEntityQueries.getTasksListToShow(context, "WHERE status='Na liście' AND finished_date <= DATETIME('now')", "");
        if(tasksToShow.size()>0)
            showNotification(context.getString(R.string.tasksAfterTime), tasksToShow, 001, context);
        tasksToShow = TaskEntityQueries.getTasksListToShow(context, "WHERE status='Na liście' AND DATE(finished_date) == DATE(DATETIME('now'))", "");
        if(tasksToShow.size()>0)
            showNotification(context.getString(R.string.tasksToday), tasksToShow, 002, context);
    }

    private void showNotification(String title, List<Task> tasks, int id, Context context) {
        String text = generateTasksNames(tasks, context);
        Intent intent = new Intent(context, Notify.class);
        intent.putExtra("text", text);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.list)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setContentIntent(pendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }

    private String generateTasksNames(List<Task> tasks, Context context) {
        StringBuilder sb = new StringBuilder("");
        for (Task task : tasks)
            sb.append(context.getString(R.string.name)+": "+task.name+" ("+context.getString(R.string.priority)+": "+task.priority+"; "+
                    context.getString(R.string.date)+": "+task.getPlannedTime()+")\n");
        return sb.toString();
    }
}