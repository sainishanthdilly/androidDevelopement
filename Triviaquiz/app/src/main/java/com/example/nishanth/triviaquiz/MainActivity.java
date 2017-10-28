/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */

package com.example.nishanth.triviaquiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetQuestionsAsync.SetQuestions {

    Button exit,start;
    LinearLayout lp;
    ArrayList<Questions> q;



    String urlForQuestion = "http://dev.theappsdr.com/apis/trivia_json/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestParam req = new RequestParam(urlForQuestion,"POST");
        exit = (Button) findViewById(R.id.buttonExit);
        start = (Button) findViewById(R.id.buttonStart);
        start.setEnabled(false);


        lp = (LinearLayout) findViewById(R.id.linearMain);

        TextView tx = new TextView(this);
        tx.setText("Loading Trivia");

        ProgressBar pb = new ProgressBar(this);
        lp.addView(tx);
        lp.addView(pb);




        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,TriviaActivity.class);
                i.putExtra("Questions",q);
                startActivity(i);
                finish();


            }
        });





        if(isConnectedOnline())
        {
            new GetQuestionsAsync(this).execute(req);
        }
        else {
            Toast.makeText(getApplicationContext(),"No Internet" ,Toast.LENGTH_SHORT).show();
        }





    }

    private boolean isConnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo in =cm.getActiveNetworkInfo();
        if(in!=null &&in.isConnected())
            return true;




        return false;
    }

    @Override
    public void setQuestions(ArrayList<Questions> questions) {

        start.setEnabled(true);
        lp.removeViewAt(0);
        lp.removeViewAt(0);
        TextView tx = new TextView(this);
        tx.setText("Trivia Ready");
        lp.addView(tx);
        ImageView im = new ImageView(this);
        String uri = "@drawable/trivia";

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        im.setImageDrawable(res);
        //im.setBackgroundResource(R.drawable.trivia);
        lp.addView(im);



        q = questions;





    }
}
