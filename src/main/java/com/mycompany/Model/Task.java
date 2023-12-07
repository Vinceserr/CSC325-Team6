package com.mycompany.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate startDay;
    private LocalDate endDay;
    private String repeat;
    private ArrayList<DayOfWeek> weeks;

    public Task() {
    }

    public Task(String title, LocalTime startTime, LocalTime endTime,
                LocalDate startDay, LocalDate endDay, String repeat,
                ArrayList<DayOfWeek> weeks) {
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

    public ArrayList<DayOfWeek> getWeeks() {
        return weeks;
    }

    public void setWeeks(ArrayList<DayOfWeek> weeks) {
        this.weeks = weeks;
    }


}