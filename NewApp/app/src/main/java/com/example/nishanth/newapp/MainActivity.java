package com.example.nishanth.newapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  GetJsonRes.SendJsonData, GetNewsImage.GetImg {

    final static String API_KEY ="2ce0d1514041402ebf44590e54ec0cec";
    final static String Base_URI = "https://newsapi.org/v1/articles";
    final static String key1 ="apiKey";
    final static String key2 = "source";
    final static String[] sourceValues = {"bbc-news","cnn","buzzfeed","espn","sky-news"};


    Spinner sp;

    TextView textTile,textAuthor,textPublish;
    EditText textDescr;
    Button getNews,finish1,first,prev,next,last;
    ImageView im;
    ArrayList<NewsData> n;

    int currentValue;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sp = (Spinner) findViewById(R.id.spinner);
        n= new ArrayList<>();
        textTile = (TextView) findViewById(R.id.textViewTitle);
        textAuthor = (TextView)findViewById(R.id.textViewAuthor);
        textPublish = (TextView) findViewById(R.id.textViewPublish);
        textDescr = (EditText) findViewById(R.id.textViewDescr);

        finish1 = (Button) findViewById(R.id.buttonFinish);
        first = (Button)findViewById(R.id.buttonfirst);
        prev = (Button)findViewById(R.id.buttonPrev);
        next = (Button)findViewById(R.id.buttonNext);
        last = (Button)findViewById(R.id.buttonLast);

        getNews = (Button)findViewById(R.id.buttonGetNews);

        im = (ImageView)findViewById(R.id.imageView);


      prev.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(currentValue <= 0)
              {
                  currentValue =0;
              }
              else{
                  currentValue --;
              }
              new GetNewsImage(MainActivity.this).execute(n.get(currentValue).getUrlToImage());
          }
      });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentValue >= n.size()-1)
                {
                    currentValue =n.size()-1;
                }
                else{
                    currentValue ++;
                }
                new GetNewsImage(MainActivity.this).execute(n.get(currentValue).getUrlToImage());
            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentValue >= n.size()-1)
                {
                    currentValue =n.size()-1;
                }
                else{
                    currentValue = n.size()-1;
                }
                new GetNewsImage(MainActivity.this).execute(n.get(currentValue).getUrlToImage());
            }
        });



        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentValue <= 0)
                {
                    currentValue =0;
                }
                else{
                    currentValue = 0;
                }
                new GetNewsImage(MainActivity.this).execute(n.get(currentValue).getUrlToImage());
            }
        });









        getNews.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            currentValue =0;

            if(isConnectedOnline())
            {
                n.clear();
                String s = sp.getSelectedItem().toString();


                RequestParam req = new RequestParam("Get",Base_URI);
                req.addParams(key1,API_KEY);
                req.addParams(key2,s);
              //  Toast.makeText(getApplicationContext(),req.getEncodeURL(),Toast.LENGTH_SHORT).show();

                GetJsonRes getJRes = new GetJsonRes(MainActivity.this);
                getJRes.execute(req);
            }
            else{
                Toast.makeText(getApplicationContext(),"No internet", Toast.LENGTH_SHORT).show();

            }

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
    public void sendJData(String s) {
         n = new ParseJson().parseJsonNews(s);



          new GetNewsImage(MainActivity.this).execute(n.get(currentValue).getUrlToImage());

        Log.d("Demo", s);
        Toast.makeText(MainActivity.this,n.get(0).getAuthor(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getImage(Bitmap bitmap) {
        im.setImageBitmap(bitmap);
      /*  textTile = (TextView) findViewById(R.id.textViewTitle);
        textAuthor = (TextView)findViewById(R.id.textViewAuthor);
        textPublish = (TextView) findViewById(R.id.textViewPublish);
        textDescr = (EditText) findViewById(R.id.textViewDescr);
        */
        textTile.setText("Title:   "+n.get(currentValue).getTitle());
        textAuthor.setText("Author: " +n.get(currentValue).getAuthor());
        textPublish.setText("Publish: "+n.get(currentValue).getPublish());
        textDescr.setText( "Description: "+n.get(currentValue).getDescr());


    }
}
