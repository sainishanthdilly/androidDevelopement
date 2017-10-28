/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */

package com.example.nishanth.triviaquiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TriviaActivity extends AppCompatActivity implements  GetImageAsync.SetImage,View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    TextView q1, q2, time1;

    Button prev, next;
    ArrayList<Questions> ques;
    LinearLayout im,fin;

    ArrayList<String> ans;



    RadioGroup rg;
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        q1= (TextView) findViewById(R.id.textViewQ);
        q1.setText("Q1");
        im = (LinearLayout) findViewById(R.id.linearImg);

        time1 = (TextView) findViewById(R.id.textViewTime);


        q2 = (TextView) findViewById(R.id.textView3);

        ques = (ArrayList<Questions>) getIntent().getExtras().get("Questions");
        ans = new ArrayList<String>(ques.size());

        for(int i=0;i<ques.size();i++){
            ans.add("");
        }



       // Toast.makeText(getApplicationContext(),ques.size()+" ",Toast.LENGTH_SHORT).show();


        rg = (RadioGroup) findViewById(R.id.options);
        rg.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

        rg.removeAllViews();

        prev = (Button) findViewById(R.id.buttonPrev);
        next = (Button) findViewById(R.id.buttonNext);
        pos=0;
        prev.setEnabled(false);
        Questions q = ques.get(pos);
        q2.setText(q.getText());

        String[] ch = q.getChoices();
        ProgressBar pb = new ProgressBar(this);
        im.addView(pb);
        im.setGravity(Gravity.CENTER);
        RequestParam rp = new RequestParam(q.getImage(),"GET");

        if(q.getImage().trim().length()!=0)
            new GetImageAsync(this,pos).execute(rp);
        else
            im.removeAllViews();

        for(int i=0;i<ch.length; i++){
            RadioButton rb = new RadioButton(this);
            rb.setText(ch[i]);
            rg.addView(rb);

        }

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                time1.setText("Time Remaining: " + millisUntilFinished / 1000 +" Seconds");
            }

            public void onFinish() {

                /*for(int i=0;i<ans.size();i++){
                    if(ans.get(i).trim().length() ==0)
                        ans.set(i,Integer.toString(100));
                }*/
                Intent i = new Intent(TriviaActivity.this,TriviaStats.class);
                i.putExtra("Answers",ans);
                i.putExtra("CorrectAnswers",ques);
                startActivity(i);
                finish();


            }
        }.start();



        next.setOnClickListener(this);


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pos == 0)
                    prev.setEnabled(false);

                if(pos == ques.size()-1)
                {

                    next.setText("Next");

                }


                if (pos > 0) {
                    pos--;
                    im = (LinearLayout) findViewById(R.id.linearImg);

                    Questions q = ques.get(pos);
                    q1.setText("Q"+(pos+1));
                    im.removeAllViews();
                    ProgressBar p = new ProgressBar(TriviaActivity.this);
                    im.addView(p);
                    im.setGravity(Gravity.CENTER);

                    if(q.getImage().trim().length()!=0) {
                        RequestParam rp = new RequestParam(q.getImage(), "GET");
                        new GetImageAsync(TriviaActivity.this,pos).execute(rp);
                    }
                    else
                    im.removeAllViews();
                    q2.setText(q.getText());

                    rg.removeAllViews();

                    String[] ch = q.getChoices();
                    for (int i = 0; i < ch.length; i++) {
                        RadioButton rb = new RadioButton(TriviaActivity.this);
                        rb.setText(ch[i]);
                        rg.addView(rb);

                    }


                    if(ans.get(pos).trim().length()!=0) {
                        RadioButton rb = (RadioButton) rg.getChildAt(Integer.parseInt(ans.get(pos))-1);
                        rb.setChecked(true);
                    }


                }
            }
        });

    }


    @Override
    public void setimage(Bitmap bm, int po) {
        im = (LinearLayout) findViewById(R.id.linearImg);
        if(ques.get(pos).getImage().trim().length()==0)
            im.removeAllViews();

        if(po == pos) {
            im.removeAllViews();
            ImageView iv = new ImageView(this);
            iv.setImageBitmap(bm);
            im.addView(iv);
            im.setGravity(Gravity.CENTER);
        }


    }

    @Override
    public void onClick(View v) {

        Button b1 = (Button)v;
        if(b1.getText().toString().equalsIgnoreCase("Next")){





                prev.setEnabled(true);
                if (pos < (ques.size() - 1)) {

                    im = (LinearLayout) findViewById(R.id.linearImg);
                    im.removeAllViews();
                    ProgressBar p = new ProgressBar(TriviaActivity.this);
                    im.addView(p);
                    im.setGravity(Gravity.CENTER);


                    pos++;
                    q1.setText("Q" + (pos + 1));

                    Questions q = ques.get(pos);

                    RequestParam rp = new RequestParam(q.getImage(), "GET");
                    if (q.getImage().trim().length() != 0)
                        new GetImageAsync(TriviaActivity.this, pos).execute(rp);
                    else
                    im.removeAllViews();

                    q2.setText(q.getText());

                    rg.removeAllViews();

                    String[] ch = q.getChoices();
                    for (int i = 0; i < ch.length; i++) {
                        RadioButton rb = new RadioButton(TriviaActivity.this);
                        rb.setText(ch[i]);
                        rg.addView(rb);


                    }

                    if (ans.get(pos).trim().length() != 0) {
                        RadioButton rb = (RadioButton) rg.getChildAt(Integer.parseInt(ans.get(pos)) - 1);
                        rb.setChecked(true);
                    }
                    if(pos == ques.size()-1)
                        next.setText("Finish");




                }


            }



        else {


            Intent i = new Intent(TriviaActivity.this, TriviaStats.class);
            i.putExtra("Answers", ans);
            i.putExtra("CorrectAnswers", ques);
            startActivity(i);
            finish();
        }

        }





    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int count = group.getChildCount();
        try {
            for (int i = 0; i < count; i++) {
                RadioButton rb = (RadioButton) group.getChildAt(i);
                if (rb.isChecked())
                    ans.set(pos, Integer.toString(i + 1));
            }
            /*boolean flag = true;
            for (int i = 0; i < ans.size(); i++) {
                if (ans.get(i).trim().length() == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag && pos == (ans.size()-1)) {
                fin = (LinearLayout) findViewById(R.id.linearquestionTrivia);
                fin.removeAllViews();
                Button b = new Button(TriviaActivity.this);
                b.setText("Finish");
                fin.addView(b);
                b.setOnClickListener(TriviaActivity.this);


            }*/

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
