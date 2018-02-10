package com.example.matt.runningplanneranddiaryv2;

/**
 * Created by Matt on 11/03/2017.
 */

public class calorie_Counter {

    //weight in kg
    //need to create on first start method that will record all of the user's initial details e.g height and weight
    private int weight = 75;

    //find metabolic equivalent of task
    private double metabolic_Number = 0;

    //speed in MPH
    private double speedVar = 0;

    private double caloriesBurnedTotal = 0;

    static private double met_Multiplier_Number = 3.5;

    private String format = "";
    private double formatted_Speed = 0;

    public double calcCalories(double time_in, double distance_in)
    {
        /*
        time converted from mins to hours  =     time / 60
        distance converted from km to miles =      distance * 0.621371
         */
        speedVar = (distance_in * 0.621371) / (time_in / 60);

        //note-  need to check what happens if i pass a number to this that is between the checks
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------HERE
        //need to convert this number to 1dp!
        format = String.format("%.1f", speedVar);

        formatted_Speed = Double.parseDouble(format);

        metabolic_Number = metabolicEquivalent(speedVar);

        caloriesBurnedTotal = ((metabolic_Number * met_Multiplier_Number * weight) / 200) * time_in;

        return caloriesBurnedTotal;
    }

    /*
    _____MET Value  x  3.5  x  _____kg body weight  ÷  200 = calories burned per minute.

    calories burned per minute * time_in = total calories burned for exercise
     */


    public double metabolicEquivalent(double _speed)
    {

        if (isBetween(_speed, 0.0, 1.9))
        {
            return 2.0;
        }
        else if (isBetween(_speed, 2.0, 2.4))
        {
            return 2.8;
        }
        else if (isBetween(_speed, 2.5, 2.8))
        {
            return 3.0;
        }
        else if (isBetween(_speed, 2.9, 3.2))
        {
            return 3.5;
        }
        else if (isBetween(_speed, 3.3, 3.5))
        {
            return 4.3;
        }
        else if (isBetween(_speed, 3.6, 3.9))
        {
            return 5.0;
        }
        else if (isBetween(_speed, 4.0, 4.9))
        {
            return 6.0;
        }
        else if (isBetween(_speed,5.0,5.2))
        {
            return 8.3;
        }
        else if (isBetween(_speed, 5.3, 5.9))
        {
            return 9.0;
        }
        else if (isBetween(_speed, 6.0, 6.6))
        {
            return 9.8;
        }
        else if (isBetween(_speed, 6.7, 6.9))
        {
            return 10.5;
        }
        else if (isBetween(_speed, 7.0, 7.4))
        {
            return 11.0;
        }
        else if (isBetween(_speed, 7.5, 7.9))
        {
            return 11.5;
        }
        else if (isBetween(_speed,8.0, 8.5))
        {
            return 12.8;
        }
        else if (isBetween(_speed, 8.6, 8.9))
        {
            return 12.3;
        }
        else if (isBetween(_speed, 9.0, 9.9))
        {
            return 12.8;
        }
        else if (isBetween(_speed, 10.0, 10.9))
        {
            return 14.5;
        }
        else if (isBetween(_speed, 11.0, 11.9))
        {
            return 16.0;
        }
        else if (isBetween(_speed, 12, 12.9))
        {
            return 19.0;
        }
        else if (isBetween(_speed, 13.0, 13.9))
        {
            return 19.8;
        }
        else if (_speed >= 14.0)
        {
            return 23.0;
        }

        //if it gets here, then something's gone wrong
        //need to write a log
        else
        {
            return 0.0;
        }


    }

    //bool to return true if number is between certain values
    public static boolean isBetween(double x, double lower, double upper)
    {
        return lower <= x && x <= upper;
    }

    /*

    Reference for metabolic equivalent of task
    Src : http://download.lww.com/wolterskluwer_vitalstream_com/PermaLink/MSS/A/MSS_43_8_2011_06_13_AINSWORTH_202093_SDC1.pdf
    Last visited : 11/03/2017

2.0 walking, less than 2.0 mph, level, strolling, very slow
2.8 walking, 2.0 mph, level, slow pace, firm surface
3.0 walking, 2.5 mph, level, firm surface
3.5 walking, 2.8 to 3.2 mph, level, moderate pace, firm surface
4.3 walking, 3.5 mph, level, brisk, firm surface, walking for exercise
6.0 Running, 4 mph (13 min/mile)
8.3 Running, 5 mph (12 min/mile)
9.0 Running, 5.2 mph (11.5 min/mile)
9.8 Running, 6 mph (10 min/mile)
10.5 Running, 6.7 mph (9 min/mile)
11.0 Running, 7 mph (8.5 min/mile)
11.5 Running, 7.5 mph (8 min/mile)
11.8 Running, 8 mph (7.5 min/mile)
12.3 Running, 8.6 mph (7 min/mile)
12.8 Running, 9 mph (6.5 min/mile)
14.5 Running, 10 mph (6 min/mile)
16.0 Running, 11 mph (5.5 min/mile)
19.0 Running, 12 mph (5 min/mile)
19.8 Running, 13 mph (4.6 min/mile)
23.0 Running, 14 mph (4.3 min/mile)
     */

}
