package com.example.nishanth.passwordgeneration;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button b;
    SeekBar s1,s2,s3,s4;
    TextView r1,r2,r3,r4;
    ProgressDialog progressDialog;
    ArrayList<String> password;
    ArrayList<String> pt;

    Handler handler;
    static String pwd ="PASSWORDS";
    static String pwdT = "PASSWORDST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
                b = (Button)findViewById(R.id.button_generate);
        password = new ArrayList<String>();
        pt = new ArrayList<String>();


        s3 = (SeekBar) findViewById(R.id.seekBar3);
        s4 = (SeekBar) findViewById(R.id.seekBar4);
        s1 = (SeekBar) findViewById(R.id.seekBar3);

        s2 = (SeekBar) findViewById(R.id.seekBar4);

        r1 = (TextView)findViewById(R.id.pg_textViewTRcount);
        r2 = (TextView)findViewById(R.id.pg_textViewTRL);
        r3 = (TextView)findViewById(R.id.pg_textViewARC);

        r4  = (TextView)findViewById(R.id.pg_textViewARL);
        progressDialog.setMessage("Generating Passwords");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);






        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                r1.setText(progress+"");
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r2.setText((7+progress)+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r3.setText((1+progress)+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        s4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r4.setText((7+progress)+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c =Integer.parseInt(r3.getText().toString());
                int l = Integer.parseInt(r4.getText().toString());

                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        return false;
                    }
                });



                new Thread(new DoWork1(Integer.parseInt(r1.getText().toString()),Integer.parseInt(r2.getText().toString()))).start();


                        AsyncTPC b = new AsyncTPC(c, l);
                b.execute();




            }
        });






    }

 class AsyncTPC extends AsyncTask<Void,Integer,Void>{

     int count, length;
     AsyncTPC(int count,int length){
         this.count = count;
         this.length = length;
     }

     @Override
     protected Void doInBackground(Void... params) {
         int x=0;
         for(int i=0;i<count;i++){
            String s = Util.getPassword(length);
            password.add(s);
            x+=100/count;
            publishProgress(x);
         }

         return null;
     }

     @Override
     protected void onPreExecute() {
         super.onPreExecute();
         progressDialog.show();
     }

     @Override
     protected void onPostExecute(Void aVoid) {
         super.onPostExecute(aVoid);
         progressDialog.dismiss();

         Intent i = new Intent(MainActivity.this,GeneratedPasswords.class);
         i.putExtra(MainActivity.pwd,password);
         i.putExtra(MainActivity.pwdT,pt);
         startActivity(i);
     }

     @Override
     protected void onProgressUpdate(Integer... values) {
         super.onProgressUpdate(values);
         progressDialog.setProgress(values[0]);

     }




 }


    class DoWork1 implements Runnable{
        int ct,ln;
        DoWork1(int ct,int ln){
            this.ct= ct;
            this.ln = ln;
        }

        @Override
        public void run() {

            Message m = new Message();
            for(int i=0;i<ct;i++){

                pt.add(Util.getPassword(ln));


            }


        }
    }
}
