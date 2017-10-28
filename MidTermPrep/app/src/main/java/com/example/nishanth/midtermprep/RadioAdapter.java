package com.example.nishanth.midtermprep;

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

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.RadioViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<RadioDataPojo> radioDataPojos;
    private ButtonClickCallBack buttonClickCallBack;


    public void setButtonClickCallBack(final ButtonClickCallBack buttonClickCallBack){
        this.buttonClickCallBack = buttonClickCallBack;
    }

    public RadioAdapter(Context context, ArrayList<RadioDataPojo> radioDataPojos) {
        this.context = context;
        layoutInflater =LayoutInflater.from(context);
        this.radioDataPojos = radioDataPojos;
    }

    @Override
    public RadioAdapter.RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RadioViewHolder radioViewHolder;

        View view =layoutInflater.inflate(R.layout.custom_row,parent,false);
        radioViewHolder= new RadioViewHolder(view);
        return radioViewHolder;
    }

    @Override
    public void onBindViewHolder(RadioAdapter.RadioViewHolder holder, int position) {
        Picasso.with(context).load(radioDataPojos.get(position).getImageUrl()).into(holder.im);
        holder.title.setText(radioDataPojos.get(position).getTitle());
        holder.posted.setText(radioDataPojos.get(position).getDatePoted());
        holder.music.setText("Play Now");



    }

    @Override
    public int getItemCount() {
        return radioDataPojos.size();
    }

    class RadioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView im;
        TextView title,posted,music;
        ImageButton ib;
        public RadioViewHolder(View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.imageViewL);
            title = (TextView) itemView.findViewById(R.id.textViewTitle);
            posted = (TextView) itemView.findViewById(R.id.textViewPubdate);
            music = (TextView) itemView.findViewById(R.id.textViewPlay);
            ib = (ImageButton) itemView.findViewById(R.id.imageButtonPlay);
            ib.setOnClickListener(this);
            im.setOnClickListener(this);
            posted.setOnClickListener(this);
            music.setOnClickListener(this);
            title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.imageButtonPlay){
                buttonClickCallBack.onPlayButtonClick(getAdapterPosition());

            }
            else{
             /*   Intent i = new Intent(context,PlayActivity.class);
                i.putExtra("Title",radioDataPojos.get(getAdapterPosition()).getTitle());
                i.putExtra("Image",radioDataPojos.get(getAdapterPosition()).getImageUrl());
                i.putExtra("Date",radioDataPojos.get(getAdapterPosition()).getDatePoted());
                i.putExtra("Audio",radioDataPojos.get(getAdapterPosition()).getAudioUrl());
                i.putExtra("Duration",radioDataPojos.get(getAdapterPosition()).getDuration());
                i.putExtra("Description",radioDataPojos.get(getAdapterPosition()).getDescription());

                context.startActivity(i);*/



            }



        }
    }


}
