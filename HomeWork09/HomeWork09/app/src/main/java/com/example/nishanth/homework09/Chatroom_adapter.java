package com.example.nishanth.homework09;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Shireen on 20-04-2017.
 */

public class Chatroom_adapter  extends RecyclerView.Adapter<Chatroom_adapter.ViewHolder>  {

    private String[] mDataset;
    public static final int SMALL_VIEW = 0;
    public static final int LARGE_VIEW = 1;
    private int mType = 0;
    List<Chatroom> array_allitem=new ArrayList<Chatroom>();
    Context context;
    Thread soundThread;
    int duration ;
    Chatroom pod_atclick;
    String username;
    ProgressDialog pd;
    SendAdapterata data;


    public Chatroom_adapter(List<Chatroom> podcasts_list, AddChatroom d,String user)
    {
        this.array_allitem=podcasts_list;
        data=d;
        username=user;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView room_name,username,usertime;
        Button go_chat;
        CardView cardView;
        ImageView trip_icon,image_one,image_two;

        public ViewHolder(View itemView) {
            super(itemView);

            room_name= (TextView) itemView.findViewById(R.id.chatroom_display);
            username= (TextView) itemView.findViewById(R.id.trip_addeduser);
            usertime= (TextView) itemView.findViewById(R.id.trip_addedtime);
            trip_icon=(ImageView)itemView.findViewById(R.id.trip_icon);

            go_chat=(Button)itemView.findViewById(R.id.go);

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chatrow,parent,false);
        context=parent.getContext();

        ViewHolder view_holder=new ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


            if(array_allitem!=null) {

                     holder.room_name.setText(array_allitem.get(position).getRoomname().toString());

                if(array_allitem.get(position).getCreated_by().equals(username)) {
                    holder.username.setText("Created by: You" );

                }
                else
                {
                    holder.username.setText("Created by:"+ array_allitem.get(position).getCreated_by().toString());
                }
                if((array_allitem.get(position).getTrip_icon()!=null)|| array_allitem.get(position).getTrip_icon()!="")
                {
                    String img=array_allitem.get(position).getTrip_icon();
                    Picasso.with(context).load(img).into(holder.trip_icon);

                }
                PrettyTime pt = new PrettyTime();
                String x = pt.format(new Date(Long.valueOf(array_allitem.get(position).getCreated_time())));


                     holder.go_chat.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Intent i=new Intent(context,TripDisplay.class);
                             // startActivity(i);
                             //Intent i=new Intent(context,ChatActivity.class);
                             i.putExtra("trip_id",array_allitem.get(position).getRoomid());
                             i.putExtra("trip_name",array_allitem.get(position).getRoomname());
                             i.putExtra("trip_creayedby",array_allitem.get(position).getCreated_by());
                             i.putExtra("trip_userid",array_allitem.get(position).getCreated_byid());
                             i.putExtra("trip_icon",array_allitem.get(position).getTrip_icon());
                             i.putExtra("created_time",array_allitem.get(position).getCreated_time());
                             context.startActivity(i);
                         }
                     });
            }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String roomid= array_allitem.get(position).getRoomid();
                data.senddeleteAdapData(roomid,position);
                return false;
            }
        });

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


    static public interface SendAdapterata{
        public void sendAdapData(String position, boolean isFavCity);
        public void senddeleteAdapData(String position,int position1);
        public Context getContext();


    }




}
