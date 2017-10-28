package com.example.nishanth.thegamedb;

 import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by nishanth on 2/17/2017.
 */

public class RequestParams {
    String baseURL,method;
    HashMap<String,String> params;

    public RequestParams(String baseURL, String method) {
        this.baseURL = baseURL;
        this.method = method;
        params = new HashMap<>();
    }

    public String getEncodedURL(){

        return baseURL+"?"+getEncodedParams();

    }

    public void putParams(String key, String value){
        params.put(key, value);
    }
    public String getEncodedParams(){
        StringBuilder sb =new StringBuilder();
        sb.append("");
        for(String key : params.keySet()){
            String value="";
            try {
                 value= URLEncoder.encode(params.get(key),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(sb.length() !=0)
                sb.append("&");
            sb.append(key+"="+value);

        }

        return sb.toString();
    }


    public HttpURLConnection setUpConnection() throws IOException
    {
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
