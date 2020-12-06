package com.company.classes;

import java.util.ArrayList;
// тут определяет метод поиска
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

        ArrayList<MemoryBlock> tempMemoryBlocks = new ArrayList<>();
        //TODO find free block using given strategy;

        for(int i = 0; i < memoryBlocks.size()-1; i++){
            if (memoryBlocks.get(i+1).start-memoryBlocks.get(i).end > size) {
                MemoryBlock tempMemoryBlock = new MemoryBlock(memoryBlocks.get(i).end, memoryBlocks.get(i+1).start);
                tempMemoryBlocks.add(tempMemoryBlock);
                System.out.println(tempMemoryBlock);
            }
        }
        if (Configuration.memoryVolume-memoryBlocks.get(memoryBlocks.size()-1).end > size){
            MemoryBlock tempMemoryBlock = new MemoryBlock(memoryBlocks.get(memoryBlocks.size()-1).end, Configuration.memoryVolume);
            tempMemoryBlocks.add(tempMemoryBlock);
            System.out.println(tempMemoryBlock);
        }

        return  0;
    }

    public static void add(MemoryBlock memoryBlock){
        memoryBlocks.add(memoryBlock);
    }

    public static String print() {
        String result = "[";
        for(MemoryBlock memoryBlock : memoryBlocks){
            result+=memoryBlock+" ";
        }
        return result + "]";
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