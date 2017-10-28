package com.example.nishanth.inclass06imp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;
/**
 * Created by nishanth on 2/26/2017.
 */


public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.MyViewHolder> {

    private List<Note> moviesList;
    private RecyclerView recyclerView;
    private ShoppingAdapter mAdapter;
    private Context context;
    private ButtonClickCallBack clickCallBack;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView noteTask,  timerDe;
        public TextView priority;
        public CheckBox ischecked;


        public MyViewHolder(View view) {
            super(view);

            noteTask = (TextView) view.findViewById(R.id.textViewNotes);
            ischecked = (CheckBox) view.findViewById(R.id.checkBox);
           priority = (TextView) view.findViewById(R.id.textViewPriority);
            timerDe =  (TextView) view.findViewById(R.id.textViewTime);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    clickCallBack.onPlayButtonClick(getAdapterPosition());

                    return false;

                }
            });

        }


        @Override
        public void onClick(View v) {



        }
    }


    public ShoppingAdapter(ButtonClickCallBack clickCallBack, Context context, List<Note> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
        this.clickCallBack = clickCallBack;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemrowslistview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Note it = moviesList.get(position);

        if(moviesList.get(position).getStatus().equalsIgnoreCase("PENDING")){
            holder.ischecked.setChecked(false);
        }
        else{
            holder.ischecked.setChecked(true);
        }


        holder.noteTask.setText(it.getNote() + " ");
        //holder.ischecked.setChecked(false);
        String pr ="High";
        if(it.getPriority().equals("0"))
        {
            pr ="High";
        }
        else if(it.getPriority().equals("1")){
            pr ="Medium";

        }
        else{
            pr ="Low";

        }
        holder.priority.setText(pr);
        PrettyTime p = new PrettyTime();
        if(it.getTime()!=null) {
            long l = Long.parseLong(it.getTime());
            //  long g = System.currentTimeMillis() - l;
            String x = p.format(new Date(l));
            holder.timerDe.setText(x);


        }


      holder.ischecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View v1=v;
                boolean isChecked = ((CheckBox)v).isChecked();


                if(!isChecked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you really want to mark the task complete ");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clickCallBack.onCheckedClickCallBack(position,"PENDING");
                            //mListObj.get(pos).setChecked(false);
                            ((CheckBox)v1).setChecked(false);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //mListObj.get(pos).setChecked(true);
                            ((CheckBox)v1).setChecked(true);
                        }
                    }).show();

                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you really want to mark the task pending ");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //mListObj.get(pos).setChecked(true);
                            clickCallBack.onCheckedClickCallBack(position,"COMPLETE");

                            ((CheckBox)v1).setChecked(true);



                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //mListObj.get(pos).setChecked(false);

                            ((CheckBox)v1).setChecked(false);
                        }
                    }).show();

                }
            }


        });




    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}




