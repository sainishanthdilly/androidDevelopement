package com.example.nishanth.inclass06;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by nishanth on 2/13/2017.
 * Group 05
 * Sai Nishanth Dilly
 */

public class ItemsUtil {

public static class PersonPullParser{

    public static ArrayList<Item> parsePersons(InputStream is) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is,"UTF-8");
            ArrayList<Item> itemArrayList = new ArrayList<>();
            Item item=null;
            int event = parser.getEventType();
            boolean found = false;
            while(event!= XmlPullParser.END_DOCUMENT){

                switch (event){

                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item")){
                            item = new Item();
                            found = true;

                        }
                        else if(parser.getName().equals("title")){

                            if(found)
                           item.setTitle(parser.nextText().toString().trim());


                        }
                        else if(parser.getName().equals("pubDate")){
                            if(found)
                           item.setPubDate(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("description")){
                            if(found)
                            item.setDescription(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("media:content")){
                            if(found)
                            {
                               String height=  parser.getAttributeValue(null,"height");
                                String width= parser.getAttributeValue(null,"width");
                                if(height.equalsIgnoreCase(width)){
                                    item.setImgUrl(parser.getAttributeValue(null,"url"));
                                }

                            }
                        }



                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){

                            itemArrayList.add(item);
                            item =null;
                        }

                        default:
                            break;


                }




                event = parser.next();
            }






            return  itemArrayList;

        }
    }
}
