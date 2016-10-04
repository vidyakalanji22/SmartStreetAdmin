package com.msd.smartstreet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.msd.utils.SensorInfo;

import java.util.List;

import admin.msd.com.smartstreetadmin.R;

/**
 * Adapter class to list all the sensors
 */
public class sensorArrayAdapter extends ArrayAdapter<SensorInfo>{

    List<SensorInfo> sensorInfoList;

    public sensorArrayAdapter(Context context,List<SensorInfo> sensorInfoList) {
        super(context, R.layout.sensor_list_item, sensorInfoList);
        this.sensorInfoList = sensorInfoList;
    }

    @Override
    public SensorInfo getItem(int position) {

        return sensorInfoList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.sensor_list_item, parent, false);
        ImageView iv = (ImageView) customView.findViewById(R.id.status);
        final TextView sensorId = (TextView) customView.findViewById(R.id.sensorId);
        final TextView treeName = (TextView) customView.findViewById(R.id.treeName);
        final TextView date = (TextView) customView.findViewById(R.id.date);


        SensorInfo sensor = getItem(position);
        sensorId.setText(sensor.getSensorNumber());
        treeName.setText(sensor.getTreeName());
        date.setText(sensor.getInstalledDate().toString());
        if(sensor.getStatus().equalsIgnoreCase("Active")) {
            iv.setImageResource(R.drawable.active);
        }else{
            iv.setImageResource(R.drawable.inactive);
        }

        return customView;
    }
}
