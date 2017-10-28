package com.example.nishanth.thegamedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GameDetailsActivity extends AppCompatActivity implements GetGameAsync.SetUpGame {
    TextView  title,genre,pub;
    int id;
    ImageView im;
    LinearLayout ln ;
    ProgressDialog progress;
    Button play, similar, fin;
    Game game;


    String gameURL= "http://thegamesdb.net/api/GetGame.php";
    String videoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        getSupportActionBar().setTitle("Game Details");
        title = (TextView) findViewById(R.id.textViewGameName);
        genre = (TextView) findViewById(R.id.textViewGenre);
        pub = (TextView) findViewById(R.id.textViewPublisher);
        im = (ImageView) findViewById(R.id.imageView);
        ln = (LinearLayout) findViewById(R.id.linearLayout);
        play = (Button)findViewById(R.id.buttonPlay);
        fin = (Button) findViewById(R.id.buttonFinish);
        similar = (Button) findViewById(R.id.buttonsimlarGames);
        im.setImageResource(android.R.color.transparent);

        id = getIntent().getExtras().getInt(MainActivity.ID);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading ");
        progress.setCancelable(false);
        progress.show();
        RequestParams r = new RequestParams(gameURL,"GET");
        r.putParams("id",Integer.toString(id));
        new GetGameAsync(this).execute(r);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameDetailsActivity.this,WebViewDemo.class);
                i.putExtra("videoURL",videoURL);
                startActivity(i);


            }
        });

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        similar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(GameDetailsActivity.this,SimilarGames.class);
                i.putExtra("similargames",game.getSimilar_id());
                i.putExtra("Title",game.getGame_name());
                startActivity(i);

            }
        });





    }

    @Override
    public void setGame(Game g) {
        game=g;
        progress.dismiss();
        if(g.getImg()!=null) {
            im.setImageBitmap(g.getImg());
        }
        else{
            im.setImageResource(android.R.color.transparent);
        }
        title.setText(g.getGame_name());
        genre.setText(genre.getText()+g.getGenre());
        pub.setText(pub.getText()+g.getPublisher());
        TextView tv = new TextView(this);
        tv.setText(g.getOverView());
        ln.addView(tv);
        videoURL = g.getYoutubeVideo();



    }
}
