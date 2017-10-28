package com.example.nishanth.saxparserdemo;

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
 */

public class PersonsUtil {

    public static class PersonSAXParser extends DefaultHandler{

        ArrayList<Person> personArrayList;
        StringBuilder xmlInnerText;
        Person person;
        public static ArrayList<Person> parsePerson(InputStream is) throws IOException, SAXException {
            PersonSAXParser parser = new PersonSAXParser();
            Xml.parse(is, Xml.Encoding.UTF_8,parser);

            return  parser.getPersonsList();

        }
        public ArrayList<Person> getPersonsList(){
            return personArrayList;
        }


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            personArrayList = new ArrayList<Person>();
            xmlInnerText = new StringBuilder();



        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnerText.append(ch,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("person")){
                personArrayList.add(person);
            }
            else if(localName.equals("name")){
                person.setName(xmlInnerText.toString().trim());
            }
            else if(localName.equals("age")){
                person.setAge(xmlInnerText.toString().trim());
            }
            else if(localName.equals("department")){
                person.setDepartment(xmlInnerText.toString().trim());
            }



            xmlInnerText.setLength(0);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("person")){
                person = new Person();
                person.setId(attributes.getValue("id"));
            }


        }
    }

public static class PersonPullParser{

        public static ArrayList<Person> parsePersons(InputStream is) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is,"UTF-8");
            ArrayList<Person> personArrayList = new ArrayList<>();
            Person person=null;
            int event = parser.getEventType();

            while(event!= XmlPullParser.END_DOCUMENT){

                switch (event){

                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("person")){
                            person = new Person();
                            person.setId(parser.getAttributeValue(null,"id"));

                        }
                        else if(parser.getName().equals("name")){
                            person.setName(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("age")){
                            person.setAge(parser.nextText().trim());
                        }
                        else if(parser.getName().equals("department")){
                            person.setDepartment(parser.nextText().trim());
                        }

                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("person")){

                            personArrayList.add(person);
                            person =null;
                        }

                        default:
                            break;


                }




                event = parser.next();
            }






            return  personArrayList;

        }
    }
}
