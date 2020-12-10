package com.company;

import com.company.classes.*;
import com.company.classes.Process;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Date date = new Date();

        //System.out.println(date.toString());
        //System.out.println("Программа в процессе обработки. Пожалуйста, подождите пару минут...");

        Scheduler scheduler = new Scheduler(4,4096);
        scheduler.init();

        //scheduler.schedule();

        Thread thread = new Thread(){
            public void run(){
                //System.out.println("Scheduler thread Running");
                scheduler.schedule();
            }
        };
        thread.start();

        //System.out.println(scheduler);
        //System.out.println(date.toString());
        /*
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.addProcess(3);
         */

        do {
            System.out.println("|||Enter the command|||" + "\n" + "The list of commands: " + "\n" + "0. Exit;" +
                    "\n" + "1. Generate number of processes with random parameters;" + "\n" +
                    "2. Generate the number of processes with manually entered parameters;" + "\n" + "3/Enter. Print the data;");
            Scanner theCommand = new Scanner(System.in);
            switch (theCommand.nextLine()) {
                case ("0"):
                    System.exit(0);
                case ("1"):
                    System.out.println("Введите число процесов. Максимальное количесвто");
                    Scanner numOfRandomProc = new Scanner(System.in);
                    do{
                    scheduler.addProcess(numOfRandomProc.nextInt());
                    System.out.println(scheduler);}
                    while (numOfRandomProc.nextInt() >= 10);
                    break;
                case ("2"):
                    System.out.println("Enter the number of processes");
                    Scanner numberOfProcesses = new Scanner(System.in);
                    //for (int i =0; i < )
                    //scheduler.addProcessManual(numberOfProcesses.nextInt(), int[] size);
                    System.out.println("Enter");
                    System.out.println(scheduler);
                    break;
                case ("3"):
                    int procNumber = Utils.getRandomInteger(1,5);
                    System.out.println("Сщздано " + procNumber + " случайных процессов");
                    int delay = 0;
                    for(int i = 0;i< procNumber; i++) {
                        delay = Utils.getRandomInteger(5000,20000);
                        scheduler.addProcessRandom(delay);
                        System.out.println("Процесс " + (i + 1) + " создастся через " + delay + " секунд");
                    }
                    break;
                case (""):
                    System.out.println(scheduler);
                    break;
                default:
                    break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);

    }
}
