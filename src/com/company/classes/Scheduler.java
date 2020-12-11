package com.company.classes;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {
    private Queue jobsQueue;
    private Queue readyQueue;
    private Queue waitingQueue;
    private Queue rejectedQueue;

    private MemoryScheduler memoryScheduler;
    private TactGenerator tactGenerator;

    private static CPU cpu;
    private static Device device;

    public Scheduler(final int cpuCoresNumber, int memoryVolume) {
        this.jobsQueue = new Queue();
        this.readyQueue = new Queue();
        this.rejectedQueue = new Queue();
        this.waitingQueue = new Queue();

        this.memoryScheduler = new MemoryScheduler();
        Configuration.memoryVolume = memoryVolume;

        cpu = new CPU(cpuCoresNumber);
        device = new Device();

        tactGenerator = new TactGenerator();

        //init();
        //schedule();
    }

    public void addProcess(int number){
        jobsQueue.add(number);
        for (int i = 0; i < jobsQueue.getSize(); i++) {
            var process = jobsQueue.get(i);
            if(process.getState() == State.New)
                initProcess(process);
        }

        jobsQueue.sortByPriorityAndArrivalTime(State.Ready);
    }

    public void addProcessManual(int number){
        jobsQueue.addManual(number);
        for (int i = 0; i < jobsQueue.getSize(); i++) {
            var process = jobsQueue.get(i);
            if(process.getState() == State.New)
                initProcess(process);
        }

        jobsQueue.sortByPriorityAndArrivalTime(State.Ready);
    }

    public void add(Process process){
        jobsQueue.add(process);
    }

    public void addProcessRandom(int time){
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                jobsQueue.add(1);
                jobsQueue.sortByPriorityAndArrivalTime(State.Ready);
            }
        };
        timer.schedule(timerTask, time);
    }

    public void init() {

        Timer timer = new Timer();
        timer.schedule(tactGenerator, 1000, 1000);
        tactGenerator.run();

        //jobsQueue.add(10);
        memoryScheduler.add(new MemoryBlock(200));
        memoryScheduler.add(new MemoryBlock(1200));
        memoryScheduler.add(new MemoryBlock(1200));
        memoryScheduler.add(new MemoryBlock(1200));
        /*
        for (int i = 0; i < jobsQueue.getSize(); i++) {
            initProcess(jobsQueue.get(i));
        }
        */
        //readyQueue.sortByPriority();
        //schedule();
    }

    public void schedule() {
        //readyQueue.sortByPriority();
        //int initAttempts = 3;

        do {
            /*
            for (Core core : cpu.getCores()) {
                if (core.isIdle() && readyQueue.getSize() > 0) {
                    var process = readyQueue.get(0);
                    process.setState(State.Running);
                    //if(processQueue.getSize() > 0) {
                    core.setIdle(false);
                    process.setCore(core);
                }
            }
            */
            for (int i = 0; i < readyQueue.getSize(); i++) {
                var process = readyQueue.get(i);

                if(process.getState() == State.Ready) {
                    for (Core core : cpu.getCores()) {
                        if (core.isIdle()) {
                            //var process = readyQueue.get(0);
                            process.setState(State.Running);
                            process.setRunTime(TactGenerator.getTime());
                            //if(processQueue.getSize() > 0) {
                            core.setIdle(false);
                            process.setCore(core);
                            break;
                        }
                    }
                }
                if(process.getState() != State.Running)
                    continue;

                process.setBurstTime(TactGenerator.getTime() - process.getRunTime() - process.getIdleTime());

                if (process.getBurstTime() >= process.getTime()) {

                    process.setState(State.Terminated);
                    //process.getCore().setIdle(true);
                    //process.setCore(null);
                    //process.releaseMemory();
                    //readyQueue.remove(process);
                    System.out.println("////////////////////////////////////////////");
                    System.out.println(jobsQueue);
                    System.out.println("............................................");
                    System.out.println(cpu);
                    System.out.println("............................................");
                    System.out.println("--------------------------------------------");
                    System.out.println(memoryScheduler);
                    System.out.println("--------------------------------------------");
                    System.out.println("////////////////////////////////////////////");
                }
            }

            for (int i = 0; i < readyQueue.getSize(); i++){
                var process = readyQueue.get(i);

                if(process.getState() == State.Waiting) {
                    waitingQueue.add(process);
                    readyQueue.remove(process);
                }
                else if(process.getState() == State.Terminated)
                    readyQueue.remove(process);
            }

            for (int i = 0; i < waitingQueue.getSize(); i++){
                var process = waitingQueue.get(i);

                if(process.getState() == State.Running) {
                    readyQueue.add(process);
                    waitingQueue.remove(process);
                }
            }

            for (int i = 0; i < jobsQueue.getSize(); i++) {
                var process = jobsQueue.get(i);
                if(process.getState() == State.New)
                {
                    //if(process.getMaxInitAttempts() > 0)
                    initProcess(process);
                    readyQueue.sortByPriorityAndArrivalTime(State.Ready);

                    //else {
                    //    rejectedQueue.add(process);
                    //    jobsQueue.remove(process);
                    //}
                }
            }
            /*
            boolean stopSchedule = true;
            for (int i = 0; i < jobsQueue.getSize(); i++) {
                var process = jobsQueue.get(i);
                if (process.getState() != State.Terminated) {
                    stopSchedule = false;
                    break;
                }
            }

            if (stopSchedule)
                break;
            */
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println("While performed on " + new Date());

        } while (true);
    }

    public MemoryScheduler getMemoryScheduler() {
        return memoryScheduler;
    }

    private void initProcess(Process process){
        var block = memoryScheduler.fillMemoryBLock(process.getMemory());
        if(block != null){
            process.setState(State.Ready);
            process.setMemoryBlock(block);
            process.setDevice(device);
            readyQueue.add(process);
        }
        //else {
        //    process.setMaxInitAttempts();
        //}
    }

    public Queue getJobsQueue() {
        return jobsQueue;
    }

    public void setJobsQueue(Queue jobsQueue) {
        this.jobsQueue = jobsQueue;
    }

    public Queue getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(Queue readyQueue) {
        this.readyQueue = readyQueue;
    }

    public Queue getWaitingQueue() {
        return waitingQueue;
    }

    public void setWaitingQueue(Queue waitingQueue) {
        this.waitingQueue = waitingQueue;
    }

    public Queue getRejectedQueue() {
        return rejectedQueue;
    }

    public void setRejectedQueue(Queue rejectedQueue) {
        this.rejectedQueue = rejectedQueue;
    }

    public static CPU getCpu() {
        return cpu;
    }

    public static Device getDevice() {
        return device;
    }


    @Override
    public String toString() {
        return "Scheduler {" + "\n" +
                "jobs queue: [ " + "\n" + jobsQueue + " ]" + " \n" +
                "rejected queue: [" +  "\n" +rejectedQueue + " ]" + " \n" +
                "CPU:"  + cpu + "\n" +
                "memoryScheduler: "  + memoryScheduler +
                '}';
    }
}
