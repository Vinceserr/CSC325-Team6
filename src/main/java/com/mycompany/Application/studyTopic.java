package com.mycompany.Application;

import java.time.Duration;

public class studyTopic {
    private String name;
    private Duration length;

    studyTopic(){
        this.name = null;
        this.length = null;
    }
    studyTopic(String name, Duration length){
        this.name = name;
        this.length = length;
    }
}
