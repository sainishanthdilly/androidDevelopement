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

public class AddMovieActivity extends AppCompatActivity {

    Button b;
    EditText mn,des,eYear,eIMDB;
    Spinner genre;
    SeekBar sb;
    TextView rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        b = (Button) findViewById(R.id.e_buttonSubmitAddMovie);
        mn = (EditText) findViewById(R.id.e_editTextMovieName);


        des = (EditText) findViewById(R.id.e_editTextDescription);


        genre = (Spinner)findViewById(R.id.e_spinnerGenre);
       // genre.getSelectedItem().toString();


         sb = (SeekBar) findViewById(R.id.e_seekBarRating);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                rate.setText(progress+" ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


         eYear = (EditText)findViewById(R.id.e_editTextYear);

        eIMDB = (EditText)findViewById(R.id.editTextIMDB);
        rate=(TextView) findViewById(R.id.rate);


        b.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick (View v){

                    try

                    {
                String movieName = mn.getText().toString();
                String descrip = des.getText().toString();
                String arr[] = getResources().getStringArray(R.array.genre_array);
                String val = arr[(int) genre.getSelectedItemId()];
                int pg = sb.getProgress();
                String yr = eYear.getText().toString();
                String imdb = eIMDB.getText().toString();




                        if(movieName.length()==0||descrip.length()==0||yr.length()==0||imdb.length()==0)
                            throw  new Exception();

                         if(movieName.length()<0 ||movieName.length()>50)
                        {
                            Toast.makeText(getApplicationContext(),"Enter name upto 50 characters only",Toast.LENGTH_LONG).show();
                        }
                       else if(descrip.length()<0 ||descrip.length()>50)
                        {
                            Toast.makeText(getApplicationContext(),"Enter Description upto 1000 character only",Toast.LENGTH_LONG).show();
                        }
                       else if(yr.length()!=4)
                        {
                            Toast.makeText(getApplicationContext(),"Enter valid year(4 numbers)",Toast.LENGTH_LONG).show();
                        }
                        else {


                             FavoriteMovie fb = new FavoriteMovie(movieName, descrip, val, pg, yr, imdb);

                             Intent i = new Intent();
                             i.putExtra(MainActivity.movie_data, fb);
                             setResult(RESULT_OK, i);
                             finish();
                         }

            }
                    catch (Exception e)
                    {

                        Toast.makeText(getApplicationContext(),"Enter all deails",Toast.LENGTH_LONG).show();
                    }
            }

        });

    }
}
