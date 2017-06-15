package com.example.pc.projekt.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.Models.TasksAdapter;
import com.example.pc.projekt.R;
import com.example.pc.projekt.Models.TaskToAdapter;

import java.util.*;

import android.view.View;
import android.widget.*;

/**
 * Created by pc on 2017-05-25.
 */

public class ShowTasks extends AppCompatActivity {
    protected ListView tasks;
    protected List<Task> tasksToShow;
    String condition;

    public ShowTasks() {
        this.condition = "";
    }

    public ShowTasks(String condition) {
        this.condition = condition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_tasks);
        ((RadioGroup) findViewById(R.id.rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                sortAdapter(checkedId);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        addTasks();
    }

    protected void addTasks() {
        tasks = (ListView) findViewById(R.id.list);
        tasksToShow = TaskEntityQueries.getTasksListToShow(getApplicationContext(), this.condition, "");
        addTasksToShowToListAdapter(tasksToShow);
        tasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowTasks.this, EditTaskManagement.class);
                Bundle extra = new Bundle();
                extra.putInt("taskId", tasksToShow.get(position).id);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
    }

    private void sortAdapter(int checkedId) {
        String orderBy = "";
        switch (checkedId) {
            case R.id.radioButtonAdded:
                orderBy = "ORDER BY added_date ASC";
                break;
            case R.id.radioButtonFinished:
                orderBy = "ORDER BY finished_date ASC";
                break;
            case R.id.radioButtonName:
                orderBy = "ORDER BY task ASC";
                break;
            case R.id.radioButtonPriority:
                orderBy = "ORDER BY priority ASC";
                break;
            case R.id.radioButtonStatus:
                orderBy = "ORDER BY status ASC";
                break;
        }
        List<Task> tasksToShow = TaskEntityQueries.getTasksListToShow(getApplicationContext(), this.condition, orderBy);
        addTasksToShowToListAdapter(tasksToShow);
    }

    private void addTasksToShowToListAdapter(List<Task> tasksToShow) {
        ArrayList<HashMap<String, String>> list = TaskToAdapter.generateTasksHashMapList(tasksToShow);
        TasksAdapter adapter = new TasksAdapter(this, list);
        tasks.setAdapter(adapter);
    }
}
