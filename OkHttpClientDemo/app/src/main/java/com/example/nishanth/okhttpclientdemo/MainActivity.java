package com.example.nishanth.okhttpclientdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements GetITunesAsync.GetData, Button.OnClickListener, LoginTask.LinkData, SignupTask.LinkSignedInData {
    String url = "http://52.90.79.130:8080/Groups/";
    private final OkHttpClient client = new OkHttpClient();
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences pref;
    EditText usermail,password;
    Button signup,login;
    public static final String USERDATA="user";
    EditText usermailR,passwordR,fname,lname;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usermailR= (EditText) findViewById(R.id.email_signup);
        passwordR= (EditText) findViewById(R.id.password_choose);
        fname= (EditText) findViewById(R.id.first_name);
        lname= (EditText) findViewById(R.id.last_name);
        signup= (Button) findViewById(R.id.button_signup);

        usermail= (EditText) findViewById(R.id.Email);
        password= (EditText) findViewById(R.id.Password);
        login= (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        if(pref!=null) {
            if (pref.getString("token", "") != "" && pref.getString("token", "") != null) {
                Intent intent = new Intent(MainActivity.this, SubscriptionActivity.class);
                startActivity(intent);
            }
        }

        signup.setOnClickListener(this);


    }


    @Override
    public void setData(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.buttonLogin:
                if(usermail.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(this, "Please enter the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    new LoginTask(this).execute("http://52.90.79.130:8080/Groups/api/login",usermail.getText().toString(),password.getText().toString());

                }
                break;
            case R.id.button_signup:
                if(usermailR.getText().toString().equals("") || passwordR.getText().toString().equals(" ") || fname.getText().toString().equals("") || lname.getText().toString().equals("")){
                    Toast.makeText(this, "Please enter the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    new SignupTask(this).execute("http://52.90.79.130:8080/Groups/api/signUp",usermailR.getText().toString(),passwordR.getText().toString(),fname.getText().toString(),lname.getText().toString());

                }

        }

    }

    @Override
    public void printLoggedUserDetails(User user) {

        if(!user.getStatus().equalsIgnoreCase("0")) {

            Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            Log.d("UserDerails", user.toString());
            editor.putString("token", user.getData());
            editor.putString("FullName", user.getUserFname() + " " + user.getUserLname());
            editor.commit();
            Log.d("TOkenCSRF", pref.getString("token", "abcdef"));
            Intent intent = new Intent(MainActivity.this, SubscriptionActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Sorry Invalid Credentails", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void printSignedInUserDetails(User user) {


        Toast.makeText(getApplicationContext(),user.toString(), Toast.LENGTH_LONG).show();

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("token",user.getData());
        editor.putString("FullName",user.getUserFname()+" "+user.getUserLname());
        editor.commit();
        Log.d("TOkenCSRF",pref.getString("token","abcdef"));
        Intent intent=new Intent(MainActivity.this,SubscriptionActivity.class);
        startActivity(intent);



    }
}

