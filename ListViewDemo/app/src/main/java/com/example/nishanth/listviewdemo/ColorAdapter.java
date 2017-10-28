package com.example.nishanth.listviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by nishanth on 2/20/2017.
 */

public class ColorAdapter extends ArrayAdapter<ColorAc> {
    Context mData;
    int mResource;
    List<ColorAc> mListObj;

    public ColorAdapter(Context context, int resource, List<ColorAc> objects) {
        super(context, resource, objects);
        mData = context;
        mResource = resource;
        mListObj = objects;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
         if(convertView == null){
             LayoutInflater inflater = (LayoutInflater) mData.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(mResource,parent,false);
            holder = new ViewHolder();
             holder.colorNametextView= (TextView) convertView.findViewById(R.id.textViewColorName);
             holder.colorHexCodetextView = (TextView) convertView.findViewById(R.id.textViewColorHex);
             convertView.setTag(holder);






         }

        holder = (ViewHolder)convertView.getTag();

        ColorAc c = mListObj.get(position);

        TextView colorNametextView = holder.colorNametextView;
        colorNametextView.setText(c.color);

       TextView colorHexCodetextView = holder.colorHexCodetextView;
        colorHexCodetextView.setText(c.hexCode);
        //colorHexCodetextView.setTextColor(android.graphics.Color.parseColor(c.color));

        return convertView;
    }

    static class ViewHolder{
        TextView colorNametextView;
        TextView colorHexCodetextView;
    }
}
