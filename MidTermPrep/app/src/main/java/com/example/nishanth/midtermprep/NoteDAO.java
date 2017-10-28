package com.example.nishanth.midtermprep;

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
        contentValues.put(NotesTable.COLUMN_NOTE,note.getNote());
        contentValues.put(NotesTable.COLUMN_PRIORITY,note.getPriority());
        contentValues.put(NotesTable.COLUMN_TIME,note.getTime());
        contentValues.put(NotesTable.COLUMN_STATUS,note.getStatus());



        return db.insert(NotesTable.TABLENAME,null,contentValues);


    }
    public boolean update(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesTable.COLUMN_NOTE,note.getNote());
        contentValues.put(NotesTable.COLUMN_PRIORITY,note.getPriority());
        contentValues.put(NotesTable.COLUMN_TIME,note.getTime());
        contentValues.put(NotesTable.COLUMN_STATUS,note.getStatus());

        return  db.update(NotesTable.TABLENAME,contentValues,NotesTable.COLUMN_ID + "=?",
                new String[]{note.get_id()+""}) >0;



    }

    public boolean delete(Note note){
        return db.delete(NotesTable.TABLENAME,NotesTable.COLUMN_ID +"=?",new String[]{note.get_id()+""})>0;

    }

    public Note get(long id){

        Note note=null;

        Cursor c =db.query(true,NotesTable.TABLENAME, new String[]{NotesTable.COLUMN_ID+"",NotesTable.COLUMN_NOTE,
        NotesTable.COLUMN_PRIORITY,NotesTable.COLUMN_TIME,NotesTable.COLUMN_STATUS}, NotesTable.COLUMN_ID+"=?" , new String[]{id+""},null,null,null,null);

        if(c!=null && c.moveToFirst()){

         note =   builtNoteFromCursor(c);

            if(!c.isClosed())
                c.close();

        }


        return note;
    }

    public List<Note> getALL(){

        List<Note> noteList= new ArrayList<Note>();

        Cursor c= db.query(NotesTable.TABLENAME,new String[]{NotesTable.COLUMN_ID+"",NotesTable.COLUMN_NOTE,
                NotesTable.COLUMN_PRIORITY,NotesTable.COLUMN_TIME,NotesTable.COLUMN_STATUS}, null,null,null,null,null);

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
            note.setNote(c.getString(1));
            note.setPriority(c.getString(2));
            note.setTime(c.getString(3));
            note.setStatus(c.getString(4));



        }




        return note;

    }








}
