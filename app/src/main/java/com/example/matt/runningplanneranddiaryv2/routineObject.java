package com.example.matt.runningplanneranddiaryv2;

import java.util.ArrayList;

/**
 * Created by Matt on 10/03/2017.
 */

public class routineObject {

    private double routineID;

    ArrayList<runObject> runsInRoutine = new ArrayList<>();

    public double getRoutineID() {
        return routineID;
    }

    public void setRoutineID(double routineID) {
        this.routineID = routineID;
    }
}
