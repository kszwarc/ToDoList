package com.example.pc.projekt.Controllers;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pc.projekt.Models.AlarmReceiver;
import com.example.pc.projekt.Models.Database;
import com.example.pc.projekt.Models.Export;
import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.R;

import android.view.*;
import android.content.*;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkOldTasks();
        checkCameraPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length==2 && permissions[1].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            exportList();
    }

    public void launchListMenu(View v) {
        Intent intent = new Intent(MainActivity.this, ToDoList.class);
        startActivity(intent);
    }

    public void launchTasksMenu(View v) {
        Intent intent = new Intent(MainActivity.this, TasksManagement.class);
        startActivity(intent);
    }

    public void launchExportList(View v) {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        else
            exportList();
    }

    private void exportList() {
        if (Export.export(getApplicationContext()))
            Toast.makeText(getApplication(), getString(R.string.exportedListSuccessful), Toast.LENGTH_LONG).show();
    }

    private void checkCameraPermissions() {
        int permission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED )
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 222);
    }

    private void checkOldTasks() {
        Calendar calendar = Calendar.getInstance();
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
        final int MINUTE = 60000;
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), MINUTE, pendingIntent);
    }
}
