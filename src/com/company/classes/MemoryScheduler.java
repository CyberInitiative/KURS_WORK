package com.company.classes;

import java.util.ArrayList;

public class MemoryScheduler {
    private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

    public static void releaseMemoryBLock(MemoryBlock memoryBlock) {
        memoryBlocks.remove(memoryBlock);

        return;
    }

    public static boolean fillMemoryBLock(int size) {
        findFreeBlock(size);
        return false;
    }

    private static int findFreeBlock(int size) {
        System.out.println(print());
        memoryBlocks.sort(MemoryBlock.byEnd);
        System.out.println(print());

        ArrayList<MemoryBlock> tempmemoryBlocks = new ArrayList<>();
        //TODO find free block using given strategy;

        for(int i = 0; i < memoryBlocks.size()-1; i++){
            if (memoryBlocks.get(i+1).start-memoryBlocks.get(i).end > size) {
                MemoryBlock tempMemoryBlock = new MemoryBlock(memoryBlocks.get(i).end, memoryBlocks.get(i+1).start);
                tempmemoryBlocks.add(tempMemoryBlock);
            }
        }
        if (Configuration.memoryVolume-memoryBlocks.get(memoryBlocks.size()-1).end > size){
            MemoryBlock tempMemoryBlock = new MemoryBlock(memoryBlocks.get(memoryBlocks.size()-1).end, Configuration.memoryVolume);
            tempmemoryBlocks.add(tempMemoryBlock);
        }

        return  0;
    }

    public static String print() {
        String result = "[";
        for(MemoryBlock memoryBlock : memoryBlocks){
            result+=memoryBlock+" ";
        }
        return result + "]";
    }

    public static void add(MemoryBlock memoryBlock){
        memoryBlocks.add(memoryBlock);
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < memoryBlocks.size(); i++){
            result+=memoryBlocks.get(i) + " ";
        }
        return result + "]";
    }
}
