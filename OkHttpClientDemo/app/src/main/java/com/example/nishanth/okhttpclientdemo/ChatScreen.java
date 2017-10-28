package com.example.nishanth.okhttpclientdemo;


/**
 * Created by nishanth on 3/27/2017.
 */
/*
Shireen
Nishanth
Group 04
*/


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatScreen extends AppCompatActivity implements MessagesViewTask.MessageRetriever, View.OnClickListener,SendMsgTask.AddSendInData  {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences pref;
    TextView Fullname;
    ImageView logout;
    ListView messagesView;
    ImageView send,gallery;
    EditText inputMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        String s = getIntent().getExtras().getString("Chan");
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        pref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        send= (ImageView) findViewById(R.id.Send);
        logout= (ImageView) findViewById(R.id.logout);
        inputMsg= (EditText) findViewById(R.id.inputMsg);
        send.setOnClickListener(this);
        logout.setOnClickListener(this);
        Fullname= (TextView) findViewById(R.id.Fullname);
        Fullname.setText(s);
        messagesView= (ListView) findViewById(R.id.messagesView);
        new MessagesViewTask(this).execute("http://52.90.79.130:8080/Groups/api/get/messages",pref.getString("token",""));


    }

    @Override
    public void setMsgData(ArrayList<Message> messagelist) {
       if(messagelist!=null) {
           Log.d("ListSize", messagelist.size() + "");
           MessageAdapter adapter = new MessageAdapter(this, R.layout.textmsg_layout, messagelist);
           messagesView.setAdapter(adapter);
       }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Send:
                new SendMsgTask(this).execute("http://52.90.79.130:8080/Groups/api/post/message",inputMsg.getText().toString(),"Time","4",pref.getString("token",""));
                break;
            case R.id.logout:
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("token");
                editor.remove("FullName");
                editor.commit();
                Intent intent=new Intent(ChatScreen.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show();
                break;

        }


    }

    @Override
    public void getSentACK(AddMsg addMsg) {


        if(addMsg!=null) {
            Log.d("MSG", addMsg.toString());
            if (addMsg.getStatus().equalsIgnoreCase("ok")) {
                inputMsg.setText("");
                new MessagesViewTask(this).execute("http://52.90.79.130:8080/Groups/api/get/messages?channel_id=4", pref.getString("token", ""));
            } else {
                Toast.makeText(this, "ERROR IN SENDING MESSAGE", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
