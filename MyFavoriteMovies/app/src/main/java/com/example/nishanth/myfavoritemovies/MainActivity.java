package com.example.nishanth.myfavoritemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SectionIndexer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private ArrayList<FavoriteMovie> ls = new ArrayList<FavoriteMovie>();
    static  int REQUESTCODE = 100;
    static  int REQUESTCODE2 = 200;
    static  int REQUESTCODE3 = 300;
    static  int REQUESTCODE4 = 400;

    static String  sortY ="SORTBYYEAR";
    static String  sortR ="SORTBYRATING";

    static  String movie_data = "DATA";
    AlertDialog.Builder ad,ad1;
    CharSequence[] ch ;
    int whc;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MainActivity.REQUESTCODE)
        {
            if(resultCode == RESULT_OK){
                ls.add((FavoriteMovie) data.getParcelableExtra(MainActivity.movie_data));
                // Toast.makeText(getApplicationContext(),ls.toString(),Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == MainActivity.REQUESTCODE2){
            ls.set(whc, (FavoriteMovie) data.getParcelableExtra(MainActivity.movie_data));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b = (Button) findViewById(R.id.buttonAddMovie);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddMovieActivity.class);
                startActivityForResult(i,REQUESTCODE);
            }
        });



        ad = new AlertDialog.Builder(this);
        ad1 = new AlertDialog.Builder(this);
        Button b1 = (Button)findViewById(R.id.buttonEdit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.setTitle("Pick a movie");

                CharSequence ch[] = new CharSequence[ls.size()];
                    int i=0;
                    for(FavoriteMovie f : ls){
                        ch[i++] = f.movieName;
                    }

                    ad.setItems(ch, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(MainActivity.this,EditMovie.class);
                            whc=which;
                            i.putExtra(MainActivity.movie_data,ls.get(which));
                            startActivityForResult(i,REQUESTCODE2);


                        }
                });

                ad.show();
            }
        });

        Button b_delete = (Button)findViewById(R.id.buttonDeleteMovie);
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad1.setTitle("Pick a movie");

                CharSequence ch[] = new CharSequence[ls.size()];
                int i=0;
                for(FavoriteMovie f : ls){
                    ch[i++] = f.movieName;
                }

                ad1.setItems(ch, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FavoriteMovie f = ls.remove(which);
                        Toast.makeText(getApplicationContext(),f.movieName+" movie is deleted ",Toast.LENGTH_SHORT).show();


                    }
                });

                ad1.show();
            }


        });


        Button b_listbyyear = (Button)findViewById(R.id.buttonShowListY);

        b_listbyyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.example.nishanth.myfavoritemovies.intent.action.VIEW");
                if(ls.size()!=0) {
                    //    Intent i = new Intent(MainActivity.this, MoviebyYear.class);
                    Collections.sort(ls, new Comparator<FavoriteMovie>() {
                        @Override
                        public int compare(FavoriteMovie o1, FavoriteMovie o2) {
                            if( Integer.parseInt(o1.year) > Integer.parseInt(o2.year))
                                return 1;
                            else if(Integer.parseInt(o1.year) < Integer.parseInt(o2.year)){
                                return -1;
                            }
                            return 0;

                        }
                    });

                    i.addCategory(Intent.CATEGORY_DEFAULT);
                    i.putParcelableArrayListExtra(MainActivity.sortY, ls);
                    startActivity(i);
                }

            }
        });


        Button b_listbyrating = (Button)findViewById(R.id.buttonShowListR);

        b_listbyrating .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.example.nishanth.myfavoritemovies.intent.action.VIEW1");
                if(ls.size()!=0) {
                    Collections.sort(ls,new ComparatorByRating());
                    //    Intent i = new Intent(MainActivity.this, MoviebyYear.class);
                    i.addCategory(Intent.CATEGORY_DEFAULT);
                    i.putParcelableArrayListExtra(MainActivity.sortY, ls);
                    startActivity(i);
                }

            }
        });



    }

}


class ComparatorByRating implements Comparator<FavoriteMovie>{

    @Override
    public int compare(FavoriteMovie o1, FavoriteMovie o2) {
        if( o1.rating < o2.rating)
            return 1;
        else if(o1.rating > o2.rating){
            return -1;
        }
        return 0;
    }
}
