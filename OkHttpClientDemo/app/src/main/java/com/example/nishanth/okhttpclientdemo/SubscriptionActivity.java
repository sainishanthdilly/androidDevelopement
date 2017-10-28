package com.example.nishanth.okhttpclientdemo;



/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionActivity extends AppCompatActivity implements ChannelTask.SubChannelData,Button.OnClickListener,ChannelAllTast.SubChannelALLData,SubAdapter.SendNextAc {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences pref;
    ArrayList<Channels> channelsArrayList;
    ArrayList<Channels> channelsArrayListAddMore;
    ListView list_view;
    SubAdapter adapter;
    Button add;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        list_view=(ListView)findViewById(R.id.Listview1);
        this.channelsArrayList = new ArrayList<Channels>();
        this.channelsArrayListAddMore = new ArrayList<Channels>();

        add = (Button) findViewById(R.id.AddMore);



        //  Channels s1=new Channels("Surgeon Simulator","http://is2.mzstatic.com/image/thumb/Purple20/v4/0c/21/41/0c214150-2e55-6952-8260-988addcae48c/mzl.blrebwtc.png/53x53bb-85.png");
       // channelsArrayList.add(s1);
        add.setOnClickListener(this);

        new ChannelTask(this).execute("http://52.90.79.130:8080/Groups/api/get/subscriptions",pref.getString("token",""));


    }

    @Override
    public void printSubChannelData(ArrayList<Channels> channelsArrayList) {

        this.channelsArrayList = channelsArrayList;
        for(int i=0;i<channelsArrayList.size();i++)
        {
            this.channelsArrayList.get(i).setJoin("VIEW");
        }

        adapter=new SubAdapter(SubscriptionActivity.this,SubscriptionActivity.this,R.layout.rowlay, this.channelsArrayList);
        list_view.setAdapter(adapter);



        adapter.notifyDataSetChanged();


        Toast.makeText(getApplicationContext(), channelsArrayList.toString(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {

        Button bt = (Button)v;

        if(v.getId() == R.id.AddMore)
        {

            if(bt.getText().toString().equalsIgnoreCase("Done")){

                bt.setText("ADDMORE");

                new ChannelTask(this).execute("http://52.90.79.130:8080/Groups/api/get/subscriptions",pref.getString("token",""));

            }else {
                bt.setText("Done");
                new ChannelAllTast(SubscriptionActivity.this).execute("http://52.90.79.130:8080/Groups/api/get/channels", pref.getString("token", ""));
            }
        }



    }

    @Override
    public void printSubChannelALLData(ArrayList<Channels> channelsArrayList) {
        this.channelsArrayListAddMore = channelsArrayList;
        for(int i=0;i<channelsArrayListAddMore.size();i++)
        {
            this.channelsArrayListAddMore.get(i).setJoin("JOIN");
        }
        for(int i=0;i<this.channelsArrayList.size();i++)
        {
            for(int j=0;j<channelsArrayListAddMore.size();j++)
            {
                if(this.channelsArrayListAddMore.get(j).getChannel_id().equalsIgnoreCase(this.channelsArrayList.get(i).getChannel_id())) {
                    this.channelsArrayListAddMore.get(j).setJoin("VIEW");
                    break;
                }
            }

        }

        adapter=new SubAdapter(SubscriptionActivity.this,SubscriptionActivity.this,R.layout.rowlay, this.channelsArrayListAddMore);
        list_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();



    }

    @Override
    public void goToNext(Channels channel) {
        Intent i = new Intent(SubscriptionActivity.this, ChatScreen.class);
        i.putExtra("Chan", channel.getChannel_name());
        startActivity(i);

    }
}
