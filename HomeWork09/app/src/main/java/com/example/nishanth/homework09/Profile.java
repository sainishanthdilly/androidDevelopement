package com.example.nishanth.homework09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Profile extends AppCompatActivity implements ButtonClickCallBack{

    static  final String SENDREQUEST="SEND REQUEST";
    static final String FRIENDREQUESTSENT ="FRIEND REQUEST JUST SENT";
    static  final String AcceptREQUEST="ACCEPT REQUEST";
    static  final String REMOVE="REMOVE FRIEND";
    static  final String FRIENDS = "FRIENDS";

    List<User_Table> searchList;

    ImageButton trips_button;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users_Table");
    RecyclerView recyclerView;
    List<User_Table> userTableList;
    DisPeopleAdapter disPeopleAdapter;
    //DatabaseReference myRefV = database.getReference("Friend_Table_List");
    //DatabaseReference myRefF;
    ImageButton search;
    EditText searchEdit;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    HashMap<String,Integer> friendsReqSentList;
    HashMap<String,Integer> friendsAcceptList;
    HashMap<String,Integer> friendsList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar_items,menu);
        return true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        trips_button= (ImageButton) findViewById(R.id.trip_button);
        searchEdit = (EditText) findViewById(R.id.editTextSearchPP);
        searchList = new ArrayList<>();
        //myRefF = myRefV.child(user.getUid());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerDiscover1);

        trips_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Profile.this,AddChatroom.class);
                startActivity(i);
            }
        });


        search = (ImageButton) findViewById(R.id.imageButtonDiscPPL);

        userTableList = new ArrayList<>();
        friendsReqSentList = new HashMap<>();
        friendsAcceptList =  new HashMap<>();
        friendsList = new HashMap<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);

        disPeopleAdapter = new DisPeopleAdapter(this,userTableList);
        disPeopleAdapter.setButtonClickCallBack(this);
        recyclerView.setAdapter(disPeopleAdapter);
        disPeopleAdapter.notifyDataSetChanged();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userTableList.clear();
                for(DataSnapshot children: dataSnapshot.getChildren()){
                    if(!user.getUid().equals(children.getKey())) {
                        User_Table ut = children.getValue(User_Table.class);
                        userTableList.add(ut);
                    }
                    else{
                        disPeopleAdapter.setCurrent_user(children.getValue(User_Table.class));
                    }
                }
                disPeopleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchList.clear();
                String s1 = searchEdit.getText().toString();

                if(s1.trim().length() == 0){
                    disPeopleAdapter.setUsersTableList(userTableList);
                }
                else {
                    for (User_Table user : userTableList) {
                        if (user.getLast_name().contains(s1) || user.getFirst_name().contains(s1)) {
                            searchList.add(user);
                        }

                    }

                    disPeopleAdapter.setUsersTableList(searchList);
                }

            }
        });

    }

    @Override
    public void onButtonClickSetData(int adapterPosition, Object tag) {
        String tag1 = (String) tag;

        if(tag1.equals(SENDREQUEST)){

            myRef.child(user.getUid()).child("sentRequests").child(userTableList.get(adapterPosition).getUser_id()).setValue(1);
            myRef.child(userTableList.get(adapterPosition).getUser_id()).child("acceptRequests").child(user.getUid()).setValue(1);

        }
        else if(tag1.equalsIgnoreCase(AcceptREQUEST)){

            myRef.child(user.getUid()).child("acceptRequests").child(userTableList.get(adapterPosition).getUser_id()).removeValue();
            myRef.child(userTableList.get(adapterPosition).getUser_id()).child("sentRequests").child(user.getUid()).removeValue();
            myRef.child(user.getUid()).child("friends").child(userTableList.get(adapterPosition).getUser_id()).setValue(1);
            myRef.child(userTableList.get(adapterPosition).getUser_id()).child("friends").child(user.getUid()).setValue(1);

        }

    }



    public void updateProfile(MenuItem item){
        Intent i = new Intent(Profile.this,ProfileUpdateActivity.class);
        startActivity(i);



    }

    public void logout(MenuItem item){

        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(Profile.this,LoginActivity.class);
        startActivity(i);
    }





}
