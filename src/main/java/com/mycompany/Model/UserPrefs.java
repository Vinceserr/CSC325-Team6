package com.mycompany.Model;



import java.time.LocalTime;
import java.time.Duration;

public class UserPrefs {
    private LocalTime startTime; //startTime is the earliest time the program will generate a study task.(Waking up)
    private LocalTime endTime; //endTime is the latest time the program will generate a study task.(Bed Time)
    private String studyLength; //studyLength is the maximum amount of time a study task will last.

    UserPrefs(){
        this.startTime = null;
        this.endTime = null;
        this.studyLength = null;
    }
    public UserPrefs(LocalTime startTime, LocalTime endTime, String studyLength) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.studyLength = studyLength;
    }

    @Override
    public String toString() {
        return "Day duration: " + startTime + "-" + endTime + " Max Study Length:" + studyLength;
    }
}
