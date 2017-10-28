package com.example.nishanth.thegamedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameSimilarDetailsDemo extends AppCompatActivity implements GetDemoSimilarGameDetailsAsync.SetUpGame{
    TextView titleDemo,genreDemo,pubDemo;
    int id;
    ImageView imDemo;
    LinearLayout lnDemo ;
    ProgressDialog progress;
    Button  finDemo;
    Game game;
    String gameURL= "http://thegamesdb.net/api/GetGame.php";
    String videoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_similar_details_demo);
            getSupportActionBar().setTitle("Game Similar Details Demo");
            titleDemo = (TextView) findViewById(R.id.textViewtitleDemo);
            genreDemo = (TextView) findViewById(R.id.textViewGenreDemo);
            pubDemo = (TextView) findViewById(R.id.textViewPubDemo);
            imDemo = (ImageView) findViewById(R.id.imageView4);
            lnDemo = (LinearLayout) findViewById(R.id.linearSimilarDemo);
            // play = (Button)findViewById(R.id.buttonPlay);
            finDemo = (Button) findViewById(R.id.buttonfinishdemo);
            imDemo.setImageResource(android.R.color.transparent);

            id = getIntent().getExtras().getInt(MainActivity.ID);
            progress = new ProgressDialog(this);
            progress.setMessage("Loading ");
            progress.setCancelable(false);
            progress.show();
            RequestParams r = new RequestParams(gameURL,"GET");
            r.putParams("id",Integer.toString(id));
            new GetDemoSimilarGameDetailsAsync(this).execute(r);

            finDemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();
                }
            });








        }

        @Override
        public void setGame(Game g) {
            game=g;
            progress.dismiss();
            if(g.getImg()!=null) {
                imDemo.setImageBitmap(g.getImg());
            }
            else{
                imDemo .setImageResource(android.R.color.transparent);
            }
            titleDemo .setText(g.getGame_name());
            genreDemo.setText("Genre: "+g.getGenre());
            pubDemo.setText("Publisher: "+g.getPublisher());
            TextView tv = new TextView(this);
            tv.setText(g.getOverView());
            lnDemo.addView(tv);




        }






}
