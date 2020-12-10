package com.company.classes;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Process{
    private int id;
    private String name;
    private int priority;
    private int time; //число работы в тактах
    private int memory; //кол-во памяти
    private int arrivalTime; //время захода
    private int burstTime; // Общее время, необходимое центральному процессору для выполнения всего процесса (не включает в себя время ожидания)
    private State state;
    private MemoryBlock memoryBlock;
    private Core core;
    private Device device;
    private Timer timer = new Timer();
    //private int maxInitAttempts = 3;

    //TODO заменить все цифры на константы
    public Process(int id) {
        this.id = id;
        this.memory = Utils.getRandomInteger(10,Configuration.memoryVolume/4);  //кол-во памяти, требуемое для процесса
        this.priority = Utils.getRandomInteger( Configuration.maxPriority);
        this.time = Utils.getRandomInteger(10,100);
        this.arrivalTime = TactGenerator.getTime();
        this.burstTime = 0;
        this.name = "P" + this.id;
        this.state = State.New;
    }
    public Process(int id, String name, int priority, int time, int memory) {
        this.id = id;
        this.name = "P" + this.id;
        this.priority = priority;
        this.time = time;
        this.memory = memory;
        this.arrivalTime = TactGenerator.getTime();
        this.burstTime = 0;
        this.state = State.New;
    }

    public Process() {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.time = time;
        this.memory = memory;
        this.arrivalTime = TactGenerator.getTime();
        this.burstTime = 0;
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

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setState(State state) {
        this.state = state;
        if(state == State.Running){
            startTimer();
        }
        else if(state == State.Terminated){
            stopTimer();
            core.setIdle(true);
            core = null;
            releaseMemory();
        }
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public MemoryBlock getMemoryBlock() {
        return memoryBlock;
    }

    public void setMemoryBlock(MemoryBlock memoryBlock) {
        this.memoryBlock = memoryBlock;
    }

    public void releaseMemory(){
        int memory = memoryBlock.getAvailableMemory();
        this.memoryBlock.setAvailableMemory(memory += this.memory);
    }

    public void setDevice(Device device) {
        this.device = device;
    }
/*
    public int getMaxInitAttempts() {
        return maxInitAttempts;
    }

    public void setMaxInitAttempts() {
        this.maxInitAttempts--;
    }
 */
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    private void startTimer(){
        int requestDeviceTime = Utils.getRandomInteger(0,time / 2);
        var me = this;
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                if(burstTime >= requestDeviceTime && state == State.Running){
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    System.out.println(device);
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    state = State.Waiting;
                    int resourceNumber = device.getResource();
                    if(resourceNumber != -1){
                        //if(device.doWork(resourceNumber)) {
                        device.doWork(resourceNumber);
                        state = State.Running;
                        stopTimer();
                        //}
                    }
                    else
                        System.out.println(new Date() + " Process " + me.name + " is waiting for resource");
                }
            }
        };

        timer.schedule(repeatedTask, 500, 500);
    }
    private void stopTimer(){
        timer.cancel();
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
                (core != null ? (", core number=" + core.getNumber()) : "") +
                '}' + "\n";
    }

    //public static Comparator<Process> byPriority = ((o1, o2) -> o1.priority - o2.priority);
    //public static Comparator<Process> byArrivalTime = ((o1, o2) -> o1.arrivalTime - o2.arrivalTime);
}
