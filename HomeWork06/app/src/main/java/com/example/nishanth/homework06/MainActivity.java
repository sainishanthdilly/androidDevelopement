package com.example.nishanth.homework06;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements SetUpData,AdapterView.OnItemClickListener {

    static String MyFAVORITES="My Favorites";
    static String KeyForFavData ="KeyForFavValues";
    SharedPreferences sharedpreferences;
    ListView lv;
    AdapterITunes ia;
    ArrayList<ITunesPojo> iTunesPojoArrayList;




    static String URL = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
    ProgressDialog pd;
    AlertDialog.Builder builder,builder1;
    RequestParams req;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_items,menu);


        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("iTunes Top Paid Apps");

        lv= (ListView) findViewById(R.id.listView1);
        pd = new ProgressDialog(this);
        req = new RequestParams(URL,"GET");
        pd.setMessage("Loading");
        pd.show();
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to Favorites").setCancelable(false);
        builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Add to Favorites").setCancelable(false);

        new GetItunesDataAsync(this).execute(req);


    }

    @Override
    public void setData(ArrayList<ITunesPojo> arrayList) {
       iTunesPojoArrayList = arrayList;
        pd.dismiss();
         ia = new AdapterITunes(this,R.layout.itemrowslistview,iTunesPojoArrayList);
        ia.notifyDataSetChanged();
        ia.setNotifyOnChange(true);

        lv.setAdapter(ia);
        lv.setOnItemClickListener(this);

    }

    public void setFav(View view){


    }

    public void sortDesc(MenuItem item){

        Collections.sort(iTunesPojoArrayList, new Comparator<ITunesPojo>() {
            @Override
            public int compare(ITunesPojo o1, ITunesPojo o2) {
                return  o2.getPrice().compareTo(o1.getPrice());

            }
        });

        ia.notifyDataSetChanged();




    }
    public void refresh2(MenuItem item){

        pd.show();

        new GetItunesDataAsync(this).execute(req);




    }

    public void storeFav(MenuItem item){

        ArrayList<ITunesPojo> favArrayList = new ArrayList<>();
        sharedpreferences = getSharedPreferences(MyFAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Set<String>  s=  new HashSet();
        for(ITunesPojo tpo :iTunesPojoArrayList){
            if(tpo.isCheckedFav) {
                favArrayList.add(tpo);
                s.add(tpo.getLabel()+","+tpo.getPrice()+","+tpo.getImgURL());


            }
        }
        editor.putStringSet(KeyForFavData,s);
        editor.commit();

        Intent intent = new Intent(MainActivity.this,diplayfavorites.class);

        startActivity(intent);

    }




    public void sortAsc(MenuItem item){

        Collections.sort(iTunesPojoArrayList, new Comparator<ITunesPojo>() {
            @Override
            public int compare(ITunesPojo o1, ITunesPojo o2) {
                return  o1.getPrice().compareTo(o2.getPrice());

            }
        });

        ia.notifyDataSetChanged();



    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

            LinearLayout view2 = (LinearLayout) view;
            final ImageView view1 = (ImageView)view2.getChildAt(2);


            if (!iTunesPojoArrayList.get(position).isCheckedFav) {

                builder.setMessage("Are you sure that you want to add this App to Favorites");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ImageView im = (ImageView) view1;
                        Resources res = getResources();


                        int resourceId = res.getIdentifier(
                                "blackstar", "drawable", getPackageName());
                        im.setImageResource(resourceId);
                        //im.setTag(R.drawable.blackstar);
                        iTunesPojoArrayList.get(position).isCheckedFav = true;
                        ia.notifyDataSetChanged();


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ImageView im = (ImageView) view1;
                        Resources res = getResources();

                        int resourceId = res.getIdentifier(
                                "whitestar", "drawable", getPackageName());
                        im.setImageResource(resourceId);
                        im.setTag(R.drawable.whitestar);
                        iTunesPojoArrayList.get(position).isCheckedFav = false;
                        ia.notifyDataSetChanged();

                    }
                }).show();

            } else {

                builder1.setMessage("Are you sure that you want to remove this App to Favorites").setCancelable(false);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageView im = (ImageView) view1;
                        Resources res = getResources();

                        int resourceId = res.getIdentifier(
                                "whitestar", "drawable", getPackageName());
                        im.setImageResource(resourceId);
                        //im.setTag(R.drawable.whitestar);
                        iTunesPojoArrayList.get(position).isCheckedFav = false;
                        ia.notifyDataSetChanged();


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImageView im = (ImageView) view1;
                        Resources res = getResources();


                        int resourceId = res.getIdentifier(
                                "blackstar", "drawable", getPackageName());
                        im.setImageResource(resourceId);
                        im.setTag(R.drawable.blackstar);
                        iTunesPojoArrayList.get(position).isCheckedFav = true;
                        ia.notifyDataSetChanged();

                    }
                }).show();
            }

        }

}

