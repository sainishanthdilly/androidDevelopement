package com.example.nishanth.homework07;

/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

import static com.example.nishanth.homework07.R.id.recyclerPod;

public class MainActivity extends AppCompatActivity implements PodCastAsync.ActivityData,ButtonClickCallBack,SeekBar.OnTouchListener,MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnCompletionListener{

    ProgressDialog progressDialog;
    LinearLayout ll;
    RecyclerView recyclerView;
    ArrayList<RadioDataPojo> radioDataPojoArrayList;
    RadioAdapter ra;
    RadioAdapterGrid rg;
    MediaPlayer mediaPlayer ;
    ImageButton play;
    String playing ="PLAY";
    String Stoping  = "STOP";
    String URL = "https://www.npr.org/rss/podcast.php?id=510298";
    int pos;
    SeekBar seekBar;
    Handler handler = new Handler();
    private int mediaFileLengthInMilliseconds;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("TED Radio Hour Podcast");

        play = (ImageButton) findViewById(R.id.imageButton);
        play.setTag(playing);
        play.setImageResource(android.R.drawable.ic_media_play);
        seekBar = (SeekBar) findViewById(R.id.seekBarPlayPause);
        seekBar.setOnTouchListener(this);
        seekBar.setMax(99);
        seekBar.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);

        ll = (LinearLayout) findViewById(R.id.linearSeekPlay);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(recyclerPod);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ImageButton)v).getTag().equals(playing)){
                    mediaPlayer.start();
                    ((ImageButton)v).setTag(Stoping);
                    //Drawable d = getDrawable(android.R.drawable.ic_media_pause)
                    ((ImageButton)v).setImageResource(android.R.drawable.ic_media_pause);
                    //mediaFileLengthInMilliseconds = mediaPlayer.getDuration();
                    //primarySeekBarProgressUpdater();

                }
                else{

                    String url = radioDataPojoArrayList.get(pos).getAudioUrl(); // your URL here

                    mediaPlayer.pause();
                    mediaFileLengthInMilliseconds = mediaPlayer.getDuration();
                    //primarySeekBarProgressUpdater();

                    ((ImageButton)v).setTag(playing);
                    ((ImageButton)v).setImageResource(android.R.drawable.ic_media_play);

                }

            }
        });

        new PodCastAsync(this).execute(new RequestParams(URL,"GET"));
    }

    @Override
    public void setUpData(ArrayList<RadioDataPojo> radioDataPojoArrayList) {
        this.radioDataPojoArrayList = radioDataPojoArrayList;
        progressDialog.dismiss();
        ra = new RadioAdapter(this,radioDataPojoArrayList);
        ra.setButtonClickCallBack(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ra);
        rg = new RadioAdapterGrid(this,radioDataPojoArrayList);
        rg.setButtonClickCallBack(this);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(ra);


    }

    public void changeGrid(MenuItem menuItem){

        if(!(recyclerView.getLayoutManager() instanceof GridLayoutManager)){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            recyclerView.setAdapter(rg);
        }
        else{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(ra);
        }



    }

    @Override
    public void onPlayButtonClick(int p) {

        //ProgressDialog pdp = new ProgressDialog(this);
        //pdp.setTitle("Loading Song..");
        //pdp.show();
        //pdp.setCancelable(false);
        progressDialog.show();
        pos = p;
        if(mediaPlayer == null)
        {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);

        }

        if(handler ==null){
            handler = new Handler();
        }

        mediaPlayer.reset();

        String url = radioDataPojoArrayList.get(p).getAudioUrl(); // your URL here

        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaFileLengthInMilliseconds = mediaPlayer.getDuration();

        progressDialog.dismiss();
        mediaPlayer.start();

        seekBar.setVisibility(View.VISIBLE);
        play.setVisibility(View.VISIBLE);


        play.setImageResource(android.R.drawable.ic_media_pause);
        play.setTag(Stoping);

        primarySeekBarProgressUpdater();

        Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();


    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId() == R.id.seekBarPlayPause){
            if(mediaPlayer.isPlaying()){
                SeekBar sb = (SeekBar)v;
               // int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                 int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();

                mediaPlayer.seekTo(playPositionInMillisecconds);
            }
        }
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(percent);

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        play.setImageResource(android.R.drawable.ic_media_pause);
    }

    private void primarySeekBarProgressUpdater() {
        if(seekBar!=null && mediaPlayer!=null) {

           seekBar.setProgress((int) ((((float) mediaPlayer.getCurrentPosition() * 100) / mediaFileLengthInMilliseconds)));
           //  seekBar.setProgress(mediaPlayer.getCurrentPosition());
           //  Log.d("Demo Progress", mediaPlayer.getCurrentPosition()+"");
           //  Log.d(" Media File Length",mediaFileLengthInMilliseconds+" ");

           if (mediaPlayer.isPlaying()) {
               Runnable notification = new Runnable() {
                   public void run() {
                       primarySeekBarProgressUpdater();
                   }
               };
               handler.postDelayed(notification, 1000);
           }
       }
    }



    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer!=null){
        mediaPlayer.release();
        mediaPlayer = null;
        }
    }
}
