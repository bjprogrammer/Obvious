package com.application.obvious.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HelperFunctions {
    /**
     * Formatting date format from long
     */
    @SuppressLint("SimpleDateFormat")
    public static String convertLongToDate(long time, int dateFormat) {
        String dateString = "";
        if (dateFormat == 1) {
            dateString = new SimpleDateFormat("MM/dd/yyyy")
                    .format(new Date(time));
        }
        if (dateFormat == 2) {
            dateString = new SimpleDateFormat("dd/MM/yyyy HH:mm")
                    .format(new Date(time));
        }
        if (dateFormat == 3) {
            dateString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(time));
        }
        if (dateFormat == 4) {
            dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
        }
        if (dateFormat == 5) {
            dateString = new SimpleDateFormat("dd-MM-yyyy").format(new Date(time));
        }
        if (dateFormat == 10) {
            dateString = new SimpleDateFormat("yyyy-dd-MM HH:mm").format(new Date(time));
        }
        if (dateFormat == 12) {
            dateString = new SimpleDateFormat("dd MMMM yyyy, hh:mm a").format(new Date(time));
        }
        return dateString;
    }

    //Changing date format from  yyyy-MM-dd to dd-MM-yyyy
    public static String formatDate(String timestamp)  {
        try {
            timestamp = timestamp.substring(0, 10);
            @SuppressLint("SimpleDateFormat") Date lastvisit = new SimpleDateFormat("yyyy-MM-dd").parse(timestamp);
            @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            timestamp = df.format(lastvisit);
        }catch (Exception e){}
        return timestamp;
    }

    public static long convertDateStringTolong(String DateTimeString, int DateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        if (DateFormat == 1)
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        else if (DateFormat == 2)
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        else if (DateFormat == 3)
            sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        else if (DateFormat == 4)//12-01-2016 17:55:40
            sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        else if (DateFormat == 5)
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        else if (DateFormat == 6) {
            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else if (DateFormat == 7) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (DateFormat == 8) {
            sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        } else if (DateFormat == 9) {
            sdf = new SimpleDateFormat("dd-MM-yyyy");
        } else if (DateFormat == 10) {
            sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm");
        } else if (DateFormat == 11) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (DateFormat == 12) {
            sdf = new SimpleDateFormat("dd MM yyyy hh:mm a");
        }else if (DateFormat == 13) {
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
        }else if (DateFormat == 14) {
            sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        }
        else if(DateFormat == 15) {
            sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        }else if(DateFormat==16){
            sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        }
        Date date = null;
        try {
            date = sdf.parse(DateTimeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return 0;
        }
        return date.getTime();

    }


    //Converting any given string to camel case
    public static String toCamelCase(final String init) {
        if (init == null)
            return null;

        final StringBuilder ret = new StringBuilder(init.length());

        for (final String word : init.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(Character.toUpperCase(word.charAt(0)));
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length() == init.length()))
                ret.append(" ");
        }

        return ret.toString();
    }


    //Converting json file (stored in raw folder) in string format
    public static String fetchData(Context context){

        String jsonString= null;
        JSONObject JSONObject = null;
        try {

            InputStream inputStream = context.getAssets().open("data.json");
            int sizeOfJSONFile = inputStream.available();
            byte[] bytes = new byte[sizeOfJSONFile];
            inputStream.read(bytes);
            inputStream.close();
            jsonString = new String(bytes, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonString;
    }
}
