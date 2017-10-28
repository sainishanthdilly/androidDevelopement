package com.example.nishanth.newapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by nishanth on 2/6/2017.
 */

public class RequestParam {
    String method,baseURL;
    HashMap<String,String> params=new HashMap<String, String>();

    public RequestParam(String method, String baseURL) {
        this.method = method;
        this.baseURL = baseURL;
    }

    public void addParams(String key,String value)
    {
        params.put(key,value);
    }

    public String getEncodedParams(){
        StringBuilder sb=new StringBuilder();
        for(String key: params.keySet())
        {
            try {
                String value= URLEncoder.encode(params.get(key),"UTF-8");
                if(sb.length()>0)
                {
                    sb.append("&");

                }
                sb.append(key+"="+value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }
    public String getEncodeURL()
    {
        return this.baseURL+"?"+getEncodedParams();
    }

    public HttpURLConnection setUpConnection() throws IOException
    {

        URL url= null;



            url = new URL(getEncodeURL());

            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");



        return con;
    }






}
