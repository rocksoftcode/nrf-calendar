package com.tgt.search.util.nrfcalendar

import spock.lang.Specification

class NrfCalendarSpec extends Specification {

  def "Initializes to beginning of NRF calendar year"() {
    setup:
    NrfCalendar nrfCalendar = new NrfCalendar(2014)

    expect:
    Date.parse("MM-dd-yyyy", "02-02-2014") == nrfCalendar.baseCalendar.time
  }

  def "Gets date range for week"() {
    setup:
    NrfCalendar nrfCalendar = new NrfCalendar(2014)

    when:
    DateRange range = nrfCalendar.getWeek(42)

    then:
    range.dateFrom == Date.parse("MM-dd-yyyy", "11-16-2014")
    range.dateTo == Date.parse("MM-dd-yyyy", "11-22-2014")
  }

  def "Gets date range for month"() {
    setup:
    NrfCalendar nrfCalendar = new NrfCalendar(2014)

    when:
    DateRange range = nrfCalendar.getMonth(Calendar.JUNE)

    then:
    range.dateFrom == Date.parse("MM-dd-yyyy", "06-01-2014")
    range.dateTo == Date.parse("MM-dd-yyyy", "07-05-2014")

    when:
    range = nrfCalendar.getMonth(Calendar.OCTOBER)

    then:
    range.dateFrom == Date.parse("MM-dd-yyyy", "10-05-2014")
    range.dateTo == Date.parse("MM-dd-yyyy", "11-01-2014")
  }

  def "Gets date range for months in 2015"() {
    setup:
    NrfCalendar nrfCalendar = new NrfCalendar(2015)

    when:
    DateRange range = nrfCalendar.getMonth(Calendar.DECEMBER)

    then:
    range.dateFrom == Date.parse("MM-dd-yyyy", "11-29-2015")
    range.dateTo == Date.parse("MM-dd-yyyy", "01-02-2016")
  }

  def "Gets weeks in month"() {
    setup:
    NrfCalendar nrfCalendar = new NrfCalendar(2014)

    expect:
    nrfCalendar.getWeeksInMonth(Calendar.JANUARY) == nrfCalendar.monthToWeekCount[Calendar.JANUARY]
    nrfCalendar.getWeeksInMonth(Calendar.SEPTEMBER) == nrfCalendar.monthToWeekCount[Calendar.SEPTEMBER]
    nrfCalendar.getWeeksInMonth(Calendar.NOVEMBER) == nrfCalendar.monthToWeekCount[Calendar.NOVEMBER]
  }

}
