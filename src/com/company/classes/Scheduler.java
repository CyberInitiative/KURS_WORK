package com.company.classes;

public class Scheduler {
    ProcessQueue processQueue;
    RejectedQueue rejectedQueue;
    CPU cpu;
    MemoryScheduler memoryScheduler;
    ClockGenerator clockGenerator;

    public Scheduler(final int cpuCoresNumber, int memoryVolume) {
        this.processQueue = new ProcessQueue();
        this.rejectedQueue = new RejectedQueue();
        this.cpu = new CPU(cpuCoresNumber);
        this.memoryScheduler = new MemoryScheduler();
        Configuration.memoryVolume = memoryVolume;
        init();
        clockGenerator = new ClockGenerator();
        clockGenerator.run();
    }

    public void init(){
        memoryScheduler.add(new MemoryBlock(0, 100));
        memoryScheduler.add(new MemoryBlock(1000, 1100));
        memoryScheduler.add(new MemoryBlock(500, 800));
        memoryScheduler.add(new MemoryBlock(250, 450));

        processQueue.add(3);
        rejectedQueue.add(2);
    }

    @Override
    public String toString() {
        return "Scheduler{" +
                "process queue=" + processQueue +
                "rejected queue=" + rejectedQueue +
                ", cpu=" + cpu +
                ", memoryScheduler=" + memoryScheduler +
                '}';
    }
}
