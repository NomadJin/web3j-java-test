package com.skcc.mydata.consentmanagement.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    final static String DATE_FORMAT = "yyyyMMddHHmmss";

    static public int convertYYYYMMDDHHMMSSToInteger(String dateString) throws ParseException {
        Date date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        //System.out.println(date);

        return (int) (date.getTime() / 1000);
    }

    static public Date convertIntegerToDate(int dateInt) {
        //long x = ((long)dateInt) * 1000;
        //System.out.println(x);
        //System.out.println(new Date(x));
        return new Date(((long)dateInt) * 1000);
    }


    static public String convertIntegerToYYYYMMDDHHMMSSKST(int dateInt) {
        Date date = new Date(((long)dateInt)*1000);
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String strDate = dateFormat.format(date);
        //System.out.println("Converted String: " + strDate);
        return strDate;
    }
}
