package com.example.nishanth.homework06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nishanth on 2/24/2017.
 * Nishanth
 * Shireen
 * Group 04
 *
 */

public class AdapterITunes extends ArrayAdapter<ITunesPojo> {
    Context mData;
    int mResource;
    List<ITunesPojo> mListObj;
    public AdapterITunes(Context context, int resource, List<ITunesPojo> objects) {
        super(context, resource, objects);
        mData = context;
        mResource = resource;
        mListObj = objects;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mData.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
            view = new ViewHolder();
            view.logoImageView = (ImageView) convertView.findViewById(R.id.imageViewIcon);
            view.star = (ImageView) convertView.findViewById(R.id.imageViewIconFav);
            view.trpTextView = (TextView) convertView.findViewById(R.id.textViewData);
            convertView.setTag(view);

        }
        view = (ViewHolder) convertView.getTag();

        ITunesPojo it = mListObj.get(position);
        Picasso.with(mData).load(it.getImgURL()).into(view.logoImageView);

        if(!it.isCheckedFav ) {

            view.star.setImageDrawable(mData.getApplicationContext().getResources().getDrawable(R.drawable.whitestar));
        }
        else {
            view.star.setImageDrawable(mData.getApplicationContext().getResources().getDrawable(R.drawable.blackstar));

        }


        view.trpTextView.setText(it.getLabel()+" Price: "+it.getPrice());




        return convertView;
    }
    static class ViewHolder{
        ImageView logoImageView;
        ImageView star;
        TextView trpTextView;
    }



}

