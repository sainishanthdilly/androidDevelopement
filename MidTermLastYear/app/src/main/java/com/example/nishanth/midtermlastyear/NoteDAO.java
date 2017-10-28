package com.example.nishanth.midtermlastyear;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishanth on 2/27/2017.
 */

public class NoteDAO {
    private SQLiteDatabase db;

    public NoteDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteTable.COLUMN_NAME,note.getName());
        contentValues.put(NoteTable.COLUMN_PRICE,note.getPrice());
        contentValues.put(NoteTable.COLUMN_THUMB_URL,note.getThumb_url());

        return db.insert(NoteTable.TABLENAME,null,contentValues);


    }
    public boolean update(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteTable.COLUMN_NAME,note.getName());
        contentValues.put(NoteTable.COLUMN_PRICE,note.getPrice());
        contentValues.put(NoteTable.COLUMN_THUMB_URL,note.getThumb_url());

        return  db.update(NoteTable.TABLENAME,contentValues, NoteTable.COLUMN_ID + "=?",
                new String[]{note.get_id()+""}) >0;



    }

    public boolean delete(Note note){
        return db.delete(NoteTable.TABLENAME, NoteTable.COLUMN_NAME +"=?",new String[]{note.getName()+""})>0;

    }

    public Note get(long id){

        Note note=null;

        Cursor c =db.query(true, NoteTable.TABLENAME, new String[]{NoteTable.COLUMN_ID+"", NoteTable.COLUMN_NAME,
        NoteTable.COLUMN_PRICE, NoteTable.COLUMN_THUMB_URL},
                NoteTable.COLUMN_ID+"=?" , new String[]{id+""},null,null,null,null);

        if(c!=null && c.moveToFirst()){

         note =   builtNoteFromCursor(c);

            if(!c.isClosed())
                c.close();

        }


        return note;
    }

    public List<Note> getALL(){

        List<Note> noteList= new ArrayList<Note>();

        Cursor c= db.query(NoteTable.TABLENAME,new String[]{NoteTable.COLUMN_ID+"", NoteTable.COLUMN_NAME,
                NoteTable.COLUMN_PRICE, NoteTable.COLUMN_THUMB_URL}, null,null,null,null,null);

        if(c!=null && c.moveToFirst()){

            do{

                Note note = builtNoteFromCursor(c);
                if(note!=null)
                noteList.add(note);


            }while (c.moveToNext());

            if(!c.isClosed())
                c.close();


        }

        return noteList;
    }

    private Note builtNoteFromCursor(Cursor c){

        Note note=null;
        if(c!=null){
            note = new Note();
            note.set_id(c.getLong(0));
            note.setName(c.getString(1));
            note.setPrice(c.getString(2));
            note.setThumb_url(c.getString(3));



        }




        return note;

    }








}
