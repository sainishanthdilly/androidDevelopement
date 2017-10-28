package com.example.nishanth.midtermlastyear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<Movie> mv;
    ListView lv;
    ITunesAdapter adapter;
    LinearLayout hor,vert;
    DatabaseDataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        lv= (ListView) findViewById(R.id.listviewcart);
        double sum=0;
        dm = new DatabaseDataManager(this);

        mv  = (ArrayList<Movie>) getIntent().getExtras().get("CART");

        adapter = new ITunesAdapter(this,R.layout.list_tem_rows,mv);

        lv.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        vert = (LinearLayout) findViewById(R.id.linearTotalVe);

        hor = (LinearLayout) findViewById(R.id.linearCarthori);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mv.remove(position);
                adapter.notifyDataSetChanged();



                return false;
            }
        });


        for(Movie m: mv ){
           sum+= Double.parseDouble(m.getPrice());

        }

        TextView tv = (TextView) findViewById(R.id.textViewTot);
               tv.setText("Total: " + Double.toString(sum));

       // vert.addView(tv);
        Button cancel, checkout;
        cancel = new Button(this);
        cancel.setText("Cancel");
        checkout = new Button(this);
        checkout.setText("CheckOut");

        hor.addView(cancel);


        if(getIntent().getExtras().getBoolean("History"))
        {

        }
        else {

            hor.addView(checkout);


            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (Movie m : mv) {
                        Note n = new Note();
                        n.setPrice(m.getPrice());
                        n.setName(m.getName());
                        n.setThumb_url(n.getThumb_url());
                        dm.saveNote(n);
                    }
                    Intent i = new Intent(CartActivity.this, MainActivity.class);
                    startActivity(i);

                }
            });


        }

    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(CartActivity.this, MainActivity.class);
            startActivity(i);
        }
    });







    }
}
