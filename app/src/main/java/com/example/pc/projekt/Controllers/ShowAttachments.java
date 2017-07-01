package com.example.pc.projekt.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.pc.projekt.Models.Attachement;
import com.example.pc.projekt.Models.AttachmentsAdapter;
import com.example.pc.projekt.Models.Commands.AttachmentEntityCommands;
import com.example.pc.projekt.Models.Queries.AttachmentsQueries;
import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.R;

import java.io.File;
import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class ShowAttachments extends AppCompatActivity {
    private List<Attachement> attachementList;
    private int attachmentToDeleteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_attachments);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }

    private void populateList() {
        Intent intent = getIntent();
        Task task = TaskEntityQueries.getTask(getApplicationContext(), intent.getIntExtra("taskId", 0));
        attachementList = AttachmentsQueries.getAttachementsListToShow(getApplicationContext(), task.id);
        populateAttachmentsWithImages(attachementList);
        ListView list = (ListView) findViewById(R.id.list);
        AttachmentsAdapter adapter = new AttachmentsAdapter(this, attachementList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                attachmentToDeleteId = attachementList.get(position).id;
                handleDelete();
            }
        });
    }

    private void handleDelete() {
        DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AttachmentEntityCommands.deleteAttachment(getApplicationContext(), attachmentToDeleteId);
                Toast.makeText(getApplicationContext(), getString(R.string.deletedAttachment), Toast.LENGTH_LONG).show();
                finish();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.confirmDeleteAttachment)).setPositiveButton(getString(R.string.yes), deleteListener)
                .setNegativeButton(getString(R.string.no), null).show();
    }

    private void populateAttachmentsWithImages(List<Attachement> attachementsList) {
        for (Attachement attachement : attachementsList)
        {
            File imgFile = new  File(attachement.path);
            if(imgFile.exists())
                attachement.photo = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
    }
}
