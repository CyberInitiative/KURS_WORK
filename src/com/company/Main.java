package com.company;

import com.company.classes.*;
import com.company.classes.Process;

public class Main {

    public static void main(String[] args) {
        //MemoryScheduler.add(new MemoryBlock(0, 100));
        //MemoryScheduler.add(new MemoryBlock(1000, 1100));
        //MemoryScheduler.add(new MemoryBlock(500, 800));
        //MemoryScheduler.add(new MemoryBlock(250, 450));

        //MemoryScheduler.fillMemoryBLock(100);

        //Queue queue = new Queue();

        //queue.add(5);
        //System.out.println(queue);
        Scheduler scheduler = new Scheduler(4,4096);
        System.out.println(scheduler);
    }
}
