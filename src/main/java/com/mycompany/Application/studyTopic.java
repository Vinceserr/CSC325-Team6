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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getLength() {
        return length;
    }

    public void setLength(Duration length) {
        this.length = length;
    }

    /**
     * Returns the studyTopic object as a string
     * @return a representation of the studyTopic as a string
     */
    @Override
    public String toString() {
        return "Topic name: " + name + " Duration: " + userPrefs.formatDuration(length);
    }
}
