package com.mycompany.Application;

import java.time.Duration;

public class studyTopic {
    private String name;
    private Duration length;

    public studyTopic(){
        this.name = null;
        this.length = null;
    }

    /**
     * Constructor for the studyTopic class
     * @param name the name of the studyTopic
     * @param length the duration/length of time a week for a study topic
     */
    public studyTopic(String name, Duration length){
        this.name = name;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Topic name: " + name + " Duration: " + userPrefs.formatDuration(length);
    }
}
