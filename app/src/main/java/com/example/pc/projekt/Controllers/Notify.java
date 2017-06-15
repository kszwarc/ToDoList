package com.example.pc.projekt.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.Models.TaskToAdapter;
import com.example.pc.projekt.Models.TasksAdapter;
import com.example.pc.projekt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class Notify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.text)).setText(intent.getStringExtra("text"));
    }
}
