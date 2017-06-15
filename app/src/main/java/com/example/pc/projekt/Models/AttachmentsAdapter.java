package com.example.pc.projekt.Models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.projekt.R;

import java.util.List;

/**
 * Created by pc on 2017-05-25.
 */

public class AttachmentsAdapter extends ArrayAdapter<Attachement> {
    Activity activity;
    ImageView image;
    TextView text;

    public AttachmentsAdapter(Activity activity, List<Attachement> attachements) {
        super(activity, 0, attachements);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        if(convertView == null){
            convertView=inflater.inflate(R.layout.adapter_for_attachments, null);
            image=(ImageView) convertView.findViewById(R.id.image);
            text=(TextView) convertView.findViewById(R.id.path);
        }
        final Attachement attachment = getItem(position);
        image.setImageBitmap(attachment.photo);
        text.setText(attachment.path);
        return convertView;
    }
}