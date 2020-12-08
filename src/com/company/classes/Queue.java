package com.company.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//Общая очередь всех процессов. Все новые процессы попадают сюда.
public class Queue {
    private ArrayList<Process> queue;
    private int lastID;
    //ProcessQueue processQueue = new ProcessQueue();

    public int getLastID() {
        return lastID;
    }

    public Process get(int i) {
        return queue.get(i);
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    public Queue() {
        this.queue = new ArrayList<>();
        this.lastID = 1;
    }

    public int getSize() {
        return queue.size();
    }

    public void add(Process process) {
        this.queue.add(process);
    }

    public void add() {
        Process process = new Process(this.lastID++);

        this.add(process);
    }

    public void remove(int i){
        this.queue.remove(i);
    }

    public void remove(Process process){
        this.queue.remove(process);
    }

    /*
    public void readySearcher(){
        for (int i = 0; i < queue.size(); i++){
            if(queue.get(i).state == State.Ready){
                processQueue.add(queue.get(i));
            }
        }
    }

     */

    public void add(final int N) {
        for (int i = 0; i < N; i++) {

            try {
                Thread.sleep(Utils.getRandomInteger(500, 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            this.add();
        }
    }

    /*
    public void sortByArrival(){
       for(int i = 0; i < queue.size() - 1; i++){
           for(int j = 0; j < queue.size() - i - 1; j++){
               if(queue.get(j).getArrivalTime() > queue.get(j + 1).getArrivalTime()){
                   Process temp = queue.get(j);
                   queue.get(j) = queue.get(j + 1);
               }
           }
       }
    }
     */
    public void sortByArrivalTime(){
        Process [] sortedQueue = this.queue.toArray(new Process[0]);
        for(int i = 0; i < sortedQueue.length - 1; i++){
            for(int j = 0; j < sortedQueue.length - i - 1; j++){
                if(sortedQueue[j].getArrivalTime() > sortedQueue[j + 1].getArrivalTime()){
                    Process temp = sortedQueue[j];
                    sortedQueue[j] = sortedQueue[j+1];
                    sortedQueue[j + 1] = temp;
                }
            }
        }
        this.queue = new ArrayList(Arrays.asList(sortedQueue));
    }

    public void sortByPriority(){
        Process [] sortedQueue = this.queue.toArray(new Process[0]);
        sortByArrivalTime();
        int n = sortedQueue.length;

        int indexOfFirstElement = 0;
        Process first = sortedQueue[0];
        //in reverse so in case there is a clash it favours lower id
        for (int i = n -1; i > 0; i--) {
            //find process with shortest duration and at time 0
            if (sortedQueue[i].getArrivalTime() <= first.getArrivalTime() && first.getPriority() >= sortedQueue[i].getPriority()) {
                first = sortedQueue[i];
                indexOfFirstElement = i;
            }
        }

        //swap the current first element with the new one
        Process temp = sortedQueue[indexOfFirstElement];
        sortedQueue[indexOfFirstElement] = sortedQueue[0];
        sortedQueue[0] = temp;
        this.queue = new ArrayList(Arrays.asList(sortedQueue));
    }


    /*
    public void HPF(){
        for(int i = 0; i < queue.size(); i++){
            queue.sort(Process.byPriority);
        }
    }
     */

    @Override
    public String toString() {
        String result = "";
        for (Process process : queue) {
            result += process;
        }
        return result;
    }
}
