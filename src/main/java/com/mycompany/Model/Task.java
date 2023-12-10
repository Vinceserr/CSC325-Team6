package com.mycompany.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
public class Task {
    private String title;
    private String startTime;
    private String endTime;
    private LocalDate startDay;
    private LocalDate endDay;
    private String repeat;
    private DayOfWeek[] weeks;

    public Task() {
    }

    public Task(String title, String startTime, String endTime,
                LocalDate startDay, LocalDate endDay, String repeat,
                DayOfWeek[] weeks) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDay = startDay;
        this.endDay = endDay;
        this.repeat = repeat;
        this.weeks = weeks;
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

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public DayOfWeek[] getWeeks() {
        return weeks;
    }

    public void setWeeks(DayOfWeek[] weeks) {
        this.weeks = weeks;
    }


}