nrf-calendar
============

Provides a Java implementation of the National Retail Federation's 4-5-4 calendar.

Usage:

```
NrfCalendar calendar = new NrfCalendar(2015);
DateRange fiscalOctober = calendar.getMonth(Calendar.OCTOBER);
SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
assert fiscalOctober.getDateFrom() == format.parse("10-04-2015", new ParsePosition(0));
assert fiscalOctober.getDateTo() == format.parse("10-31-2015", new ParsePosition(0));
````

Compiling and packaging the source:
```
./gradlew jar
```
