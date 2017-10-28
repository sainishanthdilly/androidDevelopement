package com.example.nishanth.midtermlastyear;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by nishanth on 2/27/2017.
 */

public class NoteTable {
     static final String TABLENAME= "Filter";
     static final String COLUMN_ID ="_id";
     static final String COLUMN_NAME = "name";
     static final String COLUMN_PRICE = "price";
    static final String COLUMN_THUMB_URL = "thumb_url";


    static public void  onCreate(SQLiteDatabase db){
         StringBuilder sb = new StringBuilder();
        Log.d("demo", "Created");
         sb.append("CREATE TABLE "+TABLENAME+" (");
         sb.append(COLUMN_ID+" integer primary key autoincrement, ");
         sb.append(COLUMN_NAME + " text not null, ");
         sb.append(COLUMN_PRICE + " text not null, ");
         sb.append(COLUMN_THUMB_URL + " text not null ");

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
         NoteTable.onCreate(db);


     }



}
