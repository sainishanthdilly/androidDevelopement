package com.example.nishanth.homework09;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shireen on 30-04-2017.
 */

public class Place_adapter   extends RecyclerView.Adapter<Place_adapter.ViewHolder> {

    private String[] mDataset;
    public static final int SMALL_VIEW = 0;
    public static final int LARGE_VIEW = 1;
    private int mType = 0;
    List<String> array_allitem=new ArrayList<String>();
    Context context;
    Thread soundThread;
    int duration ;
    Chatroom pod_atclick;
    String username;
    ProgressDialog pd;
    Place_adapter.SendAdapterata data;


    public Place_adapter(List<String> podcasts_list,TripDisplay d)
    {
        this.array_allitem=podcasts_list;
        data=d;


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.place_row,parent,false);
        context=parent.getContext();

        Place_adapter.ViewHolder view_holder=new Place_adapter.ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(array_allitem!=null) {

            holder.place_name.setText(array_allitem.get(position).toString());
            holder.no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.senddeleteAdapData(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return array_allitem.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mType;
    }

    public void setViewType(int type) {

        mType = type;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView place_name,username,usertime;
        Button go_chat;
        CardView cardView;
        ImageView no;

        public ViewHolder(View itemView) {
            super(itemView);

            place_name= (TextView) itemView.findViewById(R.id.placename);
            no=(ImageView)itemView.findViewById(R.id.nopla);


        }
    }


    static public interface SendAdapterata{
        public void sendAdapData(String position, boolean isFavCity);
        public void senddeleteAdapData(int position);
        public Context getContext();


    }




}
