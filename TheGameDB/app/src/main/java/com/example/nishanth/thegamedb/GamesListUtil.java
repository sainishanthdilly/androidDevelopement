package com.example.nishanth.thegamedb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nishanth on 2/17/2017.
 */

public class GamesListUtil {
    public static class GamesListPullParser {

        public static ArrayList<GamesList> parseGames(InputStream is) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is, "UTF-8");
            int event = parser.getEventType();
            ArrayList<GamesList> gamesListArrayList = new ArrayList<>();
            GamesList game = null;
            int counter =0;


            while (event != XmlPullParser.END_DOCUMENT) {

                if(counter>=10)
                    break;

                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("Game")) {
                            game = new GamesList();
                        } else if (parser.getName().equals("id")) {
                            String val = parser.nextText();
                            game.setId(Integer.parseInt(val));


                        } else if (parser.getName().equals("GameTitle")) {
                            String val = parser.nextText();
                            game.setGameTitle(val);
                        } else if (parser.getName().equals("Platform")) {
                            String val = parser.nextText();
                            game.setPlatform(val);

                        } else if (parser.getName().equals("ReleaseDate")) {
                            String val = parser.nextText();
                            if (val == null || val.trim().length() == 0) {
                                game.setReleaseDate("");
                            } else {
                                SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
                                try {
                                    Date dt = sdf.parse(val);
                                    SimpleDateFormat s = new SimpleDateFormat("yyyy");
                                    game.setReleaseDate(s.format(dt));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                        }


                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("Game")) {
                            gamesListArrayList.add(game);
                            game = null;
                            counter++;
                        }

                        break;
                    default:
                        break;


                }
                event = parser.next();


            }


            return gamesListArrayList;

        }

    }

    public static class GamePullParser{

        public static Game parseGame(InputStream is) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is,"UTF-8");
            int event = parser.getEventType();
            Game game_data = new Game();
            String img="";

            while(event!= XmlPullParser.END_DOCUMENT){

                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("baseImgUrl")){
                            String val = parser.nextText().trim();
                            img=val;


                        }
                        else if(parser.getName().equals("thumb")){
                            if(game_data.getImg() == null) {
                                String val = parser.nextText().trim();
                                img = img + val;
                                Bitmap bm = BitmapFactory.decodeStream((InputStream) new URL(img).getContent());
                                game_data.setImg(bm);
                            }


                        }
                        else if(parser.getName().equals("Overview")){
                            String val = parser.nextText();
                            game_data.setOverView(val);
                        }
                        else if(parser.getName().equals("genre")){
                            String val = parser.nextText();
                            game_data.setGenre(val);
                        }
                        else if(parser.getName().equals("Publisher")){
                            String val = parser.nextText();
                            game_data.setPublisher(val);
                        }
                        else if(parser.getName().equals("Youtube")){
                            String val = parser.nextText();
                            game_data.setYoutubeVideo(val);
                        }
                        else if(parser.getName().equals("GameTitle")){
                            String val = parser.nextText();
                            game_data.setGame_name(val);
                        }
                        else if(parser.getName().equals("Similar")) {
                            String val;
                            ArrayList<Integer>  id=null ;
                            int temp=0;

                            while(true) {

                                if(event == XmlPullParser.START_TAG) {

                                    if (parser.getName().equals("SimilarCount")) {
                                        val = parser.nextText();
                                        game_data.setSimilar_count(Integer.parseInt(val));
                                        id = new ArrayList<Integer>(Integer.parseInt(val));
                                    }
                                    else if(parser.getName().equals("id")){
                                        val = parser.nextText();
                                        id.add(Integer.parseInt(val));


                                    }


                                }
                                else if(event == XmlPullParser.END_TAG && parser.getName().equals("Similar"))
                                {
                                    game_data.setSimilar_id(id);
                                    event = parser.next();
                                    break;
                                }


                                event = parser.next();
                            }

                        }
                        break;

                    default:
                        break;




                }
                event = parser.next();


            }


            return game_data ;

        }

    }



    public static class SimilarGamePullParser{

        public static GameSimilar parseGames(InputStream is) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is,"UTF-8");
            int event = parser.getEventType();
            GameSimilar game= null;
            boolean flag = false;

            while(event!= XmlPullParser.END_DOCUMENT){

                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("Game")){
                            game = new GameSimilar();
                        }

                        else if(parser.getName().equals("GameTitle")){
                            String val = parser.nextText();
                            game.setGameTitle(val);
                        }
                        else if(parser.getName().equals("Platform")){
                            String val = parser.nextText();
                            game.setPlatform(val);

                        }
                        else if(parser.getName().equals("ReleaseDate")){
                            String val = parser.nextText();
                            if(val == null || val.trim().length() ==0){
                                game.setReleaseDate("");
                            }

                            else{
                                SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
                                try {
                                    Date dt = sdf.parse(val);
                                    SimpleDateFormat s = new SimpleDateFormat("yyyy");
                                    game.setReleaseDate(s.format(dt));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            flag = true;

                        }
                        break;

                    default:
                        break;

                }
                event = parser.next();
                if(flag)
                    break;


            }


            return game;

        }

    }




    public static class GameIconPullParser{

        public static Game parseIconGame(InputStream is) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is,"UTF-8");
            int event = parser.getEventType();
            Game game_data = new Game();
            String img="";

            while(event!= XmlPullParser.END_DOCUMENT){

                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("baseImgUrl")){
                            String val = parser.nextText().trim();
                            img=val;


                        }
                        else if(parser.getName().equals("clearlogo")){
                            if(game_data.getImg() == null) {
                                String val = parser.nextText().trim();
                                img = img + val;
                                Bitmap bm = BitmapFactory.decodeStream((InputStream) new URL(img).getContent());
                                game_data.setImg(bm);
                            }


                        }
                        else if(parser.getName().equals("Overview")){
                            String val = parser.nextText();
                            game_data.setOverView(val);
                        }
                        else if(parser.getName().equals("genre")){
                            String val = parser.nextText();
                            game_data.setGenre(val);
                        }
                        else if(parser.getName().equals("Publisher")){
                            String val = parser.nextText();
                            game_data.setPublisher(val);
                        }
                        else if(parser.getName().equals("Youtube")){
                            String val = parser.nextText();
                            game_data.setYoutubeVideo(val);
                        }
                        else if(parser.getName().equals("GameTitle")){
                            String val = parser.nextText();
                            game_data.setGame_name(val);
                        }
                        else if(parser.getName().equals("Similar")) {
                            String val;
                            ArrayList<Integer>  id=null ;
                            int temp=0;

                            while(true) {

                                if(event == XmlPullParser.START_TAG) {

                                    if (parser.getName().equals("SimilarCount")) {
                                        val = parser.nextText();
                                        game_data.setSimilar_count(Integer.parseInt(val));
                                        id = new ArrayList<Integer>(Integer.parseInt(val));
                                    }
                                    else if(parser.getName().equals("id")){
                                        val = parser.nextText();
                                        id.add(Integer.parseInt(val));


                                    }


                                }
                                else if(event == XmlPullParser.END_TAG && parser.getName().equals("Similar"))
                                {
                                    game_data.setSimilar_id(id);
                                    event = parser.next();
                                    break;
                                }


                                event = parser.next();
                            }

                        }
                        break;

                    default:
                        break;




                }
                event = parser.next();


            }


            return game_data ;

        }

    }




}
