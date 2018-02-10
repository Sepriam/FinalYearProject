package com.example.matt.runningplanneranddiaryv2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matt on 17/04/2017.
 */

public class create_routine_custom_adapter extends ArrayAdapter<runObject> {

    private Activity context;
    private ArrayList<runObject> array;

    TextView txtRunID;
    TextView txtTime;
    TextView txtDistance;
    TextView txtIncline;

    CheckBox checkBox;

    public create_routine_custom_adapter(Activity context, ArrayList<runObject> objects) {
        super(context, 0, objects);

        this.context = context;
        this.array = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //if there's no view passed, just inflate the activities view.
        //this is not 100% needed
        if (convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.create_routine_activity, parent, false);
        }

        //get the correct object
        final runObject runobj = array.get(position);

        View v =
                LayoutInflater.from(getContext()).inflate(R.layout.create_routine_lv_element, parent, false);

        txtRunID = (TextView) v.findViewById(R.id.runId_list_tv);
        txtTime = (TextView) v.findViewById(R.id.time_list_tv);
        txtDistance = (TextView) v.findViewById(R.id.distance_list_tv);
        txtIncline = (TextView) v.findViewById(R.id.incline_list_tv);
        checkBox = (CheckBox) v.findViewById(R.id.checkbox_list_CB);

        String distanceSet = Double.toString(runobj.getDistance());
        String inclineSet = Double.toString(runobj.getIncline());
        String timeSet = Double.toString(runobj.getTime());
        String runIDSet = Double.toString(runobj.getID());

        txtDistance.setText(distanceSet);
        txtIncline.setText(inclineSet);
        txtTime.setText(timeSet);
        txtRunID.setText(runIDSet);
        checkBox.setEnabled(true);

        return v;
    }
}
