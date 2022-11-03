package com.example.webforum.util;


import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date createDateFromDateString(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if (StringUtils.hasText(dateString)) {
            try {
                date = format.parse(dateString);
            } catch (ParseException e) {
                date = new Date();
            }
        }else {
            date = new Date();
        }
        return date;
    }
}
