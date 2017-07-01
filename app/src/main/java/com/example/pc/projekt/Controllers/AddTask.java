package com.example.pc.projekt.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.projekt.Models.Commands.TaskEntityCommands;
import com.example.pc.projekt.Models.DTO.StatusesToShow;
import com.example.pc.projekt.Models.DatePicker;
import com.example.pc.projekt.Models.Priority;
import com.example.pc.projekt.Models.Queries.PriorityEntityQueries;
import com.example.pc.projekt.Models.Queries.StatusEntityQueries;
import com.example.pc.projekt.Models.Status;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.Models.DTO.PrioritiesToShow;
import com.example.pc.projekt.R;

import android.text.InputType;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class AddTask extends AppCompatActivity {
    EditText dateTime, name;
    Spinner priority, status;
    Integer[] prioritiesId, statusesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        name = (EditText)findViewById(R.id.editTextName);
        setDateTime();
        setPriorities();
        setStatuses();
    }

    public void add(View v) {
        if (Task.validateFormData(name.getText().toString(), prioritiesId[priority.getSelectedItemPosition()], statusesId[status.getSelectedItemPosition()], dateTime.getText().toString()))
        {
            TaskEntityCommands.insertNewTask(getApplicationContext(), prepareTask());
            Toast.makeText(getApplicationContext(), getString(R.string.addedTask), Toast.LENGTH_LONG).show();
            clearFields();
        }
        else
            Toast.makeText(getApplicationContext(), getString(R.string.invalidData), Toast.LENGTH_LONG).show();
    }

    protected Task prepareTask() {
        Task task = new Task();
        task.priorityId = prioritiesId[priority.getSelectedItemPosition()];
        task.statusId = statusesId[status.getSelectedItemPosition()];
        task.name = name.getText().toString();
        task.setPlannedTime(dateTime.getText().toString());
        return task;
    }

    protected void setStatuses() {
        status = (Spinner)findViewById(R.id.spinnerStatus);
        List<Status> statuses = StatusEntityQueries.getStatusList(getApplicationContext());
        String[] list = StatusesToShow.getStatusesNames(statuses);
        statusesId = StatusesToShow.getStatusesId(statuses);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        status.setAdapter(adapter);
    }

    protected void setPriorities() {
        priority = (Spinner)findViewById(R.id.spinnerPriority);
        List<Priority> priorities = PriorityEntityQueries.getPriorities(getApplicationContext());
        String[] list = PrioritiesToShow.getPrioritiesNames(priorities);
        prioritiesId = PrioritiesToShow.getPrioritiesId(priorities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        priority.setAdapter(adapter);
    }

    protected void setDateTime() {
        dateTime = (EditText)findViewById(R.id.editTextDate);
        dateTime.setInputType(InputType.TYPE_NULL);
        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
    }

    private void showDatePickerDialog(View v) {
        DatePicker picker = new DatePicker();
        picker.setEditText(dateTime);
        picker.show(getFragmentManager(), "datePicker");
    }

    private void clearFields() {
        dateTime.setText("");
        name.setText("");
    }
}
