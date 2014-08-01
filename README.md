nrf-calendar
============

Provides an implementation of the National Retail Federation's 4-5-4 calendar.  As you can see, the code is written in Groovy.  A JAR can be downloaded from the dist directory.

Usage (Groovy):

```
NrfCalendar calendar = new NrfCalendar(2015)
DateRange fiscalOctober = calendar.getMonth(Calendar.OCTOBER)
assert fiscalOctober.dateFrom == Date.parse("MM-dd-yyyy", "10-04-2015")
assert fiscalOctober.dateTo == Date.parse("MM-dd-yyyy", "10-31-2015")
````
