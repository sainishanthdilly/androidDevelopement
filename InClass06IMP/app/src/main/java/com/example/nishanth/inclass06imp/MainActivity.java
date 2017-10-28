package com.example.nishanth.inclass06imp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * @author sai nishanth dilly
 */


public class MainActivity extends AppCompatActivity implements ButtonClickCallBack  {
    DatabaseDataManager dm;
    RecyclerView lv1;
    private static int _id = 1;
    ArrayList<NotesPojo> notesPojoArrayList;
    ArrayList<Note> notesArrayList;

    ShoppingAdapter ia;
    Spinner sp;
    EditText ed;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef ;//= database.getReference();

    Realm realm;

    ArrayList<Note> completed,pending;
    Button bt;
    static String COMPLETTED_STATUS ="COMPLETE" ;
    static String PENDING_STATUS ="PENDING";

    @Override
    public void onPlayButtonClick(int p) {

       //final String id = notesArrayList.get(p).get_id();
        final int p1 =p;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you really want to delete the task complete ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RealmResults<Note> results = realm.where(Note.class).equalTo("_id", notesArrayList.get(p1).get_id()).findAll();

                realm.beginTransaction();
                results.deleteFromRealm(0);
                //results.remove(0);
               realm.commitTransaction();
                notesArrayList.remove(p1);
                ia.notifyDataSetChanged();


                //myRef.child(id).removeValue();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();




    }

    @Override
    public void onCheckedClickCallBack(int p, String s) {


        Note editNote = realm.where(Note.class).equalTo("_id", notesArrayList.get(p).get_id()).findFirst();
        realm.beginTransaction();
        editNote.setStatus(s);
        realm.commitTransaction();
        //Note n = notesArrayList.get(p);
        //n.setStatus(s);
        notesArrayList.set(p,editNote);
         //   personDetailsModelArrayList.set(position, editPersonDetails);
        ia.notifyDataSetChanged();

    }

    enum Pro{
        h("High"),
        m("Medium"),
        l("Low");

        private final String text;

        /**
         * @param text
         */
        private Pro(final String text) {
            this.text = text;
        }

    }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_bar_items,menu);
            return true;
        }

    @Override
    protected void onStart() {
        super.onStart();

       RealmResults<Note> results = realm.where(Note.class).findAll();

        realm.beginTransaction();

        for (int i = 0; i < results.size(); i++) {
            notesArrayList.add(results.get(i));
        }

        realm.commitTransaction();

        ia.notifyDataSetChanged();



    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(MainActivity.this);
        realm = Realm.getDefaultInstance();

        lv1= (RecyclerView) findViewById(R.id.listview1);
        dm = new DatabaseDataManager(this);
        notesPojoArrayList = new ArrayList<>();
        notesArrayList = new ArrayList<>();
        ed = (EditText) findViewById(R.id.editTextNotes);
        bt = (Button) findViewById(R.id.buttonAdd);
        sp = (Spinner) findViewById(R.id.spinner2);

        ia = new ShoppingAdapter(this,this,notesArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lv1.setLayoutManager(mLayoutManager);

        lv1.setAdapter(ia);
        ia.notifyDataSetChanged();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesPojo notesPojo;
                notesPojo = new NotesPojo();
                ArrayList<NotesPojo> notesPojos = new ArrayList<NotesPojo>();
                String tx =ed.getText().toString();
                notesPojo.setNoteTask(tx);
                String pr =sp.getSelectedItem().toString();
                notesPojo.setPriority(pr);
                notesPojo.setPriSort(sp.getSelectedItemPosition());
                notesPojo.setTimeNote(Long.toString(System.currentTimeMillis()));


                if(realm.where(Note.class).findFirst()!= null &&  realm.where(Note.class).max("_id")!=null) {
                    _id = realm.where(Note.class).max("_id").intValue() + 1;
                }
                realm.beginTransaction();

                Note  note = realm.createObject(Note.class,_id);
                note.setNote(notesPojo.getNoteTask());
                note.setStatus(MainActivity.PENDING_STATUS);
                note.setTime(Long.toString(System.currentTimeMillis()));
                note.setPriority(Integer.toString(getPri(notesPojo.getPriority())));
                //note.setPty(getPri(notesPojo.getPriority()));


                notesArrayList.add(note);


                realm.commitTransaction();
                _id++;


                notesPojoArrayList.add(notesPojo);
                //notesPojoArrayList.clear();
                ia.notifyDataSetChanged();
                //notesPojoArrayList.addAll(notesPojos);
                ia.notifyDataSetChanged();

            }
        });


    }

    private int getPri(String priority) {

        if(priority.equalsIgnoreCase("High")){
            return 0;
        }
        else if(priority.equalsIgnoreCase("Medium")){
            return 1;
        }
        else{
            return 2;
        }

    }

    @Override
    protected void onDestroy() {
       notesArrayList.clear();
        realm.close();

        dm.close();
        super.onDestroy();
    }

    public void showall(MenuItem item){


        ia = new ShoppingAdapter(this,this,notesArrayList);
        ia.notifyDataSetChanged();
        //ia.setNotifyOnChange(true);
        lv1.setAdapter(ia);


    }
    public void showcomp(MenuItem item){

        completed = new ArrayList<>();

        for(int i=0; i <notesArrayList.size();i++ ){

            Note nt = notesArrayList.get(i);
            if(nt.getStatus().equalsIgnoreCase("COMPLETE"))
                completed.add(nt);
        }

        ia = new ShoppingAdapter(this,this,completed);
        ia.notifyDataSetChanged();
        lv1.setAdapter(ia);

    }

    public void showt(MenuItem item){

        RealmResults<Note> results = realm.where(Note.class).equalTo("status",PENDING_STATUS).findAll();

       // results = results.sort("time", Sort.DESCENDING);
        notesArrayList.clear();
        realm.beginTransaction();


        for (int i = 0; i < results.size(); i++) {
            notesArrayList.add(results.get(i));
        }

        realm.commitTransaction();

        RealmResults<Note> results1 = realm.where(Note.class).equalTo("status",COMPLETTED_STATUS).findAll();

      results1 = results1.sort("time", Sort.DESCENDING);
        realm.beginTransaction();

        for (int i = 0; i < results1.size(); i++) {
            notesArrayList.add(results1.get(i));
        }
        realm.commitTransaction();

        ia.notifyDataSetChanged();

    }

    public void showpen(MenuItem item){

        pending = new ArrayList<>();

        for(int i=0; i <notesArrayList.size();i++ ){

            Note nt = notesArrayList.get(i);
            if(nt.getStatus().equalsIgnoreCase("PENDING"))
                pending.add(nt);
        }

        ia = new ShoppingAdapter(this,this,pending);
        ia.notifyDataSetChanged();
        //ia.setNotifyOnChange(true);
        lv1.setAdapter(ia);

    }


    public void showpri(MenuItem item){

        RealmResults<Note> results = realm.where(Note.class).equalTo("status",PENDING_STATUS).findAll();

       // results = results.sort("priority", Sort.DESCENDING);
        notesArrayList.clear();
        realm.beginTransaction();

        for (int i = 0; i < results.size(); i++) {
            notesArrayList.add(results.get(i));
        }

        realm.commitTransaction();

        RealmResults<Note> results1 = realm.where(Note.class).equalTo("status",COMPLETTED_STATUS).findAll();
        results1 = results1.sort("priority", Sort.ASCENDING);
        realm.beginTransaction();
        for (int i = 0; i < results1.size(); i++) {
            notesArrayList.add(results1.get(i));
        }
        realm.commitTransaction();

        ia.notifyDataSetChanged();


    }


    }
