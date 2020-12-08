package com.company;

import com.company.classes.*;
import com.company.classes.Process;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date date = new Date();

        // ¬ывод текущей даты и времени с использованием toString()
        System.out.println(date.toString());
        /*
        MemoryScheduler memoryScheduler = new MemoryScheduler();
        memoryScheduler.add(new MemoryBlock(500));
        memoryScheduler.add(new MemoryBlock(1000));
        memoryScheduler.add(new MemoryBlock(1000));
        memoryScheduler.add(new MemoryBlock(1000));
        Queue queue = new Queue();
        queue.add(new Process(0));
        queue.add(new Process(1));
        queue.add(new Process(2));
        queue.add(new Process(3));

        for (int i = 0; i < queue.getLastID(); i++){
            queue.HPF();
        }

        for (int i = 0; i < queue.getSize(); i++){
            memoryScheduler.fillMemoryBLock(queue.get(i));
        }
        System.out.println(queue);

         */
        //Queue queue = new Queue();

        //queue.add(5);
        //System.out.println(queue);
        Scheduler scheduler = new Scheduler(4,4096);

        System.out.println(scheduler);
        System.out.println(date.toString());

    }
}
