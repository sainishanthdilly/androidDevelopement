package com.example.nishanth.thegamedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nishanth on 2/20/2017.
 */

public class GameAdapter extends ArrayAdapter<GameDataStructure> {
    Context mData;
    int mResource;
    List<GameDataStructure> mListObj;


    public GameAdapter(Context context, int resource,  List<GameDataStructure> objects) {
        super(context, resource, objects);
        mData = context;
        mResource = resource;
        mListObj = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mData.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
            holder = new ViewHolder();
            holder.logoImageView= (ImageView) convertView.findViewById(R.id.imageView2);
            holder.trpTextView = (TextView) convertView.findViewById(R.id.textViewTRP);
            convertView.setTag(holder);






        }

        holder = (ViewHolder)convertView.getTag();

       GameDataStructure c = mListObj.get(position);

        ImageView logtextView = holder.logoImageView;
        logtextView.setImageBitmap(c.logo);

        TextView trpdetextView = holder.trpTextView;
        trpdetextView.setText(c.Details);
        //colorHexCodetextView.setTextColor(android.graphics.Color.parseColor(c.color));

        return convertView;
    }

    static class ViewHolder{
        ImageView logoImageView;
        TextView trpTextView;
    }





}
