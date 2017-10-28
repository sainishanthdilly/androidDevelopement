package com.example.nishanth.okhttpclientdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nishanth.
 */

public class MessageAdapter extends ArrayAdapter<Message> {
    List<Message> mData;
    int mResource;
    Context mContext;
    public MessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        this.mData=objects;
        this.mResource=resource;
        this.mContext=context;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
        Message msg=mData.get(position);
        TextView Msg = (TextView) convertView.findViewById(R.id.msg);
        TextView msgsender = (TextView) convertView.findViewById(R.id.msgSender);
        TextView msgTime = (TextView) convertView.findViewById(R.id.msgTime);
        ImageView imageMsg = (ImageView) convertView.findViewById(R.id.imageMsg);
            Msg.setText(msg.getComment());

        msgsender.setText(msg.getUserFname()+" "+msg.getUserLname());
        msgTime.setText(msg.getCreatedAt());

        return convertView;
    }
}
