package com.example.pc.projekt.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.widget.BaseAdapter;

import com.example.pc.projekt.R;

/**
 * Created by pc on 2017-05-25.
 */

public class TasksAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst, txtSecond, txtThird, txtFourth, txtFifth;

    public TasksAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        if(convertView == null){
            convertView=inflater.inflate(R.layout.adapter_for_tasks, null);
            txtFirst=(TextView) convertView.findViewById(com.example.pc.projekt.R.id.name);
            txtSecond=(TextView) convertView.findViewById(com.example.pc.projekt.R.id.priority);
            txtThird=(TextView) convertView.findViewById(com.example.pc.projekt.R.id.status);
            txtFourth=(TextView) convertView.findViewById(com.example.pc.projekt.R.id.added);
            txtFifth=(TextView) convertView.findViewById(com.example.pc.projekt.R.id.planned);
        }
        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get("1"));
        txtSecond.setText(map.get("2"));
        txtThird.setText(map.get("3"));
        txtFourth.setText(map.get("4"));
        txtFifth.setText(map.get("5"));
        return convertView;
    }

}