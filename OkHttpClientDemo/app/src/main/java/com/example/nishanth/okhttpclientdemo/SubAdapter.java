package com.example.nishanth.okhttpclientdemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.channels.Channel;
import java.util.List;

/**
 * Created by Shireen on 27-03-2017.
 */

public class SubAdapter extends ArrayAdapter<Channels> {

    List<Channels> mData;
    int mResource;
    Context mContext;
    SubscriptionActivity activity;


    public SubAdapter(SubscriptionActivity activity,@NonNull Context context, @LayoutRes int resource, @NonNull List<Channels> objects) {
        super(context, resource, objects);
        this.mData=objects;
        this.mResource=resource;
        this.mContext=context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        if(convertView == null)
        {
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
       Channels channels=mData.get(position);
        TextView subname = (TextView) convertView.findViewById(R.id.Subname);
        subname.setText(channels.getChannel_name().toString());

        Button button = (Button)convertView.findViewById(R.id.View);
        button.setText(channels.getJoin().toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt = (Button) v;

                if(((Button) v).getText().toString().equalsIgnoreCase("JOIN")){
                    bt.setText("VIEW");
                    mData.get(position).setJoin("VIEW");





                }
                else{

                    activity.goToNext(mData.get(position));

                }


            }
        });




        return convertView;
    }

    public interface SendNextAc{
        public void goToNext(Channels channel);
    }

}
