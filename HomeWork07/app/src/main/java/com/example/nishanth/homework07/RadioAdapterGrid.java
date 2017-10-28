package com.example.nishanth.homework07;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nishanth on 3/9/2017.
 */
/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */


public class RadioAdapterGrid extends RecyclerView.Adapter<RadioAdapterGrid.RadioViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<RadioDataPojo> radioDataPojos;
    private ButtonClickCallBack buttonClickCallBack;


    public void setButtonClickCallBack(final ButtonClickCallBack buttonClickCallBack){
        this.buttonClickCallBack = buttonClickCallBack;
    }

    public RadioAdapterGrid(Context context, ArrayList<RadioDataPojo> radioDataPojos) {
        this.context = context;
        layoutInflater =LayoutInflater.from(context);
        this.radioDataPojos = radioDataPojos;
    }

    @Override
    public RadioAdapterGrid.RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RadioViewHolder radioViewHolder;

        View view =layoutInflater.inflate(R.layout.customgridrow,parent,false);
        radioViewHolder= new RadioViewHolder(view);
        return radioViewHolder;
    }

    @Override
    public void onBindViewHolder(RadioAdapterGrid.RadioViewHolder holder, int position) {
        Picasso.with(context).load(radioDataPojos.get(position).getImageUrl()).into(holder.im);
        holder.im.setAlpha(new Float(0.4));
        holder.title.setText(radioDataPojos.get(position).getTitle());
        holder.ib.setImageResource(android.R.drawable.ic_menu_slideshow);

    }

    @Override
    public int getItemCount() {
        return radioDataPojos.size();
    }

    class RadioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView im;
        TextView title,posted,music;
        ImageView ib;
        public RadioViewHolder(View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.imageViewLCard);
            title = (TextView) itemView.findViewById(R.id.textViewTitleCard);
            //posted = (TextView) itemView.findViewById(R.id.textViewPubdate);
            //music = (TextView) itemView.findViewById(R.id.textViewPlay);
            ib = (ImageView) itemView.findViewById(R.id.imageButtonPlayCard);
            ib.setOnClickListener(this);
            title.setOnClickListener(this);
            im.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.imageViewLCard){

                buttonClickCallBack.onPlayButtonClick(getAdapterPosition());

            }
            else{

                Intent i = new Intent(context,PlayActivity.class);
                i.putExtra("Title",radioDataPojos.get(getAdapterPosition()).getTitle());
                i.putExtra("Image",radioDataPojos.get(getAdapterPosition()).getImageUrl());
                i.putExtra("Date",radioDataPojos.get(getAdapterPosition()).getDatePoted());
                i.putExtra("Audio",radioDataPojos.get(getAdapterPosition()).getAudioUrl());
                i.putExtra("Duration",radioDataPojos.get(getAdapterPosition()).getDuration());
                i.putExtra("Description",radioDataPojos.get(getAdapterPosition()).getDescription());

                context.startActivity(i);



            }



        }
    }


}
