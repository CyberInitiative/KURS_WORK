package com.company.classes;

import java.util.ArrayList;

public class Queue {
    private ArrayList<Process> queue;

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    private int lastID;

    public Queue() {
        this.queue = new ArrayList<>();
        this.lastID = 1;

    }

    public void add()
    {
        queue.add(new Process(this.lastID++));
    }

    public void add(final int N)
    {
        for (int i = 0; i < N; i++) {
            this.queue.add(new Process(this.lastID++));
        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "queue=" + queue +
                '}';
    }

    //TODO Scheduling algorithm
}
