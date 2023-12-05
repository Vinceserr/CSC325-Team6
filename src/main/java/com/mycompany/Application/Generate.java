package com.mycompany.Application;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will get the files from appConfigProperties and implement them
 * to generate new tasks with the name of the studyTopic to fulfil the study topic's
 * duration using the userPrefs file
 */
public class Generate {
        private static boolean isOverlap(List<Task> existingTasks, LocalDate date, LocalTime startTime, LocalTime endTime) {
            for (Task existingTask : existingTasks) {
                if (date.equals(existingTask.getStartDay())) {
                    if (!(endTime.isBefore(existingTask.getStartTime()) || startTime.isAfter(existingTask.getEndTime()))) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static List<Task> generateTasksForStudyTopic(String topicName, Duration topicDuration, userPrefs prefs, List<Task> existingTasks) {
            List<Task> topicTasks = new ArrayList<>();
            LocalDate currentDate = LocalDate.now();
            LocalTime startTime = prefs.getStartTime();
            LocalTime endTime = prefs.getEndTime();

            while (startTime.isBefore(endTime)) {
                LocalTime endTimeForTask = startTime.plus(topicDuration);

                if (!isOverlap(existingTasks, currentDate, startTime, endTimeForTask)) {
                    Task newTask = new Task(topicName, startTime, endTimeForTask, currentDate, currentDate, "Single Task");
                    topicTasks.add(newTask);
                    startTime = endTimeForTask; // Move to the next time slot
                } else {
                    currentDate = currentDate.plusDays(1); // Skip to the next day
                    startTime = prefs.getStartTime(); // Reset start time
                }
            }

            return topicTasks;
        }

        public static List<Task> generateTasks(List<studyTopic> studyTopics, userPrefs prefs, List<Task> existingTasks) {
            List<Task> generatedTasks = new ArrayList<>();

            for (studyTopic topic : studyTopics) {
                String topicName = topic.getName();
                Duration topicDuration = topic.getLength();
                List<Task> topicTasks = generateTasksForStudyTopic(topicName, topicDuration, prefs, existingTasks);
                generatedTasks.addAll(topicTasks);
            }

            return generatedTasks;
        }
    }
