package com.example.nishanth.midtermlastyear;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nishanth on 3/19/2017.
 */

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private RecyclerView recyclerView;
    private ShoppingAdapter mAdapter;
    private Context context;
    private ButtonClickCallBack clickCallBack;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, price;
         public ImageView img;
        public TextView discount;
        public Button addToCart;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewGName);
            price = (TextView) view.findViewById(R.id.textViewGPrice);
            img = (ImageView)view.findViewById(R.id.imageViewGridImg);
            discount = (TextView) view.findViewById(R.id.textViewGDiscount);
            addToCart = (Button) view.findViewById(R.id.buttonAddTo);
            addToCart.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            addToCart.setEnabled(false);

            clickCallBack.onPlayButtonClick(getAdapterPosition());



        }
    }


    public ShoppingAdapter(ButtonClickCallBack clickCallBack, Context context, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
        this.clickCallBack = clickCallBack;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_grid_item_rows, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getName());
        holder.price.setText(movie.getPrice());
        Picasso.with(context).load(movie.getImgUrl()).into(holder.img);
        holder.discount.setText(movie.getDiscount());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}




