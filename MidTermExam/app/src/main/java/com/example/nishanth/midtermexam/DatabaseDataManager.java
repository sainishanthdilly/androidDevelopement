package com.example.nishanth.midtermexam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by nishanth on 2/27/2017.
 */

public class DatabaseDataManager {

    private Context mContext;
     DatabaseOpenHelper databaseOpenHelper;
     SQLiteDatabase db;
    private NoteDAO noteDAO;

    public DatabaseDataManager(Context mContext) {
        this.mContext = mContext;
        databaseOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = databaseOpenHelper.getWritableDatabase();
        noteDAO = new NoteDAO(db);
    }

    public void close(){
        if(db!=null)
            db.close();
    }

    public NoteDAO getNoteDAO() {
        return noteDAO;
    }

    public long saveNote(Note note){
        return this.noteDAO.save(note);

    }

    public boolean updateNote(Note note){
        return this.noteDAO.update(note);
    }

    public boolean deleteNote(Note note){

        return this.noteDAO.delete(note);
    }

    public Note getNote(long id){

        return this.noteDAO.get(id);
    }


    public List<Note> getALLNotes(){

        return this.noteDAO.getALL();
    }











}
