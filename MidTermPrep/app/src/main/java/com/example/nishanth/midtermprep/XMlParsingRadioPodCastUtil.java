package com.example.nishanth.midtermprep;

import android.net.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nishanth on 3/9/2017.
 */

/*
Sai Nishanth Dilly
Shireen Shaik
Group 04
 */


public class XMlParsingRadioPodCastUtil {

    public static class XMLParsing{

        public static ArrayList<RadioDataPojo> radioDataPojoArrayList(InputStream is) throws XmlPullParserException, IOException, java.text.ParseException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is,"UTF-8");
            int event = parser.getEventType();
            ArrayList<RadioDataPojo> radioDataPojoArrayList = new ArrayList<>();
            boolean flag = false;
            RadioDataPojo radioDataPojo = null;

            while(event!= XmlPullParser.END_DOCUMENT){

                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item")){
                            radioDataPojo = new RadioDataPojo();
                        }

                        else if(parser.getName().equals("title")){
                            String val = parser.nextText();
                            if(radioDataPojo!=null)
                            radioDataPojo.setTitle(val);
                        }
                        else if(parser.getName().equals("description")){
                            String val = parser.nextText();
                            if(radioDataPojo!=null)
                                radioDataPojo.setDescription(val);
                        }
                        else if(parser.getName().equals("itunes:duration")){
                            String val = parser.nextText();
                            if(radioDataPojo!=null)
                                radioDataPojo.setDuration(val);
                        }

                        else if(parser.getName().equals("pubDate")){
                            String val = parser.nextText();
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
                            try {
                                Date dt = sdf.parse(val);
                                SimpleDateFormat s = new SimpleDateFormat("EEE, dd, MMM yyyy");
                                if(radioDataPojo!=null)
                                radioDataPojo.setDatePoted("posted: "+s.format(dt));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        else if(parser.getName().equals("itunes:image")){
                            String val = parser.getAttributeValue(0);
                            if(val != null ){
                                if(radioDataPojo!=null)
                                radioDataPojo.setImageUrl(val);
                            }
                        }
                        else if(parser.getName().equals("enclosure")){
                            String val = parser.getAttributeValue(0);
                            if(val != null){
                                if(radioDataPojo!=null)
                                radioDataPojo.setAudioUrl(val);
                            }
                        }


                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                           radioDataPojoArrayList.add(radioDataPojo);
                           radioDataPojo =null;
                        }

                    default:
                        break;

                }
                event = parser.next();


            }


            return radioDataPojoArrayList;


        }


    }

}
