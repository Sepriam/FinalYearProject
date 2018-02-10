package com.example.matt.runningplanneranddiaryv2;

/**
 * Created by Matt on 10/03/2017.
 */

public class runObject {

    //ID > DIST > TIME > INCLINE > CALS > ISCOMPLETE

    private double ID;
    //distance in kilometers
    private double Distance;
    //time in minutes
    private double Time;
    private double Incline;
    private double Calories_Burned;
    private boolean IsComplete = false;

    public void runObject1(double _id, double _distance, double _time, double _incline, double _calories_Burned)
    {
        this.ID = _id;
        this.Distance = _distance;
        this.Time = _time;
        this.Incline = _incline;
        this.Calories_Burned = _calories_Burned;

        // note this is for the just run page so all the run objects added here will be complete by default

        this.IsComplete = true;
    }

    public void runObject2(double _id, double _distance, double _time, double _incline, double _calories_Burned, boolean _IsComplete)
    {
        this.ID = _id;
        this.Distance = _distance;
        this.Time = _time;
        this.Incline = _incline;
        this.Calories_Burned = _calories_Burned;
        this.IsComplete = _IsComplete;
    }

    public double getCalories_Burned() {
        return Calories_Burned;
    }

    public void setCalories_Burned(double calories_Burned) {
        Calories_Burned = calories_Burned;
    }

    public double getTime() {
        return Time;
    }

    public void setTime(double time) {
        Time = time;
    }

    public boolean isComplete() {
        return IsComplete;
    }

    public void setComplete(boolean complete) {
        IsComplete = complete;
    }

    public double getIncline() {
        return Incline;
    }

    public void setIncline(double incline) {
        Incline = incline;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }
}
