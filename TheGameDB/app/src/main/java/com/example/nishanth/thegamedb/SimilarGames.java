package com.example.nishanth.thegamedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SimilarGames extends AppCompatActivity implements GetSimilarGameAsync.SetupSMGame {


    Button simFin;

    String url = "http://thegamesdb.net/api/GetGame.php";
    int count;
    ProgressDialog pd;
    ArrayList<Integer> ids;
    TextView tx;
    ArrayList<String> sim;
    ListView slv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_games);
        count =0;

        getSupportActionBar().setTitle("Similar Games");
        slv = (ListView) findViewById(R.id.listViewForSimilar);
        pd = new ProgressDialog(this);
        pd.setTitle("Loading");
        pd.show();
        simFin = (Button) findViewById(R.id.buttonSimilarFin);



       // sLinear = (LinearLayout) findViewById(R.id.linearSimilar);
        ids = (ArrayList<Integer>) getIntent().getExtras().getIntegerArrayList("similargames");

        tx = (TextView) findViewById(R.id.textViewSimilarGamesTit);
        tx.setText("Similar games to "+getIntent().getExtras().getString("Title"));
        sim = new ArrayList<>();


        simFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SimilarGames.this,MainActivity.class);
                startActivity(i);
            }
        });


        slv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SimilarGames.this,GameSimilarDetailsDemo.class);
                intent.putExtra(MainActivity.ID,ids.get(position));
                startActivity(intent);
                finish();

            }
        });



        for(Integer id : ids){
            RequestParams req = new RequestParams(url,"GET");
            req.putParams("id",id.toString());
            new GetSimilarGameAsync(this).execute(req);




        }



    }


    @Override
    public void setUpGameData(GameSimilar game) {
        count++;
        if(count == ids.size()) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sim);
            slv.setAdapter(adapter);
            pd.dismiss();



        }
        //TextView tv = new TextView(this);
       // tv.setText(game.getGameTitle()+"."+" Released in "+game.getReleaseDate()+"."+" Platform: "+game.getPlatform());

        sim.add(game.getGameTitle()+"."+" Released in "+game.getReleaseDate()+"."+" Platform: "+game.getPlatform());
      //  sLinear.addView(tv);


    }
}
