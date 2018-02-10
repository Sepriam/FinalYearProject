package com.example.matt.runningplanneranddiaryv2;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class createRoutineAct extends ListActivity {

    calorie_Counter countCals = new calorie_Counter();
    runObject newRunObj = new runObject();
    routineObject newRoutineObj = new routineObject();
    routineList RL = new routineList();
    HomePage_act homepage = new HomePage_act();


    private Button addBtn, finishBtn;
    private EditText edt_dist,edt_time,edt_incline;
    private ListView lv_list;
    private TextView textView;
    private ArrayList<runObject> array = new ArrayList<>();
    private create_routine_custom_adapter adapter;

    private Double IDCounter = 1.0;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_routine_activity);


        addBtn = (Button) findViewById(R.id.create_routine_btn);
        finishBtn = (Button) findViewById(R.id.createRoutineActivityFinishButton);
        edt_dist = (EditText) findViewById(R.id.target_distance_ET_CR);
        edt_incline = (EditText)findViewById(R.id.incline_ET_CR);
        edt_time = (EditText) findViewById(R.id.target_time_ET_CR);
        lv_list = (ListView) findViewById(android.R.id.list);


        array = new ArrayList<runObject>();
        adapter = new create_routine_custom_adapter(this, array);

        lv_list.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRunToRoutine();
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRoutineFinishFunction();
            }
        });

    }

    public void addRunToRoutine()
    {
        if (edt_time.getText().toString().equals("") || edt_incline.getText().toString().equals("") || edt_dist.getText().toString().equals(""))
        {

            Toast.makeText(this, "Invalid entry into a field!",Toast.LENGTH_LONG).show();


        } else {

            double dist = Double.parseDouble(edt_dist.getText().toString());
            double inc = Double.parseDouble(edt_incline.getText().toString());
            double time = Double.parseDouble(edt_time.getText().toString());
            int idCounter = counter;
            counter++;
            final double cals = countCals.calcCalories(time, dist);

            newRunObj.runObject1(idCounter, dist, time, inc, cals);

            array.add(newRunObj);

            adapter.notifyDataSetChanged();

            edt_dist.setText("");
            edt_time.setText("");
            edt_incline.setText("");

            //ID > DIST > TIME > INCLINE > CALS > ISCOMPLETE


        }


    }

    public void createRoutineFinishFunction()
    {

        Double newCounter = 1.0;

        for (routineObject a : RL.listOfRoutines)
        {
            if (a.getRoutineID() == 0.0)
            {

            }
            else
            {
                newCounter++;
            }
        }

        RL.addRunFromCreateRoutine(newCounter, array);

        saveRoutineToFile();

        finish();

    }

    public void saveRoutineToFile(){


        //initiating csv separation strings
        String COMMA_SEPARATOR = ",";
        String NEW_LINE_SEPARATOR = "\n";

        //creating a new routineObject
        routineObject routine = new routineObject();

        //setting the routine object to be the last routine in the list of routines (i.e. the one created before this save function call)
        routine = RL.listOfRoutines.get(RL.listOfRoutines.size() - 1);

        try {
            //adding to the current file (append) default if to make it internal storage and private
            FileOutputStream fileOutputStream = openFileOutput(homepage.FileName, Context.MODE_APPEND);



            for (runObject a : routine.runsInRoutine)
            {
                //ID > DIST > TIME > INCLINE > CALS > ISCOMPLETE
                fileOutputStream.write(String.valueOf(routine.getRoutineID()).toString().getBytes());
                fileOutputStream.write(COMMA_SEPARATOR.getBytes());
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
                openFileInput(homepage.FileName).close();


            } catch (Exception e) {

                e.printStackTrace();

            }
        }

        return;

    }


}
