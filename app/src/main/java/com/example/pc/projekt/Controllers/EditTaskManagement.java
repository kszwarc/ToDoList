package com.example.pc.projekt.Controllers;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.pc.projekt.R;

/**
 * Created by pc on 2017-05-25.
 */

public class EditTaskManagement extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_management);
        Intent intent = getIntent();
        Bundle extra = new Bundle();
        extra.putInt("taskId", intent.getIntExtra("taskId", 0));
        TabHost mTabHost = getTabHost();
        intent = new Intent(this, EditTask.class);
        intent.putExtras(extra);
        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.edit)).setIndicator(getString(R.string.edit)).setContent(intent));
        intent = new Intent(this, ShowAttachments.class);
        intent.putExtras(extra);
        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.showAttachments)).setIndicator(getString(R.string.showAttachments)).setContent(intent));
        intent = new Intent(this, AddAtachment.class);
        intent.putExtras(extra);
        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.addAttachments)).setIndicator(getString(R.string.addAttachments)).setContent(intent));
        mTabHost.setCurrentTab(0);
    }
}
