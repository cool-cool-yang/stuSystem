package com.stuSystem;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try{
            String s = "2020-05-10";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(s);
            System.out.println(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
}
