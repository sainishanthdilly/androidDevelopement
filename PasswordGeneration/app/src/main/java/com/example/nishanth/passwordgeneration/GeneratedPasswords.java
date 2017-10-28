package com.example.nishanth.passwordgeneration;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class GeneratedPasswords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_passwords);

        ArrayList<String> ls = (ArrayList<String>) getIntent().getExtras().get(MainActivity.pwd);
        ArrayList<String> lst = (ArrayList<String>) getIntent().getExtras().get(MainActivity.pwdT);




        Toast.makeText(getApplicationContext(),ls.toString(),Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(),lst.toString(),Toast.LENGTH_SHORT).show();



        LinearLayout l1 = (LinearLayout) findViewById(R.id.ln1);
        LinearLayout l2 = (LinearLayout) findViewById(R.id.ln1);

        for(int i=0;i<lst.size();i++) {
            TextView t1 = new TextView(this);
            t1.setText(ls.get(i));

            l1.addView(t1);
        }
        for (int i=0;i<lst.size();i++)
        {
            TextView t1 = new TextView(this);
            t1.setText(ls.get(i));

            l2.addView(t1);
        }



        Button f = (Button) findViewById(R.id.finish);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
