package edu.tamu.richardcouperthwaite.writinglog.models;

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
}
