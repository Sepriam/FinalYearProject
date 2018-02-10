package com.example.matt.runningplanneranddiaryv2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class running_planner_act extends ListActivity {


    HomePage_act home = new HomePage_act();
    runObject loadRun = new runObject();
    routineObject loadRoutine = new routineObject();
    routineList loadRoutineList = new routineList();

    ArrayAdapter<String> adapter;

    ArrayList<String> routineIDList2 = new ArrayList();

    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_planner_activity);



        myList = (ListView) findViewById(android.R.id.list);
        //testtv = (TextView)findViewById(R.id.textthistv);

        String loadContent = loadAllContent(home.FileName);

        //if the content loaded from file is not null
        if (loadContent != "")
        {
            reassignContent(loadContent);
        }



        //testtv.setText(Integer.toString(loadRoutineList.listOfRoutines.get(0).getRoutineID()));

        for (routineObject a : loadRoutineList.listOfRoutines)
        {
            if (a.getRoutineID() == 0.0)
            {
            }
            else
            {
                routineIDList2.add(Double.toString(a.getRoutineID()));
            }

        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, routineIDList2);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();

        String loadContent = loadAllContent(home.FileName);

        //if the content loaded from file is not null
        if (loadContent != "")
        {
            reassignContent(loadContent);
        }

        for (routineObject a : loadRoutineList.listOfRoutines)
        {
            if (a.getRoutineID() == 0.0)
            {
                return;
            }
            else
            {
                routineIDList2.add(Double.toString(a.getRoutineID()));
            }

        }
        adapter.notifyDataSetChanged();
    }

    /*
    protected void onListItemClick(ListView l, View v, int position, long id){
        Intent myIntent = new Intent(this, createRoutineAct.class);
        String idOfRoutine = Integer.toString(position);
        myIntent.putExtra("ID", idOfRoutine);
        startActivity(myIntent);

    }*/


    public String loadAllContent(String fileName)
    {

        String result = "";

        try {
            FileInputStream fileInputStream = openFileInput(fileName);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();

            while((result=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(result + "\n");
                //will add newline at end of line
            }

            result = stringBuilder.toString();

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public void reassignContent(String loadedFromFile)
    {
        for (routineObject a : loadRoutineList.listOfRoutines)
        {
            a.runsInRoutine.clear();
        }
        loadRoutineList.listOfRoutines.clear();

        //creating a string array to split the input string at each new line
        String[] splitAtNewline = loadedFromFile.split("\n");

        //for loop to iterate through each newline string
        for(String line : splitAtNewline)
        {
            //creating a string array to split the input string further at comma separators
            String[] splitAtCommaSeparator = line.split(",");

            Boolean inRoutineList = false;

            //assign the routine ID to check whether the routine with that Id has already been partly loaded
            Double routineId = Double.parseDouble(splitAtCommaSeparator[0]);

            loadRoutine.setRoutineID(routineId);

            Double _runID = Double.parseDouble(splitAtCommaSeparator[1]);
            Double _distance = Double.parseDouble(splitAtCommaSeparator[2]);
            Double _time = Double.parseDouble(splitAtCommaSeparator[3]);
            Double _incline = Double.parseDouble(splitAtCommaSeparator[4]);
            Double _calories = Double.parseDouble(splitAtCommaSeparator[5]);
            Boolean _complete = Boolean.parseBoolean(splitAtCommaSeparator[6]);

            loadRun.runObject2(_runID, _distance, _time, _incline, _calories, _complete);

            if (loadRoutine.runsInRoutine.size() == 0)
            {
                loadRoutine.runsInRoutine.add(loadRun);
                loadRoutineList.listOfRoutines.add(loadRoutine);
            }
            else {
                for (int i = 0; i < loadRoutineList.listOfRoutines.size(); i++)
                {
                    if (loadRoutineList.listOfRoutines.get(i).getRoutineID() == 0 && routineId == 0)
                    {
                        loadRoutineList.listOfRoutines.get(i).runsInRoutine.add(loadRun);
                    }

                    if (loadRoutineList.listOfRoutines.get(i).getRoutineID() == 0 && routineId != 0) {}

                    if (loadRoutineList.listOfRoutines.get(i).getRoutineID() != 0 && routineId != 0)
                    {
                        if (loadRoutineList.listOfRoutines.get(i).getRoutineID() == routineId)
                        {
                            loadRoutineList.listOfRoutines.get(i).runsInRoutine.add(loadRun);
                        }
                    }
                }

                /*for (int i = 0; i < loadRoutineList.listOfRoutines.size(); i++)
                {
                    if (loadRoutineList.listOfRoutines.get(i).getRoutineID() == routineId)
                    {
                        loadRoutineList.listOfRoutines.get(i).runsInRoutine.add(loadRun);
                    }

                }*/
            }


        }
    }


    public void to_create_routine_act(View view) {
        Intent myIntent = new Intent(this, createRoutineAct.class);
        startActivity(myIntent);
    }


    public void FinishRunningPlannerAct(View view) {
        finish();
    }
}