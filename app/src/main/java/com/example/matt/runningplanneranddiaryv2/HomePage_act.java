package com.example.matt.runningplanneranddiaryv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;


public class HomePage_act extends AppCompatActivity {

    public String errorMsg = "";
    public String FileName = "test10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

        //on application load
        //call check file exists function
        checkFileExist();
    }


    public void toJustRunPage(View view)
    {
        Intent myIntent = new Intent(this, Just_Run_Act.class);
        startActivity(myIntent);
    }


    public void toPastRunPage(View view) {
        Intent myIntent = new Intent(this, Past_Runs.class);
        startActivity(myIntent);
    }

    public void toCreateRoutinePage(View view) {
        Intent myIntent = new Intent(this, running_planner_act.class);
        startActivity(myIntent);
    }






    //function that will determine if file already exists, if not it will create a new file with the default file name
    public void checkFileExist()
    {


        File file = new File(FileName);

        if (!file.exists())
        {
            file.delete();
            file.mkdir();

            Toast.makeText(getApplicationContext(), "made new file",
                    Toast.LENGTH_LONG).show();
        }


    }



}
