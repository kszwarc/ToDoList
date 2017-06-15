package com.example.pc.projekt.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.Models.TasksAdapter;
import com.example.pc.projekt.R;
import com.example.pc.projekt.Models.TaskToAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class ToDoList extends ShowTasks {

    public ToDoList()
    {
        super("WHERE status='Na liście'");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.radioButtonStatus).setVisibility(View.INVISIBLE);
    }
}
