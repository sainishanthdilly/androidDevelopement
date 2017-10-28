package com.example.nishanth.myfavoritemovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


//Sai Nishanth Dilly
// Shaik rajia Shareen
//Group04 HW02

public class EditMovie extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Spinner sp;
    SeekBar sb;
    FavoriteMovie f;
    String arr[];
    TextView rate1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        if(getIntent().getExtras()!=null){
            f= getIntent().getParcelableExtra(MainActivity.movie_data);
        }

        e1 = (EditText) findViewById(R.id.e_editTextMovieName);

        e1.setText(f.movieName);

        e2 = (EditText) findViewById(R.id.e_editTextDescription);
        e2.setText(f.descr);

        sp = (Spinner)findViewById(R.id.e_spinnerGenre);
        arr = getResources().getStringArray(R.array.genre_array);
        int i=0;
        while(!arr[i].equalsIgnoreCase(f.genre)){
            i++;
        }
        sp.setSelection(i);

        sb = (SeekBar)findViewById(R.id.e_seekBarRating);
        sb.setProgress(f.rating);


        rate1=(TextView) findViewById(R.id.rate1);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                rate1.setText(progress+" ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        e3 = (EditText)findViewById(R.id.e_editTextYear);
        e3.setText(f.year);

        e4 = (EditText)findViewById(R.id.e_editTextIMDB);
        e4.setText(f.IMDB);


        Button b = (Button)findViewById(R.id.e_buttonSubmitAddMovie);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try

                {

                    f.movieName = e1.getText().toString();


                    f.descr = e2.getText().toString();
                    f.genre = sp.getSelectedItem().toString();
                    f.rating = sb.getProgress();
                    f.year = e3.getText().toString();
                    f.IMDB = e4.getText().toString();


                    if(f.movieName.length()==0||f.descr.length()==0||f.year.length()==0||f.IMDB.length()==0)
                        throw  new Exception();

                    if(f.movieName.length()<0 ||f.movieName.length()>50)
                    {
                        Toast.makeText(getApplicationContext(),"Enter name upto 50 characters only",Toast.LENGTH_LONG).show();
                    }
                    else if(f.descr.length()<0 ||f.descr.length()>50)
                    {
                        Toast.makeText(getApplicationContext(),"Enter Description upto 1000 character only",Toast.LENGTH_LONG).show();
                    }
                    else if(f.year.length()!=4)
                    {
                        Toast.makeText(getApplicationContext(),"Enter valid year(4 numbers)",Toast.LENGTH_LONG).show();
                    }
                    else {

                    Intent i = new Intent();
                    i.putExtra(MainActivity.movie_data, f);
                    setResult(RESULT_OK, i);
                    finish();
                }}
                catch (Exception e)
                {

                    Toast.makeText(getApplicationContext(),"Enter all deails",Toast.LENGTH_LONG).show();
                }


            }
        });










    }
}
