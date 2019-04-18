package com.logicworms.roundedprogressbar.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class TimeUtils {

    public static int getDifferenceFromTargetTime(String orderDate, String orderTime) {
        String time = orderDate.trim() + " " + orderTime.trim();
        DateFormat format = new SimpleDateFormat("dd/MMM/yyyy hh:mm a", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return (int) ((date.getTime() - System.currentTimeMillis()) / 1000);
    }

    public static String formatTime(int difference) {
        int hours = difference / 3600;
        int remainder = difference - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        String formattedHours = getHours(hours);
        String formattedMins = getMinutes(mins);

        Formatter formatter = new Formatter();
        String formatted;
        if (mins < 0) {
            formatted = formatter.format("%s late", formattedMins).toString();
        } else {
            if (hours > 0)
                formatted = formatter.format("%s and %s left to fulfill order", formattedHours, formattedMins).toString();
            else formatted = formatter.format("%s left to fulfill order", formattedMins).toString();
        }
        return formatted;
    }

    private static String getHours(int hours) {
        Formatter formatter = new Formatter();
        if (hours <= 0)
            return "";
        if (hours > 1) {
            return formatter.format("%s hours", hours).toString();
        } else return formatter.format("%s hour", hours).toString();
    }

    private static String getMinutes(int mins) {
        Formatter formatter = new Formatter();
        String min = (mins == 0 || Math.abs(mins) == 1) ? "min" : "mins";
        if (mins > 1) {
            return formatter.format("%s %s", mins, min).toString();
        } else if (mins == 1) return formatter.format("%s %s", mins, min).toString();
        else return formatter.format("%s %s", Math.abs(mins), min).toString();
    }
}
