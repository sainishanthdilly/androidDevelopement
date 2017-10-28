package com.example.nishanth.midtermprep;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by nishanth on 2/27/2017.
 */

public class NotesTable {
     static final String TABLENAME= "notes";
     static final String COLUMN_ID ="_id";
     static final String COLUMN_NOTE = "note";
     static final String COLUMN_PRIORITY = "priority";
    static final String COLUMN_TIME = "time";
    static final String COLUMN_STATUS = "status";


    static public void  onCreate(SQLiteDatabase db){
         StringBuilder sb = new StringBuilder();
        Log.d("demo", "Created");
         sb.append("CREATE TABLE "+TABLENAME+" (");
         sb.append(COLUMN_ID+" integer primary key autoincrement, ");
         sb.append(COLUMN_NOTE + " text not null, ");
         sb.append(COLUMN_PRIORITY + " text not null, ");
         sb.append(COLUMN_TIME + " text not null, ");
         sb.append(COLUMN_STATUS + " text not null");


        sb.append(");");
         try {
             db.execSQL(sb.toString());
         }
         catch (SQLException e){
             e.printStackTrace();

         }




     }

     static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
         db.execSQL("DROP TABLE IF EXISTS "+TABLENAME );
         NotesTable.onCreate(db);


     }



}
