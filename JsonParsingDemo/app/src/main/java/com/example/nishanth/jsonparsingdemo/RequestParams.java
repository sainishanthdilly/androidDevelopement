package com.example.nishanth.jsonparsingdemo;

import android.support.annotation.VisibleForTesting;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by nishanth on 2/10/2017.
 */

public class RequestParams{
    String method,baseURL;
    HashMap<String,String> mp = new HashMap<String, String>();

    public RequestParams(String baseURL, String method) {
        this.baseURL = baseURL;
        this.method = method;
    }

    public void addParams(String key, String value){
        mp.put(key, value);

    }

    public String getEncodedParams(){
        StringBuilder sb = new StringBuilder();

        for(String key :  mp.keySet()){
            if(sb.length() !=0){
                sb.append("&");
            }
            try {
                sb.append(key+"="+ URLEncoder.encode(mp.get(key),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();

    }

    public String getEncodedURL(){
        return baseURL+"?"+getEncodedParams();
    }

    public HttpURLConnection setUpConnection() throws Exception {
        HttpURLConnection con=null;
        if(method.equals("GET")) {
            URL url = new URL(getEncodedURL());
            con = (HttpURLConnection) url.openConnection();






        }
        else{

            URL url = new URL(baseURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStream out = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out);
            osw.write(getEncodedParams());
            osw.flush();






        }
        return con;

    }






}
