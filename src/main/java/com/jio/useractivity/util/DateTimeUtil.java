package com.jio.useractivity.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {

    /** The input date formatter used for string to date conversions */
    private SimpleDateFormat inputDateFormatter = null;

    public void setInputDateFormat(String inputFormat, TimeZone timezone) {
        inputDateFormatter = getSdf(inputFormat, timezone);
    }
    /**
     * Current implementation does not do any caching of date formatters. It is
     * left to the calling program to cache this object itself if required.
     *
     * @param format,
     *            date format to be used with formatter
     * @param tz,
     *            timezone to be set on date formatter
     * @return SimpleDateFormat object
     *
     */
    private SimpleDateFormat getSdf(String format, TimeZone tz) {
        if (format == null || tz == null)
            throw new NullPointerException("Invalid format or timezone");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        sdf.setTimeZone(tz);
        return sdf;
    }

    /**
     * Convert a date string in a specific timezone to Date object. Current
     * implementation uses SimpleDateFormat to do so.
     *
     * @param dateStr,
     *            date in string format to be converted to Date
     * @param sdf,
     *            formatter used for conversion
     */
    private Date stringToDate(String dateStr, SimpleDateFormat sdf)
            throws ParseException {
        if (dateStr == null)
            throw new NullPointerException("Invalid date string");
        return sdf.parse(dateStr);
    }
    /**
     * Convert a date string in a specific time zone to a date object. The
     * format and timezone must have been set prior to invoking this method
     * using their respective setters. This promotes reuse of the conversion
     * objects.
     *
     * @param dateStr,
     *            date in string format to be converted to Date
     */
    public synchronized Date stringToDate(String dateStr) throws ParseException {
        if (inputDateFormatter == null) {
            throw new NullPointerException("Input Date format not initialized");
        }
        return stringToDate(dateStr, inputDateFormatter);
    }

    /**
     * @param dateToConvert
     * @return
     */
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * @param dateToConvert
     * @return
     */
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


    /**
     * @param now
     * @param inputDate
     * @return return timedifference
     */
    public static Long getTimeDifference (Date now, Date inputDate, String type) {
        if(ChronoUnit.SECONDS.name().equalsIgnoreCase(type)) {
            LocalDateTime dateTimeBefore = DateTimeUtil.convertToLocalDateTimeViaInstant(inputDate);
            LocalDateTime dateTimeAfter = DateTimeUtil.convertToLocalDateTimeViaInstant(now);
            return ChronoUnit.SECONDS.between(dateTimeBefore, dateTimeAfter);
        } else if(ChronoUnit.MINUTES.name().equalsIgnoreCase(type)) {
            LocalDateTime dateTimeBefore = DateTimeUtil.convertToLocalDateTimeViaInstant(inputDate);
            LocalDateTime dateTimeAfter = DateTimeUtil.convertToLocalDateTimeViaInstant(now);
            return ChronoUnit.MINUTES.between(dateTimeBefore, dateTimeAfter);
        } else if(ChronoUnit.HOURS.name().equalsIgnoreCase(type)) {
            LocalDateTime dateTimeBefore = DateTimeUtil.convertToLocalDateTimeViaInstant(inputDate);
            LocalDateTime dateTimeAfter = DateTimeUtil.convertToLocalDateTimeViaInstant(now);
            return ChronoUnit.HOURS.between(dateTimeBefore, dateTimeAfter);
        } else if(ChronoUnit.DAYS.name().equalsIgnoreCase(type)) {
            LocalDate dateBefore = DateTimeUtil.convertToLocalDateViaInstant(inputDate);
            LocalDate dateAfter = DateTimeUtil.convertToLocalDateViaInstant(now);
            return ChronoUnit.DAYS.between(dateBefore, dateAfter);
        } else if(ChronoUnit.MONTHS.name().equalsIgnoreCase(type)) {
            LocalDate dateBefore = DateTimeUtil.convertToLocalDateViaInstant(inputDate);
            LocalDate dateAfter = DateTimeUtil.convertToLocalDateViaInstant(now);
            return ChronoUnit.MONTHS.between(dateBefore, dateAfter);
        } else {
            LocalDate dateBefore = DateTimeUtil.convertToLocalDateViaInstant(inputDate);
            LocalDate dateAfter = DateTimeUtil.convertToLocalDateViaInstant(now);
            return ChronoUnit.YEARS.between(dateBefore, dateAfter);
        }
    }
}
