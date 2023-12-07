package com.mycompany.Application;

import com.mycompany.Model.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class TaskScheduler {
    private Map<LocalDate, List<Task>> taskMap;
    private Set<DataChangeListener> listeners;

    public TaskScheduler() {
        taskMap = new HashMap<>();
        listeners = new HashSet<>();
    }

    // add task into map
    public void addTask(Task task) {
        LocalDate date = task.getStartDay();
        while (!date.isAfter(task.getEndDay())) {
            if (isDayOfWeekAllowed(date, task)) {
                taskMap.computeIfAbsent(date, k -> new ArrayList<>()).add(task);
            }
            date = date.plusDays(1);
        }

        notifyListeners(null);
    }
    // remove all identical tasks or a single task on date select by user
    public void removeTask(LocalDate date,Task task) {
        if(date == null){
            date = task.getStartDay();
            while (!date.isAfter(task.getEndDay())) {
                List<Task> tasks = taskMap.get(date);
                if (tasks != null) {
                    tasks.removeIf(t -> t.equals(task));
                }
                date = date.plusDays(1);
            }
        }else {
            List<Task> tasksOnDate = taskMap.get(date);
            if (tasksOnDate != null) {
                tasksOnDate.removeIf(t -> t.equals(task));
            }
        }


        notifyListeners(date);
    }


    // get Tasks list on this date
    public List<Task> getTasksOnDate(LocalDate date) {
        return taskMap.getOrDefault(date, new ArrayList<>());
    }

    // get all tasks
    public List<Task> getAllTasks() {
        Set<Task> uniqueTasks = new HashSet<>();
        for (Map.Entry<LocalDate, List<Task>> entry : taskMap.entrySet()) {
            uniqueTasks.addAll(entry.getValue());
        }
        List<Task> allTasks = new ArrayList<>(uniqueTasks);
        return allTasks;
    }

    public void printMap(){
        for (Map.Entry<LocalDate, List<Task>> entry : taskMap.entrySet()) {
            System.out.println(entry.getKey());
            for(Task task:entry.getValue()){
                System.out.println(task.getStartDay()+":" + task.getTitle());
            }
        }
    }

    // a tool method to check the day of weeks
    private boolean isDayOfWeekAllowed(LocalDate date, Task task) {
        DayOfWeek day = date.getDayOfWeek();
        for (DayOfWeek allowedDay : task.getWeeks()) {
            if (allowedDay == day) {
                return true;
            }
        }
        return false;
    }

    // add Listener for data change
    public void addListener(DataChangeListener listener) {
        listeners.add(listener);
    }

    // notify change to every listener
    public void notifyListeners(LocalDate date) {
        for (DataChangeListener listener : listeners) {
            listener.onDataChanged(date);
        }
    }


}
