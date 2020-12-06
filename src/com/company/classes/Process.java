package com.company.classes;

public class Process {
    int id;
    String name;
    int priority;
    int time; //число работы в тактах
    int memory; //кол-во памяти
    int arrivalTime; //время захода
    int burstTime; // время сколько процесс отработал на ЦП
    State state;
    //заменить все цифры на константы
    public Process(int id) {
        this.id = id;
        this.memory = Utils.getRandomInteger(10,Configuration.memoryVolume/2);  //кол-во памяти, требуемое для процесса
        this.priority = Utils.getRandomInteger( Configuration.maxPriority);
        this.time = Utils.getRandomInteger(10,100);
        this.arrivalTime = TactGenerator.getTime();
        this.burstTime = 0;
        this.name = "P" + this.id;
        this.state = State.New;
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

    public int getArrivalTime() {
        return arrivalTime;
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

    @Override
    public String toString() {
        return id +
                "{ name='" + name + '\'' +
                ", priority=" + priority +
                ", time=" + time +
                ", memory=" + memory +
                ", timeIn=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", state=" + state +
                '}' + "\n";
    }
}
