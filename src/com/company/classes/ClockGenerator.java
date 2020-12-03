package com.company.classes;

public class ClockGenerator {
    public static int getTime() {
        return time;
    }

    public static void incTime(int tact)
    {
        time+=tact;
    }

    public static void incTime()
    {
        time++;
    }

    private static int time;
}
