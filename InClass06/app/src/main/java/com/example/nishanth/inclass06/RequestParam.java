package com.example.nishanth.inclass06;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;


/**
 * Created by nishanth on 2/13/2017.
 * Group 05
 * Sai Nishanth Dilly
 */

public class RequestParam {
    String method,baseURL;
    HashMap<String,String> params=new HashMap<String, String>();

    public RequestParam(String baseURL , String method ) {
        this.baseURL = baseURL;
        this.method = method;

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
        //if(getEncodedParams().length()!=0)
        //    return this.baseURL;
        return this.baseURL+"?"+getEncodedParams();
    }

    public HttpURLConnection setUpConnection() throws IOException
    {
        HttpURLConnection con=null;
        if(method.equals("GET")) {
            URL url = new URL(getEncodeURL());
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
