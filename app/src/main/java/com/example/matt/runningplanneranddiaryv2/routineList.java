package com.example.matt.runningplanneranddiaryv2;

import java.util.ArrayList;

/**
 * Created by Matt on 10/03/2017.
 */

public class routineList {

    runObject newRun = new runObject();
    routineObject newRoutine = new routineObject();

    int runCounter = 0;
    private int routineCounter = 0;

    ArrayList<routineObject> listOfRoutines = new ArrayList<>();



    public void addRunFromJustRun(double _distance, double _time, double _incline, double _calories_Burned) {
        for (routineObject a : listOfRoutines) {
            if (a.getRoutineID() == 0) {
                runCounter++;
            }
        }

        newRun.runObject1(runCounter, _distance, _time, _incline, _calories_Burned);

        newRoutine.runsInRoutine.add(newRun);

        newRoutine.setRoutineID(0);

        listOfRoutines.add(newRoutine);

    }

    public void addRunFromCreateRoutine(Double _routineID, ArrayList<runObject> _listOfRuns)
    {


        for (runObject a : _listOfRuns)
        {
            newRoutine.runsInRoutine.add(a);
        }

        newRoutine.setRoutineID(_routineID);

        listOfRoutines.add(newRoutine);

    }

    public int countRoutines(){
        for (routineObject a : listOfRoutines) {
            if (a.getRoutineID() != 0)
                routineCounter++;
        }
        return routineCounter;
    }

}
