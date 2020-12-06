package com.company.classes;

import java.util.Comparator;
//блок памяти
public class MemoryBlock {
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
}


