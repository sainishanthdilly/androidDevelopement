package com.example.nishanth.homework06;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */


public class diplayfavorites extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    ArrayList<ITunesPojo> tunesPojoArrayList;
    AdapterITunes adapter;
    AlertDialog.Builder builder,builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diplayfavorites);
        lv= (ListView) findViewById(R.id.listViewFav);
        getSupportActionBar().setTitle("Your Favorites");
        tunesPojoArrayList= new ArrayList<>();
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyFAVORITES, Context.MODE_PRIVATE);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to Favorites").setCancelable(false);
        builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Add to Favorites").setCancelable(false);

        Set<String> set = sharedpreferences.getStringSet(MainActivity.KeyForFavData,null);
        if(set!=null){
            Iterator<String> it= set.iterator();
            while (it.hasNext()){
                String s = it.next();
                String[] split = s.split(",");
                ITunesPojo po = new ITunesPojo();
                po.setLabel(split[0]);
                po.setPrice(split[1]);
                po.setImgURL(split[2]);
                po.isCheckedFav=true;

                tunesPojoArrayList.add(po);


            }
            adapter =  new AdapterITunes(getApplicationContext(),R.layout.itemrowslistview,tunesPojoArrayList);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(this);





        }






    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


        LinearLayout view2 = (LinearLayout) view;
        final ImageView view1 = (ImageView)view2.getChildAt(2);


        if (!tunesPojoArrayList.get(position).isCheckedFav) {

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
                    tunesPojoArrayList.get(position).isCheckedFav = true;
                    adapter.notifyDataSetChanged();


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
                    tunesPojoArrayList.get(position).isCheckedFav = false;
                     adapter.notifyDataSetChanged();

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
                    tunesPojoArrayList.get(position).isCheckedFav = false;
                    adapter.notifyDataSetChanged();


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
                    tunesPojoArrayList.get(position).isCheckedFav = true;
                    adapter.notifyDataSetChanged();

                }
            }).show();
        }









    }
}
