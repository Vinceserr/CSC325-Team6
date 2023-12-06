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
    // remove task from map
    public void removeTask(Task task) {
        LocalDate date = task.getStartDay();
        while (!date.isAfter(task.getEndDay())) {
            List<Task> tasks = taskMap.get(date);
            if (tasks != null) {
                tasks.removeIf(t -> t.equals(task));
            }
            date = date.plusDays(1);
        }

        notifyListeners(null);
    }
    // remove task from map but only the date user choose
    public void removeTaskOnDate(LocalDate date, Task task) {
        List<Task> tasksOnDate = taskMap.get(date);
        if (tasksOnDate != null) {
            tasksOnDate.removeIf(t -> t.equals(task));
        }

        notifyListeners(date);
    }

    // get Tasks on this date
    public List<Task> getTasksOnDate(LocalDate date) {
        return taskMap.getOrDefault(date, new ArrayList<>());
    }

    // get all tasks
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Task>> entry : taskMap.entrySet()) {
            for (Task task : entry.getValue()) {
                allTasks.add(task);
            }
        }
        return allTasks;
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

    public Map<LocalDate, List<Task>> getTaskMap() {
        return taskMap;
    }

    // add Listener for data change
    public void addListener(DataChangeListener listener) {
        listeners.add(listener);
    }

    // notify change to every listener
    private void notifyListeners(LocalDate date) {
        for (DataChangeListener listener : listeners) {
            listener.onDataChanged(date);
        }
    }


}
