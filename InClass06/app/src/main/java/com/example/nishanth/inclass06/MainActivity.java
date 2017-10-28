package com.example.nishanth.inclass06;



/**
 * Created by nishanth on 2/13/2017.
 * Group 05
 * Sai Nishanth Dilly
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements  GetImageAsync.SetImage,GetItemsAsyncTask.SetData {


    String url ="http://rss.cnn.com/rss/cnn_tech.rss";
    Button next,prev,last,first,finish1,getNews;
    ImageView im;
    LinearLayout ln;
    ArrayList<Item> itemsRes;
    int pos;
    ProgressDialog progressDialog;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // getSupportActionBar().setLogo(R.drawable.ccnp);
       //actionBar.setIcon(R.drawable.cnna);
      //  getSupportActionBar().setIcon(R.drawable.ccnp);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("CNN News");


        im = (ImageView) findViewById(R.id.imageView);
        next = (Button) findViewById(R.id.buttonNext);
        prev = (Button) findViewById(R.id.buttonPrev);
        last = (Button) findViewById(R.id.buttonLast);
        first = (Button)findViewById(R.id.buttonFirst);
        finish1 = (Button) findViewById(R.id.buttonFinish);
        next = (Button)findViewById(R.id.buttonNext);
        getNews = (Button) findViewById(R.id.buttonGetNews);
        ln = (LinearLayout) findViewById(R.id.LinearData);
        pb = new ProgressBar(this);
        pos =0;
        itemsRes = new ArrayList<Item>();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading");
       // progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        finish1.setBackgroundColor(Color.BLUE);

        finish1.setEnabled(false);
        next.setEnabled(false); prev.setEnabled(false);last.setEnabled(false);
        first.setEnabled(false);
        getNews.setBackgroundColor(Color.BLUE);







        getNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos =0;
                progressDialog.show();

                if (isConnectedOnline()) {
                    RequestParam req = new RequestParam(url,"GET");

                    new GetItemsAsyncTask(MainActivity.this).execute(req);

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos == itemsRes.size()-1){

                }
                else{

                    ln.removeAllViews();
                    pos++;


                    TextView t1 = new TextView(MainActivity.this);
                    t1.setText("News Title: "+ itemsRes.get(pos).getTitle());
                    t1.setTextColor(Color.BLUE);

                    TextView t2 = new TextView(MainActivity.this);

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

                    String s="";
                    try {
                        Date d = sdf.parse(itemsRes.get(pos).getPubDate());
                        SimpleDateFormat formDt = new SimpleDateFormat("yyyy-MM-dd");
                        s = formDt.format(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    t2.setText("Published on: "+s);
                    //t2.setText("Published on: "+ itemsRes.get(pos).getPubDate());
                    t2.setTextColor(Color.DKGRAY);

                    TextView t3 = new TextView(MainActivity.this);
                    t3.setText("Description: "+itemsRes.get(pos).getDescription());

                    ln.addView(t1);
                    ln.addView(t2);
                    ln.addView(t3);
                    RequestParam re = new RequestParam(itemsRes.get(pos).getImgUrl(),"GET");
                    new GetImageAsync(MainActivity.this).execute(re);




                }
            }
        });


        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos == itemsRes.size()-1){

                }
                else{

                    ln.removeAllViews();
                    pos = itemsRes.size()-1;


                    TextView t1 = new TextView(MainActivity.this);
                    t1.setText("News Title: "+ itemsRes.get(pos).getTitle());
                    t1.setTextColor(Color.BLUE);

                    TextView t2 = new TextView(MainActivity.this);

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

                    String s="";
                    try {
                        Date d = sdf.parse(itemsRes.get(pos).getPubDate());
                        SimpleDateFormat formDt = new SimpleDateFormat("yyyy-MM-dd");
                        s = formDt.format(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    t2.setText("Published on: "+s);
                    //t2.setText("Published on: "+ itemsRes.get(pos).getPubDate());
                    t2.setTextColor(Color.DKGRAY);

                    TextView t3 = new TextView(MainActivity.this);
                    t3.setText("Description: "+itemsRes.get(pos).getDescription());

                    ln.addView(t1);
                    ln.addView(t2);
                    ln.addView(t3);
                    RequestParam re = new RequestParam(itemsRes.get(pos).getImgUrl(),"GET");
                    new GetImageAsync(MainActivity.this).execute(re);




                }
            }
        });



        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos == 0){

                }
                else{

                    ln.removeAllViews();
                    pos--;


                    TextView t1 = new TextView(MainActivity.this);
                    t1.setText("News Title: "+ itemsRes.get(pos).getTitle());
                    t1.setTextColor(Color.BLUE);

                    TextView t2 = new TextView(MainActivity.this);

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

                    String s="";
                    try {
                        Date d = sdf.parse(itemsRes.get(pos).getPubDate());
                        SimpleDateFormat formDt = new SimpleDateFormat("yyyy-MM-dd");
                        s = formDt.format(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    t2.setText("Published on: "+s);
                    //t2.setText("Published on: "+ itemsRes.get(pos).getPubDate());
                    t2.setTextColor(Color.DKGRAY);

                    TextView t3 = new TextView(MainActivity.this);
                    t3.setText("Description: "+itemsRes.get(pos).getDescription());

                    ln.addView(t1);
                    ln.addView(t2);
                    ln.addView(t3);
                    RequestParam re = new RequestParam(itemsRes.get(pos).getImgUrl(),"GET");
                    new GetImageAsync(MainActivity.this).execute(re);




                }
            }
        });


        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos == 0){

                }
                else{

                    ln.removeAllViews();
                    pos =0;


                    TextView t1 = new TextView(MainActivity.this);
                    t1.setText("News Title: "+ itemsRes.get(pos).getTitle());
                    t1.setTextColor(Color.BLUE);


                    TextView t2 = new TextView(MainActivity.this);

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

                    String s="";
                    try {
                        Date d = sdf.parse(itemsRes.get(pos).getPubDate());
                        SimpleDateFormat formDt = new SimpleDateFormat("yyyy-MM-dd");
                        s = formDt.format(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    t2.setText("Published on: "+s);
                   // t2.setText("Published on: "+ itemsRes.get(pos).getPubDate());
                    t2.setTextColor(Color.DKGRAY);

                    TextView t3 = new TextView(MainActivity.this);
                    t3.setText("Description: "+itemsRes.get(pos).getDescription());

                    ln.addView(t1);
                    ln.addView(t2);
                    ln.addView(t3);
                    RequestParam re = new RequestParam(itemsRes.get(pos).getImgUrl(),"GET");
                    new GetImageAsync(MainActivity.this).execute(re);




                }
            }
        });


        finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }






    private boolean isConnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo in =cm.getActiveNetworkInfo();
        if(in!=null &&in.isConnected())
            return true;

        return false;
    }












    @Override
    public void setimage(Bitmap bm) {

        im.setImageBitmap(bm);

    }

    @Override
    public void setData(ArrayList<Item> items) {

        progressDialog.dismiss();

        itemsRes = items;
        finish1.setEnabled(true);
        next.setEnabled(true); prev.setEnabled(true);last.setEnabled(true);
        first.setEnabled(true);

        TextView t1 = new TextView(this);
        t1.setText("News Title: "+ itemsRes.get(pos).getTitle());
        t1.setTextColor(Color.BLUE);

        TextView t2 = new TextView(this);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

        String s="";
        try {
            Date d = sdf.parse(itemsRes.get(pos).getPubDate());
            SimpleDateFormat formDt = new SimpleDateFormat("yyyy-MM-dd");
            s = formDt.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        t2.setText("Published on: "+s);
        t2.setTextColor(Color.DKGRAY);

        TextView t3 = new TextView(this);
        t3.setText("Description: "+itemsRes.get(pos).getDescription());

        ln.addView(t1);
        ln.addView(t2);
        ln.addView(t3);
        RequestParam re = new RequestParam(itemsRes.get(pos).getImgUrl(),"GET");
        new GetImageAsync(MainActivity.this).execute(re);


    }
}
