package com.example.nishanth.thegamedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetGamesListAsync.SetupGames, GetGameIconAsync.SetUpGame {

    Button search,go;
    EditText enter_text;
    LinearLayout Lin;
    String url = "http://thegamesdb.net/api/GetGamesList.php";
    String method = "GET";
    String ParamName1 ="name";
    String gameURL= "http://thegamesdb.net/api/GetGame.php";
    ProgressDialog progress;
    ArrayList<GamesList> gamesListArrayList;
    static String ID ="ID";
    int counter=0;
    int pos;

    ArrayList<GameDataStructure> game_data_structure;
    ListView listView12;
    static boolean flag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("The Game DB");
        search = (Button) findViewById(R.id.buttonSearch);
       // go = (Button) findViewById(R.id.buttonGo);
        enter_text = (EditText) findViewById(R.id.editTextSearch);
       // Lin = (LinearLayout) findViewById(R.id.llinearSearch);
         progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Loading");
        //progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        game_data_structure = new ArrayList<GameDataStructure>();
        pos=0;
        flag = false;

        listView12  = (ListView) findViewById(R.id.ListView1);

        listView12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Demo","Item Selected , "+position );
                Intent intent = new Intent(MainActivity.this,GameDetailsActivity.class);
                intent.putExtra(MainActivity.ID,gamesListArrayList.get(position).getId());
                startActivity(intent);
                gamesListArrayList.clear();
                game_data_structure.clear();
            }
        });



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Lin.removeAllViews();
                String value = enter_text.getText().toString();
                if(value ==null || value.trim().length() ==0 )
                    Toast.makeText(getApplicationContext(),"Enter Search Input:",Toast.LENGTH_SHORT).show();
                else {
                    try {
                        progress.show();
                        RequestParams req = new RequestParams(url, method);
                        req.putParams(ParamName1, value);
                        new GetGamesListAsync(MainActivity.this).execute(req);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        });


   /*     go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup rg = (RadioGroup) Lin.getChildAt(0);
                for(int i=0;i< rg.getChildCount(); i++){
                    RadioButton rb = (RadioButton) rg.getChildAt(i);
                    if(rb.isChecked())
                    {
                        int x = gamesListArrayList.get(i).getId();
                        Intent intent = new Intent(MainActivity.this,GameDetailsActivity.class);
                        intent.putExtra(MainActivity.ID,x);
                        startActivity(intent);
                        Lin.removeAllViews();
                        enter_text.setText("");

                        break;
                    }

                }

            }
        });

*/



    }

    @Override
    public void setUpGamesData(ArrayList<GamesList> games_list) {


        gamesListArrayList = games_list;
      //  RadioGroup rg = new RadioGroup(this);


        for(GamesList game : games_list){


            RequestParams r = new RequestParams(gameURL,"GET");
            r.putParams("id",Integer.toString(game.getId()));
            new GetGameIconAsync(MainActivity.this).execute(r);

           // if(MainActivity.flag)
           //     break;




//            RadioButton rb = new RadioButton(this);

  //          rb.setText(game.getGameTitle()+"."+" Released in "+game.getReleaseDate()+"."+" Platform: "+game.getPlatform());

    //        rg.addView(rb);
        }
      //  Lin.addView(rg);
      //  go.setClickable(true);




    }

    @Override
    public void setGame(Game g) {


    if (g != null) {
        game_data_structure.add(new GameDataStructure(g.getImg(), gamesListArrayList.get(pos).getGameTitle() + "." + " Released in " + gamesListArrayList.get(pos).getReleaseDate() + "." + " Platform: " +
                "" + gamesListArrayList.get(pos).getPlatform()));

        pos++;
    }


    if (pos >= gamesListArrayList.size()) {

        MainActivity.flag = true;

        GameAdapter adapter = new GameAdapter(this, R.layout.row_layout_item, game_data_structure);
        listView12.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        progress.dismiss();

    }
}





}
