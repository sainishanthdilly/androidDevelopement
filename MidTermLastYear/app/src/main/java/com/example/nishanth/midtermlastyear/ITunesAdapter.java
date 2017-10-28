package com.example.nishanth.midtermlastyear;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nishanth on 3/19/2017.
 */

public class ITunesAdapter extends ArrayAdapter<Movie> {
    Context mData;
    int mResource;
    List<Movie> mListObj;
    int pos;

    public ITunesAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        mData = context;
        mResource = resource;
        mListObj = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mData.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            view = new ViewHolder();
            view.name = (TextView) convertView.findViewById(R.id.textViewTitle);
            view.img = (ImageView) convertView.findViewById(R.id.imageView);
            view.price = (TextView) convertView.findViewById(R.id.textViewPrice);
            convertView.setTag(view);
            pos = position;


        }

        view = (ViewHolder) convertView.getTag();
        Movie it = mListObj.get(position);

        view.name.setText(it.getName() + " ");
        Picasso.with(mData).load(it.getImgUrl()).into(view.img);

        view.price.setText(it.getPrice());


        return convertView;
    }

    static class ViewHolder {
        TextView name;
        ImageView img;
        TextView price;

    }
}






