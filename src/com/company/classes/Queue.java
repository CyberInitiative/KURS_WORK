package com.company.classes;

import java.util.ArrayList;

public class Queue {
    private ArrayList<Process> queue;
    private int lastID;

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    public Queue() {
        this.queue = new ArrayList<>();
        this.lastID = 1;
    }

    public void add(Process process){
        this.queue.add(process);
    }

    public boolean add()
    {
        Process process = new Process(this.lastID++);
        this.add(process);
        return false;
    }

    public void add(final int N)
    {
        for (int i = 0; i < N; i++) {
            this.add();
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Process process:queue){
            result += process;
        }
        return result;
    }

    //TODO Scheduling algorithm
}
