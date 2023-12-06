package com.mycompany.Application;

import java.time.*;
import java.util.Arrays;

public class Task {
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate startDay;
    private LocalDate endDay;
    private String repeat;
    private DayOfWeek[] weeks;

    public Task() {
    }

    public Task(String title, LocalTime startTime, LocalTime endTime,
                LocalDate startDay, LocalDate endDay, String repeat) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDay = startDay;
        this.endDay = endDay;
        this.repeat = repeat;
        this.weeks = new DayOfWeek[7];
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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

    public boolean isActivityOnDate(LocalDate date) {
        return !date.isBefore(startDay) && !date.isAfter(endDay);
    }
    public boolean isActivityOnWeek(DayOfWeek currentWeek){
        for(DayOfWeek taskWeek: weeks){
            if(currentWeek.equals(taskWeek)){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", repeat='" + repeat + '\'' +
                ", weeks=" + Arrays.toString(weeks) +
                '}';
    }
}
