package com.example.nishanth.myfavoritemovies;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


//Sai Nishanth Dilly
// Shaik rajia Shareen
//Group04 HW02

public class MoviebyYear extends Activity {
    Button bf,bp,bn,bl,finish;
    TextView title,gen,rating,yr,imdb;
    EditText Desc;
    int i=0;
    ArrayList<FavoriteMovie> ly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieby_year);
        try {
            if (getIntent() != null) {
                ly = getIntent().getParcelableArrayListExtra(MainActivity.sortY);

                title = (TextView) findViewById(R.id.y_textViewRTitle);
                Desc = (EditText) findViewById(R.id.y_editTextRResult);
                gen = (TextView) findViewById(R.id.y_textViewRGenre);
                rating = (TextView) findViewById(R.id.y_textViewRRating);
                yr = (TextView) findViewById(R.id.y_textViewRYear);
                imdb = (TextView) findViewById(R.id.y_textViewRMDB);

                bp = (Button) findViewById(R.id.y_buttonPrevious);
                bn = (Button) findViewById(R.id.y_buttonNext);
                bl = (Button) findViewById(R.id.y_buttonLast);

                bf = (Button) findViewById(R.id.y_buttonFirst);
                finish = (Button) findViewById(R.id.y_buttonFinish);

                FavoriteMovie mv = ly.get(0);
                i = 0;
                title.setText(mv.movieName);
                Desc.setText(mv.descr);
                gen.setText(mv.genre);
                rating.setText(mv.rating + "/5");
                yr.setText(mv.year);
                imdb.setText(mv.IMDB);

                bf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(getApplicationContext(),"Clickedf",Toast.LENGTH_SHORT).show();
                        FavoriteMovie mv = ly.get(0);
                        i = 0;
                        title.setText(mv.movieName);
                        Desc.setText(mv.descr);
                        gen.setText(mv.genre);
                        rating.setText(mv.rating + "/5");
                        yr.setText(mv.year);
                        imdb.setText(mv.IMDB);

                    }
                });

                bp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  Log.d("click","Prev");
                       // Toast.makeText(getApplicationContext(),"Clickedp",Toast.LENGTH_SHORT).show();

                        if (i != 0) {
                            FavoriteMovie mv = ly.get(--i);

                            title.setText(mv.movieName);
                            Desc.setText(mv.descr);
                            gen.setText(mv.genre);
                            rating.setText(mv.rating + "/5");
                            yr.setText(mv.year);
                            imdb.setText(mv.IMDB);
                        }


                    }
                });

                bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  Toast.makeText(getApplicationContext(),"Clickedn",Toast.LENGTH_SHORT).show();

                        if (i < (ly.size()-1)) {
                            i++;
                            FavoriteMovie mv = ly.get(i);

                            title.setText(mv.movieName);
                            Desc.setText(mv.descr);
                            gen.setText(mv.genre);
                            rating.setText(mv.rating + "/5");
                            yr.setText(mv.year);
                            imdb.setText(mv.IMDB);
                        }


                    }
                });

                bl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(getApplicationContext(),"Clickedl",Toast.LENGTH_SHORT).show();

                        if (i < (ly.size()-1) ){
                        i = ly.size() - 1;
                        FavoriteMovie mv = ly.get(i);

                        title.setText(mv.movieName);
                        Desc.setText(mv.descr);
                        gen.setText(mv.genre);
                        rating.setText(mv.rating + "/5");
                        yr.setText(mv.year);
                        imdb.setText(mv.IMDB);

                    }
                    }


                });


                finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
