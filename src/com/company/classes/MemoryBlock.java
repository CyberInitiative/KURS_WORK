package com.company.classes;

import java.util.ArrayList;
import java.util.Comparator;
//блок памяти
public class MemoryBlock {
    int availableMemory;
    int processesInBlock;
    ArrayList<String> keys = new ArrayList<>();

    public static Comparator<MemoryBlock> byAvailableMemorySize = ((o1, o2) -> o2.availableMemory - o1.availableMemory);

    public MemoryBlock(int availableMemory){
        this.availableMemory = availableMemory;
    }

    @Override
    public String toString() {
        return "{" + availableMemory + " " + processesInBlock +  '}';
    }
    /*
    int start;
    int end;

    @Override
    public String toString() {
        return "{" + start + ", " + end + '}';
    }

    public static Comparator<MemoryBlock> byEnd = ((o1, o2) -> o1.end - o2.end);

    public MemoryBlock(int start,int end){
        this.start = start;
        this.end = end;
    }

     */
}


