package com.umpay.rms.gpd.internal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    public static final String dateFormat = "yyyyMMdd";
    public static final String timeFormat = "HHmmss";
    public static final String dateTimeFormat = "yyyyMMddHHmmss";
    public static final String dateTimeMsFormat = "yyyyMMddHHmmssSSS";


    public static String getDateTime( ) {
        return new SimpleDateFormat( dateTimeFormat ).format( Calendar.getInstance( ).getTime( ) );
    }

    public static String getDateTime( Date date ) {
        return new SimpleDateFormat( dateTimeFormat ).format( date );
    }

    public static String getDate( ) {
        return new SimpleDateFormat( dateFormat ).format( Calendar.getInstance( ).getTime( ) );
    }

    public static String getTime( ) {
        return new SimpleDateFormat( timeFormat ).format( Calendar.getInstance( ).getTime( ) );
    }

    public static String getDate( int nday ) {
        Date d = Calendar.getInstance( ).getTime( );
        return new SimpleDateFormat( dateFormat ).format( getDate( d, nday ) );
    }

    public static String getDate( String date, int nday ) {
        try {
            SimpleDateFormat f = new SimpleDateFormat( dateFormat );
            Date d = f.parse( date );
            return f.format( getDate( d, nday ) );
        } catch( ParseException e ) {
            return null;
        }
    }

    public static Date getDate( Date d, int nday ) {
        Calendar c = Calendar.getInstance( );
        c.setTime( d );
        c.add( Calendar.DAY_OF_YEAR, nday );
        return c.getTime( );
    }

    public static Date getDateBefore( int field, int before ) {
        Calendar c = Calendar.getInstance( );
        c.add( field, before * ( -1 ) );
        return c.getTime( );
    }

    public static Date getDateAfter( int field, int after ) {
        Calendar c = Calendar.getInstance( );
        c.add( field, after );
        return c.getTime( );
    }

    public static Date getDateBefore( Date d, int field, int before ) {
        Calendar c = Calendar.getInstance( );
        c.setTime( d );
        c.add( field, before * ( -1 ) );
        return c.getTime( );
    }

    public static Date getDateAfter( Date d, int field, int after ) {
        Calendar c = Calendar.getInstance( );
        c.setTime( d );
        c.add( field, after );
        return c.getTime( );
    }

    public static String getDate( String format ) {
        SimpleDateFormat sf = new SimpleDateFormat( format );
        return sf.format( Calendar.getInstance( ).getTime( ) );
    }

    public static String getDate( Date date ) {
        return new SimpleDateFormat( dateFormat ).format( date );
    }

    public static String getDate( Date date, String format ) {
        return new SimpleDateFormat( format ).format( date );
    }

    public static Date parseDate( String date, String format ) {
        SimpleDateFormat sf = new SimpleDateFormat( format );
        try {
            return sf.parse( date );
        } catch( ParseException e ) {
            return null;
        }
    }

    public static DateAndTime getDateAndTime( ) {
        return new DateAndTime( );
    }

    public static DateAndTime getDateAndTime( Date date ) {
        return new DateAndTime( date );
    }

    public static class DateAndTime {

        private final String date;
        private final String time;

        public DateAndTime( ) {
            this( Calendar.getInstance( ).getTime( ) );
        }

        public DateAndTime( Date d ) {
            String s = new SimpleDateFormat( dateTimeFormat ).format( d );
            date = s.substring( 0, 8 );
            time = s.substring( 8, 14 );
        }

        public String getDate( ) {
            return date;
        }

        public String getTime( ) {
            return time;
        }
    }

    public static void main( String[] args ) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat( dateTimeFormat );
        Date d = f.parse( "20131107021217" );
        Calendar c = Calendar.getInstance( );
        c.setTime( d );
        c.add( Calendar.SECOND, 303165 );
        System.out.println( DateTimeUtil.getDate( c.getTime( ), dateTimeFormat ) );

    }
}
