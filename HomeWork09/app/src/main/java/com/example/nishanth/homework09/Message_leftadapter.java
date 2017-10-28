package com.example.nishanth.homework09;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Shireen on 20-04-2017.
 */


public class Message_leftadapter extends RecyclerView.Adapter<Message_leftadapter.ViewHolder>  {


    private String[] mDataset;
    public static final int SMALL_VIEW = 0;
    public static final int LARGE_VIEW = 1;
    private int mType = 0;
    List<Message_pojo> array_allitem=new ArrayList<>();
    Context context;
    Thread soundThread;
    View itemView;
    int duration ;
    Chatroom pod_atclick;
    SendAdapterata data;
    String user_id;

    SharedPreferences sharedPreferences;
    public Message_leftadapter(List<Message_pojo> podcasts_list, ChatActivity d,String counter)
    {
        this.array_allitem=podcasts_list;
        data=d;
        user_id=counter;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView message_onscreen,message_username,message_time;
        ImageView message_image,image_one,image_two;

        CardView cardView;
        RelativeLayout relativeLayout,relativeLayout2;


        public ViewHolder(View itemView) {
            super(itemView);

            message_onscreen= (TextView) itemView.findViewById(R.id.message_onscreen);
            message_username=(TextView) itemView.findViewById(R.id.message_name);
            message_time=(TextView) itemView.findViewById(R.id.message_time);
            message_image=(ImageView) itemView.findViewById(R.id.msg_img);
            cardView=(CardView)itemView.findViewById(R.id.card_view);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.realtive_lay);
            relativeLayout2=(RelativeLayout)itemView.findViewById(R.id.rel_inner);
            image_one=(ImageView)itemView.findViewById(R.id.imgone);
            image_two=(ImageView)itemView.findViewById(R.id.imgtwo);


        }

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.charscreenrow, parent, false);
        context = parent.getContext();
        ViewHolder view_holder=new ViewHolder(view);
        return view_holder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(!array_allitem.get(position).getDeleted().keySet().contains(user_id)){

        if(array_allitem!=null) {

            if (array_allitem.get(position).getMessage_userid().equals(user_id)) {
                //  holder.relativeLayout.setGravity(RelativeLayout.ALIGN_LEFT);
                holder.relativeLayout2.setBackgroundColor(Color.parseColor("#20B2AA"));
                String img = array_allitem.get(position).getUserImage();
                Log.d("lkk", img);
                Picasso.with(context).load(img).into(holder.image_one);
                holder.image_two.setVisibility(View.GONE);

            } else if (array_allitem.get(position).getMessage_userid() != user_id) {
                // holder.relativeLayout.setGravity(RelativeLayout.ALIGN_RIGHT);
                holder.relativeLayout2.setBackgroundColor(Color.parseColor("#E51A4C"));
                String img = array_allitem.get(position).getUserImage();
                Picasso.with(context).load(img).into(holder.image_two);
                holder.image_one.setVisibility(View.GONE);
            }

            holder.message_onscreen.setText(array_allitem.get(position).getActual_message());
            holder.message_username.setText(array_allitem.get(position).getMessage_username());
            PrettyTime pt = new PrettyTime();
            String x = pt.format(new Date(Long.valueOf(array_allitem.get(position).getMessage_time())));

            holder.message_time.setText(x);


            String img = array_allitem.get(position).getImage_message();

            if ((array_allitem.get(position).getImage_message() != null)) {
                Picasso.with(context).load(img).into(holder.message_image);

            }


            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    String messageid = array_allitem.get(holder.getAdapterPosition()).getMessage_id();
                    data.senddeleteAdapData(messageid);
                    return false;
                }
            });
            //holder.message_time.setText(array_allitem.get(position).getMessage_time());
        }
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


    static public interface SendAdapterata{
        public void sendAdapData(String position, boolean isFavCity);
        public void senddeleteAdapData(String position);
        public Context getContext();


    }
}
