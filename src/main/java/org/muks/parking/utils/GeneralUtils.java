package org.muks.parking.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 300000511 on 12/10/17.
 */
public class GeneralUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static long getDuration(String inDateTime, String outDateTime) {
        Date d1 = null;
        Date d2 = null;
        long diffMinutes = 0l;

        try {
            d1 = format.parse(inDateTime);
            d2 = format.parse(outDateTime);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return diffMinutes;
    }


    public static void getDuration(String inDateTime, String outDateTime, SimpleDateFormat format) {
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(inDateTime);
            d2 = format.parse(outDateTime);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
