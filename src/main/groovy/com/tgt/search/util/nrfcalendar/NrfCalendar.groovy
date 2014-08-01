package com.tgt.search.util.nrfcalendar

class NrfCalendar {

  Calendar baseCalendar = Calendar.getInstance()

  Map<Integer, Integer> monthToWeekCount = [(Calendar.FEBRUARY): 4, (Calendar.MARCH): 5, (Calendar.APRIL): 4,
                                            (Calendar.MAY)     : 4, (Calendar.JUNE): 5, (Calendar.JULY): 4,
                                            (Calendar.AUGUST)  : 4, (Calendar.SEPTEMBER): 5, (Calendar.OCTOBER): 4,
                                            (Calendar.NOVEMBER): 4, (Calendar.DECEMBER): 5, (Calendar.JANUARY): 4]

  NrfCalendar(int year) {
    baseCalendar.set(Calendar.YEAR, year)
    baseCalendar.set(Calendar.MONTH, Calendar.FEBRUARY)
    snapToFirstWeek(baseCalendar)
  }

  DateRange getMonth(int calendarMonth) {
    Calendar monthMaker = Calendar.getInstance()
    monthMaker.set(Calendar.YEAR, baseCalendar.time.format("YYYY").toInteger())
    monthMaker.set(Calendar.MONTH, calendarMonth)
    snapToFirstWeek(monthMaker)
    return new DateRange(dateFrom: monthMaker.time, dateTo: (monthMaker.time + 7 * monthToWeekCount[calendarMonth]) - 1)
  }

  void snapToFirstWeek(Calendar calendar) {
    calendar.setMinimalDaysInFirstWeek(5)
    calendar.setFirstDayOfWeek(Calendar.SUNDAY)
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    calendar.set(Calendar.WEEK_OF_MONTH, 1)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
  }

  DateRange getWeek(int weekNumber) {
    Calendar weekMaker = baseCalendar.clone()
    weekMaker.add(Calendar.WEEK_OF_YEAR, weekNumber - 1)
    return new DateRange(dateFrom: weekMaker.time, dateTo: weekMaker.time + 6)
  }

  int getWeeksInMonth(int calendarMonth) {
    return monthToWeekCount[calendarMonth]
  }

  int getMonth(Date date) {
    Calendar monthPlacer = baseCalendar.clone()
    monthPlacer.time = date
    int week = monthPlacer.get(Calendar.WEEK_OF_MONTH)
    int month = monthPlacer.get(Calendar.MONTH)
    if (week > monthToWeekCount[monthPlacer.get(Calendar.MONTH)]) {
      month++
    } else if (week == 0) {
      month--
    }
    return month
  }

  int getWeekOfMonth(Date date) {
    Calendar weekPlacer = baseCalendar.clone()
    weekPlacer.time = date
    int week = weekPlacer.get(Calendar.WEEK_OF_MONTH)
    if (week > monthToWeekCount[weekPlacer.get(Calendar.MONTH)]) {
      week = 1
    } else if (week == 0) {
      week = monthToWeekCount[weekPlacer.get(Calendar.MONTH) - 1]
    }
    return week
  }
}