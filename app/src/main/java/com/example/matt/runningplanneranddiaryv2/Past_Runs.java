package com.example.matt.runningplanneranddiaryv2;

        import android.app.ListActivity;
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

public class Past_Runs extends ListActivity {


    HomePage_act home = new HomePage_act();
    runObject loadRun = new runObject();
    routineObject loadRoutine = new routineObject();
    routineList loadRoutineList = new routineList();

    ArrayAdapter<String> adapter;
    showPastRunsActAdapter custom_adapter;

    ArrayList<String> routineIDList = new ArrayList();

    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past__runs_activity);
        myList = (ListView) findViewById(android.R.id.list);
        //testtv = (TextView)findViewById(R.id.textthistv);

        String fileContent = loadAllContent(home.FileName);

        reassignContent(fileContent);

        //testtv.setText(Integer.toString(loadRoutineList.listOfRoutines.get(0).getRoutineID()));


        //for loop to get all the routine ids
        for (routineObject a : loadRoutineList.listOfRoutines)
        {
            String _routineID = String.valueOf(a.getRoutineID());
            routineIDList.add(_routineID);

        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, routineIDList);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();



        /*myList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

    }

    public void changeListViewAdapter(int _routineID) {
        ArrayList<runObject> newArrayListString = new ArrayList<>();

        double _routineIDDouble = Double.parseDouble(routineIDList.get(_routineID));


        //look through all routines
        for (routineObject a : loadRoutineList.listOfRoutines)
        {
            //if the routine id is the same as the one clicked
            if (a.getRoutineID() == _routineIDDouble)
            {
                //get all the runs inside this routine and add them to list
                for (runObject b : a.runsInRoutine)
                {
                    newArrayListString.add(b);
                }
            }
        }

        //create a new instance of the custom adapter with the array just created
        custom_adapter = new showPastRunsActAdapter(this, newArrayListString);
        //set the listview to this new adapter
        setListAdapter(custom_adapter);
        //notify the adapter that the array data has changed and update the listview
        adapter.notifyDataSetChanged();

    }

    public void onListItemClick(ListView l, View v, int position, long id)
    {
        changeListViewAdapter(position);
    }


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

            //loading the indexs of the previous string array to the runObject's values
            //ID > DIST > TIME > INCLINE > CALS > ISCOMPLETE

            Double _runID = Double.parseDouble(splitAtCommaSeparator[1]);
            Double _distance = Double.parseDouble(splitAtCommaSeparator[2]);
            Double _time = Double.parseDouble(splitAtCommaSeparator[3]);
            Double _incline = Double.parseDouble(splitAtCommaSeparator[4]);
            Double _calories = Double.parseDouble(splitAtCommaSeparator[5]);
            Boolean _complete = Boolean.parseBoolean(splitAtCommaSeparator[6]);

            loadRun.runObject2(_runID, _distance, _time, _incline, _calories, _complete);

            /*if (loadRoutine.runsInRoutine.size() == 0)
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

                for (int i = 0; i < loadRoutineList.listOfRoutines.size(); i++)
                {
                    if (loadRoutineList.listOfRoutines.get(i).getRoutineID() == routineId)
                    {
                        loadRoutineList.listOfRoutines.get(i).runsInRoutine.add(loadRun);
                    }

                }
            }*/





            //Check if a routine with that id is already read from the file




            /*for (int i = 0; i < loadRoutineList.listOfRoutines.size(); i++)
            {
                if (loadRoutineList.listOfRoutines.get(i).getRoutineID() == routineId)
                {
                    loadRoutineList.listOfRoutines.get(i).runsInRoutine.add(loadRun);
                    inRoutineList = true;
                }

            }*/

            if (!inRoutineList)
            {
                loadRoutine.runsInRoutine.add(loadRun);
                loadRoutineList.listOfRoutines.add(loadRoutine);
            }

        }
    }
}

