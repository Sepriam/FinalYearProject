package com.example.matt.runningplanneranddiaryv2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * Created by Matt on 25/04/2017.
 */

public class Just_Run_Fragment_2 extends Fragment implements View.OnClickListener{

    //create button widgets
    Button accelBtn, gpsBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //create a view to return
        View v =  inflater.inflate(R.layout.jr_fragment2, container, false);

        //assign widgets to respecitve widgets in xml
        accelBtn = (Button)v.findViewById(R.id.JR_fragment2_accel);
        gpsBtn = (Button)v.findViewById(R.id.JR_fragment2_GPS);

        //set on click event handler for accelerometer button
        accelBtn.setOnClickListener(this);

        //return the view in fragment
        return v;
    }

    //on click button to start Accelerometer
    @Override
    public void onClick(View view) {
        //because it's not the activity, need to get context of activity
        Intent myIntent = new Intent(getContext(), accelerometer_Activity.class);
        startActivity(myIntent);
    }

}
