package com.example.nishanth.homework09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("testDatabase");

    DatabaseReference childref  = myRef.child("id 1").child("sendRequest");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        childref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot children :
                    dataSnapshot.getChildren()){



                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        LinkedHashMap<String,Integer> f= new LinkedHashMap<>();
        f.put("self",1);


        User_Table user_table = new User_Table("dff","email","fist","lzst","uri", f,f,f);
        myRef.child("id").setValue(user_table);


    }
}
