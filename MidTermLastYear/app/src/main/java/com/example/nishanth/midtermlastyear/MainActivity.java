package com.example.nishanth.midtermlastyear;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetITunesAsync.GetData,ButtonClickCallBack {

    ListView listView;
    RecyclerView recyclerView;
    ITunesAdapter iTunesAdapterArrayAdapter;
    ShoppingAdapter shoppingAdapter;
    ArrayList<Movie> movieList;
    DatabaseDataManager dm;
    ArrayList<Movie> cartList;
    ImageButton b;

    ProgressDialog pg;
    static String url = "http://52.90.79.130:8080/MidTerm/get/products";
    static String method ="GET";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Shopping App");
        pg = new ProgressDialog(this);

        cartList = new ArrayList<>();
        dm = new DatabaseDataManager(this);
        pg.setTitle("Loading");

        pg.setCancelable(false);

        pg.show();
        RequestParams rq =new RequestParams(url,method);
        new GetShopAsync(this).execute(rq);
        //listView = (ListView) findViewById(R.id.listview1);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);



    }

    @Override
    public void setData(ArrayList<Movie> movieArrayList) {


        pg.dismiss();
        this.movieList  = movieArrayList;

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),3,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        shoppingAdapter = new ShoppingAdapter(this,this,movieList);
        recyclerView.setAdapter(shoppingAdapter);
        shoppingAdapter.notifyDataSetChanged();


    }

    @Override
    public void onPlayButtonClick(int p) {
        Movie m= new Movie();

        m.setName(movieList.get(p).getName());
        m.setImgUrl(movieList.get(p).getImgUrl());
        m.setPrice(movieList.get(p).getPrice());
        m.setDiscount(movieList.get(p).getDiscount());
        cartList.add(m);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_items,menu);

        return true;
    }


    public void cart(MenuItem item){

        Intent i = new Intent(MainActivity.this,CartActivity.class);
        i.putExtra("CART", cartList);
        i.putExtra("History",false);

        startActivity(i);







    }

    public void history(MenuItem item){

        Intent i = new Intent(MainActivity.this,CartActivity.class);
        i.putExtra("History",true);
        i.putExtra("CART", cartList);

        startActivity(i);



    }

}
