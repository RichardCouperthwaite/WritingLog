package edu.tamu.richardcouperthwaite.writinglog.models;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

public class Stats {
    private int dayscurrentweek;
    private int daysprevweek;
    private int dayscurrentmonth;
    private int daysprevmonth;
    private float timescurrentweek;
    private float timesprevweek;
    private float timescurrentmonth;
    private float timesprevmonth;

    public Stats(int dayscurrentweek, int daysprevweek, int dayscurrentmonth, int daysprevmonth, float timescurrentweek, float timesprevweek, float timescurrentmonth, float timesprevmonth) {
        this.dayscurrentweek = dayscurrentweek;
        this.daysprevweek = daysprevweek;
        this.dayscurrentmonth = dayscurrentmonth;
        this.daysprevmonth = daysprevmonth;
        this.timescurrentweek = timescurrentweek;
        this.timesprevweek = timesprevweek;
        this.timescurrentmonth = timescurrentmonth;
        this.timesprevmonth = timesprevmonth;
    }

    public int getDayscurrentweek() {
        return dayscurrentweek;
    }

    public void setDayscurrentweek(int dayscurrentweek) {
        this.dayscurrentweek = dayscurrentweek;
    }

    public int getDaysprevweek() {
        return daysprevweek;
    }

    public void setDaysprevweek(int daysprevweek) {
        this.daysprevweek = daysprevweek;
    }

    public int getDayscurrentmonth() {
        return dayscurrentmonth;
    }

    public void setDayscurrentmonth(int dayscurrentmonth) {
        this.dayscurrentmonth = dayscurrentmonth;
    }

    public int getDaysprevmonth() {
        return daysprevmonth;
    }

    public void setDaysprevmonth(int daysprevmonth) {
        this.daysprevmonth = daysprevmonth;
    }

    public float getTimescurrentweek() {
        return timescurrentweek;
    }

    public void setTimescurrentweek(float timescurrentweek) {
        this.timescurrentweek = timescurrentweek;
    }

    public float getTimesprevweek() {
        return timesprevweek;
    }

    public void setTimesprevweek(float timesprevweek) {
        this.timesprevweek = timesprevweek;
    }

    public float getTimescurrentmonth() {
        return timescurrentmonth;
    }

    public void setTimescurrentmonth(float timescurrentmonth) {
        this.timescurrentmonth = timescurrentmonth;
    }

    public float getTimesprevmonth() {
        return timesprevmonth;
    }

    public void setTimesprevmonth(float timesprevmonth) {
        this.timesprevmonth = timesprevmonth;
    }

    public static ArrayList<Integer> getDayIndices(String date, String weekstart, String monthstart) {
        ArrayList<Integer> days = new ArrayList<Integer>();
        String current[] = date.split("/");
        String week[] = weekstart.split("/");
        String month[] = monthstart.split("/");
        if (current[0].equals(month[0])) {
            days.add(0);
            days.add(Integer.getInteger(current[1])-1);
        } else {
            days.add(1);
            days.add(Integer.getInteger(current[1])-1);
        }

        Calendar calendar = GregorianCalendar.getInstance();
        Calendar calendar2 = GregorianCalendar.getInstance();
        calendar.set(2018,0,1);
        calendar2.set(2018,0,8);
        int Daydif = calendar2.get(Calendar.DAY_OF_YEAR)- calendar.get(Calendar.DAY_OF_YEAR);
        int DIM = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

        return days;
    }
}
