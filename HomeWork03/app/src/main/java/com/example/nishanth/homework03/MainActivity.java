 package com.example.nishanth.homework03;

 /*
 Sai Nishanth Dilly
 Shireen Shaik
 Group 04

  */


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    LinearLayout parent;

    Button search;
    ArrayList<String> ls;
    HashMap<String,Long> map;
    CheckBox cb,cw;
     int count;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
    parent = (LinearLayout) findViewById(R.id.LinerParent);
    LinearLayout h = new LinearLayout(this);
    h.setOrientation(LinearLayout.HORIZONTAL);
    EditText ev = new EditText(this);
        count =0;
        ev.setWidth(560);
        ls = new ArrayList<String>();
    Button b = new Button(this);

        b.setBackgroundResource(R.drawable.round);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(150,150);
       b.setLayoutParams(lp);
        b.setText("+");
        b.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        h.addView(ev);
        h.addView(b);
        parent.addView(h);
    map = new HashMap<String, Long>();


    cb = (CheckBox)findViewById(R.id.checkBoxMatch);
     cw = (CheckBox)findViewById(R.id.checkBoxMatchWord);
    b.setOnClickListener(this);
        search = (Button)findViewById(R.id.buttonSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = parent.getChildCount();
                ls.clear();
                count=0;
                map.clear();
                boolean flag=true;
                for(int i=0;i<c;i++)
                {
                    LinearLayout l = (LinearLayout) parent.getChildAt(i);
                    EditText e = (EditText)l.getChildAt(0);
                    if(e.getText().toString().trim().length()==0)
                    {
                        Toast.makeText(getApplication(),"Please Enter Words to search do not leave blank ",Toast.LENGTH_SHORT).show();
                        flag = false;
                        break;
                    }
                    ls.add(e.getText().toString());


                }
                if(ls.size() > 20)
                {
                    Toast.makeText(getApplication(),"Please Enter less or equal to 20 words ",Toast.LENGTH_SHORT).show();
                    flag = false;

                }

                if(flag) {
                    for(int i=0;i<ls.size();i++) {
                        new PrAsync().execute(ls.get(i));
                    }
                }


            }
        });






}

    @Override
    public void onClick(View v) {

        Button v1 = (Button)v;
        if(v1.getText().toString().equals("+")) {
            v1.setText("-");
            parent = (LinearLayout) findViewById(R.id.LinerParent);
            LinearLayout h = new LinearLayout(this);
            h.setOrientation(LinearLayout.HORIZONTAL);
            EditText ev = new EditText(this);
            Button b = new Button(this);
            b.setBackgroundResource(R.drawable.round);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(150,150);
            b.setLayoutParams(lp);
            b.setText("+");
            b.setTextColor(getResources().getColor(R.color.colorPrimaryDark));


            ev.setWidth(560);
            h.addView(ev);
            h.addView(b);
            parent.addView(h);
            b.setOnClickListener(this);
        }
        else{
            LinearLayout l = (LinearLayout)v1.getParent();
            l.removeView(l.getChildAt(0));
            l.removeView(l.getChildAt(0));
            parent.removeView(l);

        }

    }


    class PrAsync extends AsyncTask<String,Integer,Integer>{
        ProgressDialog pg ;
        InputStream fileIn;
        BufferedReader bufRd;
        boolean match_Case,match_word;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(MainActivity.this);
            pg.setCancelable(false);
            match_Case = cb.isChecked();
            match_word = cw.isChecked();
            for(int i=0;i<ls.size();i++){
                String t = ls.get(i);
                map.put(t,new Long(0));
            }
                //pg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
           // pg.setMax(ls.size());
            pg.show();
            try {

                fileIn = getAssets().open("textfile.txt");
                bufRd = new BufferedReader(new InputStreamReader(fileIn));
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        public PrAsync() {

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            try {
                bufRd.close();
                fileIn.close();
            }catch (Exception e){

            }
            pg.dismiss();


            if(count == (ls.size())) {
                Intent i = new Intent(MainActivity.this, Results.class);
                i.putExtra("Res", map);
                startActivity(i);
            }


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            count++;
            //pg.setProgressStyle(values[0]);

        };

        @Override
        protected Integer doInBackground(String... params) {
            String s;

            try {
                String temp = params[0];
                String temp1 = temp;

                if(!match_Case)
                {
                    temp = temp.toUpperCase();
                }


                while((s=bufRd.readLine())!=null){
                    if(!match_Case) {
                        s = s.toUpperCase();
                    }
                    StringBuilder br = new StringBuilder(s);
                                int x = br.indexOf(temp);
                                long k=0;
                                 while(x!=-1){
                                    if(match_word)
                                    {   boolean notword = false;

                                        int charb;
                                        int charE;
                                        if(x ==0)
                                        {
                                            charb = 40;

                                        }
                                        else{
                                            charb = br.charAt(x-1);
                                        }
                                        if(x+ temp.length() == br.length() ){
                                            charE = 40;

                                        }
                                        else{
                                            charE = br.charAt(x+temp.length());
                                        }

                                        if((charE>=97 && charE <=122) || (charE >=65 && charE <=90) || (charE >= 48 && charE <= 57)  || (charE == 95)  )
                                        {

                                            notword = true;
                                        }
                                        else if ((charb >= 97 && charb <= 122) || (charb >= 65 && charb <= 90) || (charb >= 48 && charb <= 57) || (charb == 95)   ) {
                                            notword = true;
                                        }


                                        if(!notword)
                                            k++;



                                    }
                                    else {
                                        k++;
                                    }

                                    x=br.indexOf(temp,x+1);

                                }
                                long k1 = map.get(temp1);
                                map.put(temp1,k+k1);



                }

                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(count);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }
}
