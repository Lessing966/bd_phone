package com.example.making.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private  final static DateUtils DATA = new DateUtils();

    private DateUtils(){

    }

    public static DateUtils getDateUtils(){
        return DATA;
    }

    public static String getStringDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
