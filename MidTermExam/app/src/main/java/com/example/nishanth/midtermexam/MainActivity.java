package com.example.nishanth.midtermexam;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements ButtonClickCallBack,GetShopAsync.GetData {

  ArrayList<Movie> movieArrayList;
    ShoppingAdapter shoppingAdapter;
    RecyclerView recyclerView;
    ProgressDialog pd;
    static String url = "http://52.90.79.130:8080/MidTerm/get/products";
    static String method ="GET";
    DatabaseDataManager dm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        dm = new DatabaseDataManager(this);
        //pd.setTitle("Loading");
        //pd.setCancelable(false);
        pd.show();
        movieArrayList = new ArrayList<>();
        shoppingAdapter = new ShoppingAdapter(this,this,movieArrayList);


        RequestParams rq =new RequestParams(url,method);
        new GetShopAsync(this).execute(rq);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),3, LinearLayoutManager.VERTICAL,false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,GridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(shoppingAdapter);



    }

    @Override
    public void onPlayButtonClick(int p) {

    }

    @Override
    public void setData(ArrayList<Movie> movieArrayList) {


        pd.dismiss();
        this.movieArrayList  = movieArrayList;

        shoppingAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_items,menu);


        return true;
    }


    public void cart(MenuItem item){




    }

    public void history(MenuItem item){




    }






















}
