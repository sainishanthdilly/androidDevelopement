/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */


package com.example.nishanth.triviaquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TriviaStats extends AppCompatActivity {

    Button fin;
    ProgressBar pb;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_stats);

        ArrayList<String> a = (ArrayList<String>) getIntent().getExtras().get("Answers");
        ArrayList<Questions> qu = (ArrayList<Questions>) getIntent().getExtras().get("CorrectAnswers");

        LinearLayout l= (LinearLayout) findViewById(R.id.LinearStatus);

        pb = (ProgressBar) findViewById(R.id.progressBarfinal);
        res = (TextView) findViewById(R.id.textViewPercent);

        pb.setMax(100);


        fin = (Button) findViewById(R.id.buttonFinishFinal);

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Intent i = new Intent(TriviaStats.this,MainActivity.class);
               // startActivity(i);
                finish();
            }
        });




      //  Toast.makeText(getApplication(),a.toString(),Toast.LENGTH_LONG).show();

        int wrong_count=0;
        try{
        for(int i=0;i<qu.size();i++) {
            Questions q = qu.get(i);
            int x;
            if(a.get(i).trim().length() ==0)
            x = 100;
            else
            x= Integer.parseInt(a.get(i));

            if ( x != (int)(Integer.parseInt(q.getAnswer().trim())) ) {
                TextView t = new TextView(this);
                t.setText("Q" + Integer.toString(i + 1) + " " + q.getText());
                TextView t1 = new TextView(this);
                if(x == 100)
                    t1.setText("Your answer : " + "");
                else
                t1.setText("Your answer : " + q.getChoices()[Integer.parseInt(a.get(i))-1]);
                t1.setBackgroundColor(Color.RED);
                TextView t2 = new TextView(this);

                t2.setText("Correct answer : " + q.getChoices()[Integer.parseInt(q.getAnswer().trim())-1]);

                wrong_count++;


                l.addView(t);
                l.addView(t1);
                l.addView(t2);


            }

        }



        }

        catch(Exception e)
            {
              e.printStackTrace();
            }



        res.setText(Integer.toString((((qu.size()-wrong_count)*100)/qu.size()))+"%");
        pb.setProgress( (((qu.size() - wrong_count)*100))/qu.size()   );



    }
}
