package com.mycompany.Application;

import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private String startTime;
    private String endTime;
    private String startDay;
    private String endDay;
    private String Color;
    private boolean loop;
    private String dayOfWeeks;

    public Event() {
    }

    public Event(String title, String startTime, String endTime, String startDay,
                 String endDay, String color, boolean loop, String dayOfWeeks) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDay = startDay;
        this.endDay = endDay;
        Color = color;
        this.loop = loop;
        this.dayOfWeeks =dayOfWeeks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public String getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(String dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }
    //transform the weeks to row number like MON to 1, TUE to 2
    public int weeksToRows(String week){

        if (week.equals("SUN")){
            return 0;
        }
        if(week.equals("MON")){
            return 1;
        }
        if(week.equals("TUE")){
            return 2;
        }
        if(week.equals("WED")){
            return 3;
        }
        if(week.equals("THU")){
            return 4;
        }
        if(week.equals("FRI")){
            return 5;
        }
        if(week.equals("SAT")){
            return 6;
        }

        return -1;
    }
}
