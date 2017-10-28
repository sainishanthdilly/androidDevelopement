package com.example.nishanth.frangmentsinclass;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AFragment.OnFrangmentTextChange {


    TextView tv;
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Demo" , "Main Activity OnStart");
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Demo" , "Main Activity OnDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Demo" , "Main Activity OnResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Demo" , "Main Activity OnCreate before inflate");

        setContentView(R.layout.activity_main);

        Log.d("Demo" , "Main Activity OnStart after inflate ");
         tv= (TextView) findViewById(R.id.textView);

        getFragmentManager().beginTransaction().add(R.id.linearcontainer,new AFragment(),"Fragmet1").commit();

        getFragmentManager().beginTransaction().add(R.id.linearcontainer,new AFragment(),"Fragmet2").commit();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                //AFragment f = (AFragment)getFragmentManager().findFragmentById(R.id.fragment);
                AFragment f = (AFragment)getFragmentManager().findFragmentByTag("Fragmet1");

                AFragment f1 = (AFragment)getFragmentManager().findFragmentByTag("Fragmet2");

                if(checkedId == R.id.radioButtonRed){
                    f.changeColor(Color.RED);
                    f1.changeColor(Color.RED);
                }
                else if(checkedId == R.id.radioButtonBlue){
                    f.changeColor(Color.BLUE);
                    f1.changeColor(Color.BLUE);

                }
                else if(checkedId == R.id.radioButtonGreen){
                    f.changeColor(Color.GREEN);
                    f1.changeColor(Color.GREEN);

                }




            }
        });








    }

    @Override
    public void onTextChange(String txt) {

        tv.setText(txt);
    }
}
