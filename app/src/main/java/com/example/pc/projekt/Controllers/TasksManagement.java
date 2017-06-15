package com.example.pc.projekt.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import android.content.*;
import com.example.pc.projekt.Models.Database;
import com.example.pc.projekt.R;
import android.app.*;

/**
 * Created by pc on 2017-05-25.
 */

public class TasksManagement extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_management);
        TabHost mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.show)).setIndicator(getString(R.string.show)).setContent(new Intent(this  ,ShowTasks.class )));
        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.add)).setIndicator(getString(R.string.add)).setContent(new Intent(this , AddTask.class )));
        mTabHost.setCurrentTab(0);
    }
}
