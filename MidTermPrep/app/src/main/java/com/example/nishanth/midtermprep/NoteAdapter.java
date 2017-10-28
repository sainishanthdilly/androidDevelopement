package com.example.nishanth.midtermprep;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

/**
 * Created by nishanth on 2/27/2017.
 */

public class NoteAdapter extends ArrayAdapter<NotesPojo> {
        Context mData;
        int mResource;
        List<NotesPojo> mListObj;
        int pos;

    public NoteAdapter(Context context, int resource, List<NotesPojo> objects) {
        super(context, resource, objects);
        mData = context;
        mResource = resource;
        mListObj = objects;
      }

@Override
public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if(convertView == null) {
        LayoutInflater inflater = (LayoutInflater) mData.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(mResource,parent,false);
        view = new ViewHolder();
        view.noteTask = (TextView) convertView.findViewById(R.id.textViewNotes);
        view.ischecked = (CheckBox) convertView.findViewById(R.id.checkBox);
        view.priority = (TextView) convertView.findViewById(R.id.textViewPriority);
        view.timerDe =  (TextView) convertView.findViewById(R.id.textViewTime);
        convertView.setTag(view);
        //    convertView.setOnClickListener(this);
            pos =position;



        }

            view = (ViewHolder) convertView.getTag();
            NotesPojo it = mListObj.get(position);

            view.noteTask.setText(it.getNoteTask() + " ");
            view.ischecked.setChecked(it.isChecked());
            view.priority.setText(it.getPriority());
            PrettyTime p = new PrettyTime();
            long l = Long.parseLong(it.getTimeNote());
          //  long g = System.currentTimeMillis() - l;
            String x = p.format(new Date(l));
            view.timerDe.setText(x);



        view.ischecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View v1=v;
                boolean isChecked = ((CheckBox)v).isChecked();


                if(!isChecked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mData);
                    builder.setMessage("Do you really want to mark the task complete ");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListObj.get(pos).setChecked(false);
                            ((CheckBox)v1).setChecked(false);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListObj.get(pos).setChecked(true);
                            ((CheckBox)v1).setChecked(true);
                        }
                    }).show();

                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(mData);
                    builder.setMessage("Do you really want to mark the task pending ");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                mListObj.get(pos).setChecked(true);
                            ((CheckBox)v1).setChecked(true);



                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListObj.get(pos).setChecked(false);
                            ((CheckBox)v1).setChecked(false);
                        }
                    }).show();

                }
            }


        });
     return convertView;
        }

    static class ViewHolder {
        TextView noteTask;
        CheckBox ischecked;
        TextView priority;
        TextView timerDe;
    }








}
