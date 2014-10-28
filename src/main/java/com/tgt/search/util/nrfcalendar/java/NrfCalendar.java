package com.tgt.search.util.nrfcalendar.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NrfCalendar {
    private Calendar baseCalendar = Calendar.getInstance();
    private Map<Integer, Integer> monthToWeekCount = new HashMap<>();
    private static final DateFormat FORMAT_YEAR = new SimpleDateFormat("YYYY");

    public NrfCalendar(int year) {
        initMonthToWeekCount();
        baseCalendar.set(Calendar.YEAR, year);
        baseCalendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        snapToFirstWeek(baseCalendar);
    }

    private void initMonthToWeekCount() {
        monthToWeekCount.put(Calendar.FEBRUARY, 4);
        monthToWeekCount.put(Calendar.MARCH, 5);
        monthToWeekCount.put(Calendar.APRIL, 4);
        monthToWeekCount.put(Calendar.MAY, 4);
        monthToWeekCount.put(Calendar.JUNE, 5);
        monthToWeekCount.put(Calendar.JULY, 4);
        monthToWeekCount.put(Calendar.AUGUST, 4);
        monthToWeekCount.put(Calendar.SEPTEMBER, 5);
        monthToWeekCount.put(Calendar.OCTOBER, 4);
        monthToWeekCount.put(Calendar.NOVEMBER, 4);
        monthToWeekCount.put(Calendar.DECEMBER, 5);
        monthToWeekCount.put(Calendar.JANUARY, 4);
    }

    void snapToFirstWeek(Calendar calendar) {
        calendar.setMinimalDaysInFirstWeek(5);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.WEEK_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public DateRange getMonth(int calendarMonth) {
        Calendar monthMaker = Calendar.getInstance();
        monthMaker.set(Calendar.YEAR, Integer.parseInt(FORMAT_YEAR.format(baseCalendar.getTime())));
        monthMaker.set(Calendar.MONTH, calendarMonth);
        snapToFirstWeek(monthMaker);
        Date dateFrom = monthMaker.getTime();
        monthMaker.add(Calendar.DAY_OF_MONTH, 7 * monthToWeekCount.get(calendarMonth));
        monthMaker.add(Calendar.DAY_OF_MONTH, -1);
        Date dateTo = monthMaker.getTime();
        return new DateRange(dateFrom, dateTo);
    }

    public DateRange getWeek(int weekNumber) {
        Calendar weekMaker = (Calendar) baseCalendar.clone();
        weekMaker.add(Calendar.WEEK_OF_YEAR, weekNumber - 1);
        Date dateFrom = weekMaker.getTime();
        weekMaker.add(Calendar.DAY_OF_MONTH, 6);
        Date dateTo = weekMaker.getTime();
        return new DateRange(dateFrom, dateTo);
    }

    public int getWeeksInMonth(int calendarMonth) {
        return monthToWeekCount.get(calendarMonth);
    }

    public int getMonth(Date date) {
        Calendar monthPlacer = (Calendar) baseCalendar.clone();
        monthPlacer.setTime(date);
        int week = monthPlacer.get(Calendar.WEEK_OF_MONTH);
        int month = monthPlacer.get(Calendar.MONTH);
        if (week > monthToWeekCount.get(monthPlacer.get(Calendar.MONTH))) {
            month++;
        } else if (week == 0) {
            month--;
        }
        return month;
    }

    public int getWeekOfMonth(Date date) {
        Calendar weekPlacer = (Calendar) baseCalendar.clone();
        weekPlacer.setTime(date);
        int week = weekPlacer.get(Calendar.WEEK_OF_MONTH);
        if (week > monthToWeekCount.get(weekPlacer.get(Calendar.MONTH))) {
            week = 1;
        } else if (week == 0) {
            week = monthToWeekCount.get(weekPlacer.get(Calendar.MONTH) - 1);
        }
        return week;
    }
}
