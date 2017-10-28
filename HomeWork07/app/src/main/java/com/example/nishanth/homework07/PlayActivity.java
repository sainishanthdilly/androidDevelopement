package com.example.nishanth.homework07;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.ParseException;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayActivity extends AppCompatActivity implements ImageButton.OnClickListener,SeekBar.OnTouchListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnBufferingUpdateListener{

    ImageView im;
    TextView t1,t2,t3,t4;
    SeekBar seekBarProgress;
    ImageButton ib;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds; // this value contains the song duration in milliseconds. Look at getDuration() method in MediaPlayer class
    String playing ="PLAY";
    String Stoping  = "STOP";
    private final Handler handler = new Handler();
    String song;
    private ProgressDialog progressDialog;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
       Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
        flag = true;

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("Play ");
        song  = (String) getIntent().getExtras().get("Audio");
        t1 = (TextView) findViewById(R.id.textViewPlayTitle);
        t2 = (TextView) findViewById(R.id.textViewPlayDescrip);
        im = (ImageView) findViewById(R.id.imageViewPlayAct);
        t3 = (TextView) findViewById(R.id.textViewPlayPubDate);
        t4 = (TextView) findViewById(R.id.textViewPlayDur);
        ib = (ImageButton) findViewById(R.id.imageButtonFinalAct);
        seekBarProgress = (SeekBar) findViewById(R.id.seekBarPlayPauseFinal);
        t1.setText((String) getIntent().getExtras().get("Title"));
        t2.setText("Description: "+ (String) getIntent().getExtras().get("Description"));
        String d = (String) getIntent().getExtras().get("Date");
        d = d.substring(d.indexOf(":")+2,d.length());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd, MMM yyyy");
        try {
            Date dt = sdf.parse(d);
            SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
            t3.setText("Publication Date: "+ s.format(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }


        t4.setText("Duration: "+(String) getIntent().getExtras().get("Duration"));
        Picasso.with(this).load((String) getIntent().getExtras().get("Image")).into(im);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();


        initView();


    }
    private void initView(){
        seekBarProgress.setMax(99);
        seekBarProgress.setOnTouchListener(this);
        
        ib.setImageResource(android.R.drawable.ic_media_pause);
        ib.setTag(Stoping);
        ib.setOnClickListener(PlayActivity.this);
        progressDialog.dismiss();




    }

    /** Method which updates the SeekBar primary progress by current song playing position*/
    private void primarySeekBarProgressUpdater() {

        if(seekBarProgress!=null && mediaPlayer!=null) {

            seekBarProgress.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaFileLengthInMilliseconds) * 100)); // This math construction give a percentage of "was playing"/"song length"
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
    public void onClick(View v) {
        if(flag){
            Toast.makeText(getApplicationContext(),"Playing Please wait",Toast.LENGTH_SHORT).show();

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);

            try {
                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
            } catch (Exception e) {
                e.printStackTrace();
            }

            mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

            mediaPlayer.start();
            flag = false;

        }
        else {


            if (((ImageButton) v).getTag().equals(playing)) {

                mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

                mediaPlayer.start();
                ((ImageButton) v).setImageResource(android.R.drawable.ic_media_pause);
                ((ImageButton) v).setTag(Stoping);
            } else {
                ((ImageButton) v).setImageResource(android.R.drawable.ic_media_play);
                ((ImageButton) v).setTag(playing);


                mediaPlayer.pause();

            }
            primarySeekBarProgressUpdater();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId() == R.id.seekBarPlayPauseFinal){
            /** Seekbar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position*/
            if(mediaPlayer.isPlaying()){
                SeekBar sb = (SeekBar)v;
                int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                mediaPlayer.seekTo(playPositionInMillisecconds);
            }
        }
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBarProgress.setSecondaryProgress(percent);

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        ib.setImageResource(android.R.drawable.ic_media_play);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer!=null){
        mediaPlayer.release();
        mediaPlayer = null;}
    }




}
