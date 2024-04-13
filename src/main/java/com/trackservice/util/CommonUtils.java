package com.trackservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static String convertDateToString(Date date){
        if (date == null ){
            return null;
        }
        String DATE_FORMAT_NOW = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String stringDate = sdf.format(date);
        return stringDate;
    }
}
