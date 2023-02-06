package com.inventorymanagement.rest.webservices.restfulwebservices.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getTodayDate() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        Date todayDate = today.getTime();
        return todayDate;
    }

    public static Date getCurrentMonthDate() {
        Calendar currentMonth = Calendar.getInstance();
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);
        currentMonth.set(Calendar.HOUR_OF_DAY, 0);
        currentMonth.set(Calendar.MINUTE, 0);
        currentMonth.set(Calendar.SECOND, 0);
        Date currentMonthDate = currentMonth.getTime();
        return currentMonthDate;
    }

    public static String getMonthNameByDate(Date date) {
        return new SimpleDateFormat("MMMM").format(date);
    }

    public static String getDayNameByDate(Date date) {
        return new SimpleDateFormat("EEEE").format(date);
    }
}
