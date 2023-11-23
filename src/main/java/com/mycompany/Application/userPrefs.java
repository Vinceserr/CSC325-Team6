package com.mycompany.Application;
import java.time.LocalTime;
import java.time.Duration;
public class userPrefs {
    private LocalTime startTime; //startTime is the earliest time the program will generate a study task.(Waking up)
    private LocalTime endTime; //endTime is the latest time the program will generate a study task.(Bed Time)
    private Duration studyLength; //studyLength is the maximum amount of time a study task will last.


    userPrefs(){
        this.startTime = null;
        this.endTime = null;
        this.studyLength = null;
    }
    public userPrefs(LocalTime startTime, LocalTime endTime, Duration studyLength) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.studyLength = studyLength;
    }
    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d", hours, minutes);
    }


    @Override
    public String toString() {
        return "Day duration: " + startTime + "-" + endTime + " Max Study Length:" + formatDuration(studyLength);
    }
}