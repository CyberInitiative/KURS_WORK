package com.company.classes;

import java.util.Timer;

public class Scheduler {
    private Queue processQueue;
    private Queue rejectedQueue;
    private Queue runningQueue;
    private Queue jobsQueue;
    private CPU cpu;
    private MemoryScheduler memoryScheduler;
    private TactGenerator tactGenerator;

    public Scheduler(final int cpuCoresNumber, int memoryVolume) {
        this.processQueue = new Queue();
        this.rejectedQueue = new Queue();
        this.jobsQueue = new Queue();
        this.runningQueue = new Queue();
        this.cpu = new CPU(cpuCoresNumber);
        this.memoryScheduler = new MemoryScheduler();
        Configuration.memoryVolume = memoryVolume;
        tactGenerator = new TactGenerator();
        Timer timer = new Timer();
        timer.schedule(tactGenerator, 1000, 1000);
        tactGenerator.run();
        init();
        schedule();
    }

    public void init() {
        jobsQueue.add(4);
        memoryScheduler.add(new MemoryBlock(200));
        memoryScheduler.add(new MemoryBlock(1200));
        memoryScheduler.add(new MemoryBlock(1200));
        memoryScheduler.add(new MemoryBlock(1200));
        for (int i = 0; i < jobsQueue.getSize(); i++) {
            var process = jobsQueue.get(i);
            var block = memoryScheduler.fillMemoryBLock(jobsQueue.get(i).getMemory());
            if(block != null){
                process.setState(State.Ready);
                process.setMemoryBlock(block);
                processQueue.add(process);
            }
            else {
                rejectedQueue.add(process);
            }
        }
        /*
        for (int i = 0; i < jobsQueue.getSize(); i++) {
            if (jobsQueue.get(i).getState() == State.Ready) {
                processQueue.add(jobsQueue.get(i));
            }
        }

         */
        processQueue.sortByPriority();
        //schedule();
    }

    public void schedule() {
        do {
            //memoryScheduler.releaseBlock();
            //if (processQueue.getSize() > 0) {
            for (Core core : this.cpu.getCores()) {
                if (core.isIdle() && processQueue.getSize() > 0) {
                    var process = processQueue.get(0);
                    process.setState(State.Running);
                    //if(processQueue.getSize() > 0) {
                    core.setIdle(false);
                    process.setCore(core);
                    runningQueue.add(process);
                    processQueue.remove(process);
                    //}
                }
            }
            //}

            for (int i = 0; i < runningQueue.getSize(); i++) {
                var process = runningQueue.get(i);
                process.setBurstTime(TactGenerator.getTime() - process.getArrivalTime());

                if (process.getBurstTime() >= process.getTime()) {
                    process.setState(State.Terminated);
                    process.getCore().setIdle(true);
                    process.setCore(null);
                    process.releaseMemory();
                    runningQueue.remove(process);
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

            boolean terminate = true;
            for (int i = 0; i < jobsQueue.getSize(); i++) {
                if (jobsQueue.get(i).getState() != State.Terminated) {
                    terminate = false;
                    break;
                }
            }

            if (terminate)
                break;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } while (true);
    }

    @Override
    public String toString() {
        return "Scheduler{" +
                "jobs queue = " + jobsQueue +
                "process queue =" + processQueue +
                "rejected queue =" + rejectedQueue +
                ", cpu=" + cpu +
                ", memoryScheduler =" + memoryScheduler +
                '}';
    }
}
