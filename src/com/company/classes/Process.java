package com.company.classes;

import java.util.Random;

public class Process {
    int id;
    String name;
    int priority;
    int time;
    int memory;
    int timeIn;
    int burstTime;
    State state;

    public Process(int id) {
        this.id = id;
        this.memory = Utils.getRandomInteger(10,Configuration.memoryVolume/2);  //заменить все цифры на константы
        this.priority = Utils.getRandomInteger( Configuration.maxPriority);
        this.time = Utils.getRandomInteger(10,100);
        this.timeIn = ClockGenerator.getTime();
        this.burstTime = 0;
        this.name = "P" + this.id;
        this.state = State.Ready;
    }

    @Override
    public String toString() {
        return id +
                "{ name='" + name + '\'' +
                ", priority=" + priority +
                ", time=" + time +
                ", memory=" + memory +
                ", timeIn=" + timeIn +
                ", burstTime=" + burstTime +
                ", state=" + state +
                '}' + "\n";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getTime() {
        return time;
    }

    public int getMemory() {
        return memory;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public State getState() {
        return state;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
