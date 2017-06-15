package com.example.pc.projekt.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.projekt.Models.Commands.TaskEntityCommands;
import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.R;

import java.util.Arrays;

/**
 * Created by pc on 2017-05-25.
 */

public class EditTask extends AddTask {
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        name = (EditText)findViewById(R.id.editTextName);
        task = getTask();
        setDateTime();
        setPriorities();
        setStatuses();
        setTaskData();
    }

    private void setTaskData()
    {
        name.setText(task.name.toString());
        dateTime.setText(task.getPlannedTime().toString());
        status.setSelection(Arrays.asList(statusesId).indexOf(task.statusId));
        priority.setSelection(Arrays.asList(prioritiesId).indexOf(task.priorityId));
    }

    private Task getTask()
    {
        Intent intent = getIntent();
        return TaskEntityQueries.getTask(getApplicationContext(), intent.getIntExtra("taskId", 0));
    }

    public void delete(View v)
    {
        DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TaskEntityCommands.deleteTask(getApplicationContext(), task.id);
                Toast.makeText(getApplicationContext(), getString(R.string.deleted), Toast.LENGTH_LONG).show();
                finish();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.confirm)).setPositiveButton(getString(R.string.yes), deleteListener)
                .setNegativeButton(getString(R.string.no), null).show();
    }

    public void edit(View v)
    {
        if (Task.validateFormData(name.getText().toString(), prioritiesId[priority.getSelectedItemPosition()], statusesId[status.getSelectedItemPosition()], dateTime.getText().toString()))
        {
            Task task = prepareTask();
            task.id = this.task.id;
            TaskEntityCommands.updateTask(getApplicationContext(),task);
            Toast.makeText(getApplicationContext(), getString(R.string.updated), Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(), getString(R.string.invalidData), Toast.LENGTH_LONG).show();
    }

}
