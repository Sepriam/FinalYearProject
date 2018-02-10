package com.example.matt.runningplanneranddiaryv2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Just_Run_Act extends AppCompatActivity {

    Switch jr_switch;

    Just_Run_Fragment_1 frag1;
    Just_Run_Fragment_2 frag2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.just_run_activity);

        jr_switch = (Switch)findViewById(R.id.jr_switch1);

        if (savedInstanceState == null)
        {
            FragmentManager FM = getFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();

            Just_Run_Fragment_1 F1 = new Just_Run_Fragment_1();


            FT.replace(R.id.jr_lin_layout, F1);
            FT.commit();
        }

        jr_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b)
                {
                    FragmentManager FM = getFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();

                    Just_Run_Fragment_1 F1 = new Just_Run_Fragment_1();


                    FT.replace(R.id.jr_lin_layout, F1);
                    FT.commit();
                }
                else
                {

                    FragmentManager FM = getFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();

                    Just_Run_Fragment_2 F1 = new Just_Run_Fragment_2();

                    FT.replace(R.id.jr_lin_layout, F1);
                    FT.commit();
                }
            }
        });

    }



}
