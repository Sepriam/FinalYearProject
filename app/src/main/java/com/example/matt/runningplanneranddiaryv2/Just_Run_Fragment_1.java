package com.example.matt.runningplanneranddiaryv2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Matt on 25/04/2017.
 */

public class Just_Run_Fragment_1 extends Fragment implements View.OnClickListener {

    //instantiate all the widgets to their respective variable names
    Button saveRun_btn;
    EditText distance_Edt, time_Edt, incline_Edt;
    TextView calorie_Burn_Tv;

    //create and set variables to record the different just run elements
    double distance = 0;
    double time = 0;
    double incline = 0;
    double calories_burned = 0;

    //make the new class objects
    calorie_Counter calorieCounter = new calorie_Counter();
    routineList makeRoutine = new routineList();
    HomePage_act home = new HomePage_act();


    //On create fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //create a new view with the jr_fragment1 xml layout
        View v =  inflater.inflate(R.layout.jr_fragment1, container, false);

        //assign the variables to their resources
        saveRun_btn = (Button)v.findViewById(R.id.Save_Just_Run_btn);
        distance_Edt = (EditText)v.findViewById(R.id.distance_edt);
        time_Edt = (EditText)v.findViewById(R.id.time_edt);
        incline_Edt = (EditText)v.findViewById(R.id.incline_edt);
        calorie_Burn_Tv = (TextView)v.findViewById(R.id.calorie_burn_tv);

        //set the button's on click listener
        saveRun_btn.setOnClickListener(this);

        return v;


    }


    @Override
    public void onClick(View view) {
        //on click grab data from allocated edittext widgets and assign to the variables

        try {

                distance = Double.parseDouble(distance_Edt.getText().toString());
                time = Double.parseDouble(time_Edt.getText().toString());
                incline = Double.parseDouble(incline_Edt.getText().toString());

                //call the calories burned method to calculate calorie burn
                calories_burned = calorieCounter.calcCalories(time, distance);

                calorie_Burn_Tv.setText(Double.toString(calories_burned));

                //add the run to the routine list associated with just runs
                makeRoutine.addRunFromJustRun(distance, time, incline, calories_burned);


                //-----------------------------------------------------------------------------
                saveJustRun(view);


                getActivity().finish();
        }
        catch (Exception e)
        {

        }


    }

    public void saveJustRun(View v){

        //int to get the total size of the list
        int lastIndexOfList = (makeRoutine.listOfRoutines.size() - 1);

        //initiating csv separation strings
        String COMMA_SEPARATOR = ",";
        String NEW_LINE_SEPARATOR = "\n";

        //creating a new routineObject
        routineObject routine = new routineObject();

        //setting the routine object to be the last routine in the list of routines (i.e. the one created before this save function call)
        routine = makeRoutine.listOfRoutines.get(lastIndexOfList);

        try {
            //adding to the current file (append) default if to make it internal storage and private
            FileOutputStream fileOutputStream = getContext().openFileOutput(home.FileName, Context.MODE_APPEND);

            fileOutputStream.write(String.valueOf(routine.getRoutineID()).toString().getBytes());
            fileOutputStream.write(COMMA_SEPARATOR.getBytes());

            for (runObject a : routine.runsInRoutine)
            {
                //ID > DIST > TIME > INCLINE > CALS > ISCOMPLETE
                fileOutputStream.write(String.valueOf(a.getID()).toString().getBytes());
                fileOutputStream.write(COMMA_SEPARATOR.getBytes());
                fileOutputStream.write(String.valueOf(a.getDistance()).toString().getBytes());
                fileOutputStream.write(COMMA_SEPARATOR.getBytes());
                fileOutputStream.write(String.valueOf(a.getTime()).toString().getBytes());
                fileOutputStream.write(COMMA_SEPARATOR.getBytes());
                fileOutputStream.write(String.valueOf(a.getIncline()).toString().getBytes());
                fileOutputStream.write(COMMA_SEPARATOR.getBytes());
                fileOutputStream.write(String.valueOf(a.getCalories_Burned()).toString().getBytes());
                fileOutputStream.write(COMMA_SEPARATOR.getBytes());
                fileOutputStream.write(String.valueOf(a.isComplete()).toString().getBytes());
                fileOutputStream.write(NEW_LINE_SEPARATOR.getBytes());
            }

            fileOutputStream.flush();
            fileOutputStream.close();



        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            try {

                // Close the writer regardless of what happens...
                getContext().openFileInput(home.FileName).close();


            } catch (Exception e) {

                e.printStackTrace();

            }
        }

        return;

    }




}
