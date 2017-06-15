package com.example.pc.projekt.Controllers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pc.projekt.Models.Commands.AttachmentEntityCommands;
import com.example.pc.projekt.Models.Queries.AttachmentsQueries;
import com.example.pc.projekt.Models.Queries.TaskEntityQueries;
import com.example.pc.projekt.Models.Task;
import com.example.pc.projekt.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by pc on 2017-05-25.
 */

public class AddAtachment extends AppCompatActivity {
    private static int count = 0;
    private Uri outputFileUri;
    private String dir;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_atachment);
        Intent intent = getIntent();
        task = TaskEntityQueries.getTask(getApplicationContext(), intent.getIntExtra("taskId", 0));
        count = AttachmentsQueries.getLastAttachmentId(getApplicationContext(), task.id)+1;
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/"+task.id+"/";
        File newdir = new File(dir);
        newdir.mkdirs();
    }

    private File createNewFile(String extension)
    {
        count++;
        String file = dir + count + "."+extension;
        File newFile = new File(file);
        try {
            newFile.createNewFile();
        }
        catch (IOException e) {  }
        return newFile;
    }

    public void buttonOnClickSaveMovie(View v)
    {
        outputFileUri = Uri.fromFile(createNewFile("mp4"));
        int permission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED)
            Toast.makeText(getApplicationContext(), getString(R.string.cameraError), Toast.LENGTH_LONG);
        else
            saveMovie();
    }

    public void buttonOnClickSavePhoto(View v)
    {
        outputFileUri = Uri.fromFile(createNewFile("jpg"));
        int permission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED)
            Toast.makeText(getApplicationContext(), getString(R.string.cameraError), Toast.LENGTH_LONG);
        else
            savePhoto();
    }

    private void saveMovie()
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, 0);
        AttachmentEntityCommands.insertNewAttachment(getApplicationContext(), task.id, outputFileUri.getPath());
    }

    private void savePhoto()
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, 1);
        AttachmentEntityCommands.insertNewAttachment(getApplicationContext(), task.id, outputFileUri.getPath());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}