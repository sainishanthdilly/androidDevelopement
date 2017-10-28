package com.example.nishanth.midtermprep;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity implements PodCastAsync.ActivityData,ButtonClickCallBack {

    CharSequence[] c ={"Red","Green","Blue"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:7045351234"));
        //startActivity(intent);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        getResources().getString(R.string.app_name);

        /*RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(relativeLayout);*/


        AlertDailogListen obj = new AlertDailogListen();
        builder.setTitle("Nish").setTitle("Alert Dai").setCancelable(false);
        builder.setMultiChoiceItems(c, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(),"Click "+which,Toast.LENGTH_SHORT).show();


            }
        });
        //builder.setItems(c,obj);
        /*builder.setSingleChoiceItems(c,0,obj).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });*/
        //builder.setPositiveButton("Yes",  obj);
        //builder.setNegativeButton("No",  obj);
        final AlertDialog alert =builder.create();
        Button b= (Button) findViewById(R.id.button);
        //Picasso.with(context).load(R.drawable.landing_scree).into(imageView1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();

            }
        });

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void setUpData(ArrayList<RadioDataPojo> radioDataPojoArrayList) {

    }

    @Override
    public void onPlayButtonClick(int p) {

    }

    class AlertDailogListen implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(),"Click "+which,Toast.LENGTH_SHORT).show();

        }
    }


}

class ComparatorByRating implements Comparator<NotesPojo> {

    @Override
    public int compare(NotesPojo o1, NotesPojo o2) {
        if( o1.getId() < o2.getId())
            return 1;
        else if(o1.getId() > o2.getId()){
            return -1;
        }
        return 0;
    }
}
