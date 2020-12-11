package com.company;

import com.company.classes.*;
import com.company.classes.Process;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Date date = new Date();
        //System.out.println(date.toString());
        //System.out.println("Программа в процессе обработки. Пожалуйста, подождите пару минут...");

        Scheduler scheduler = new Scheduler(4, 4096);
        scheduler.init();

        //scheduler.schedule();

        Thread thread = new Thread() {
            public void run() {
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
            System.out.println("|||Введите команду!|||" + "\n" + "Список команд: " + "\n" + "0. Выйти из программы;" +
                    "\n" + "1. Сгенерировать кол-во процессов со случайными параметрами;" + "\n" +
                    "2. Сгенерировать кол-во процессов с заданными параметрами;" + "\n" +
                    "3. Инициировать создание случайное кол-во процессов в случайный момент времени;" + "\n" +
                    "4 или Enter. Распечать данные.");
            Scanner theCommand = new Scanner(System.in);
            switch (theCommand.nextLine()) {
                case ("0"):
                    System.exit(0);
                case ("1"):
                    Scanner numOfRandomProc = new Scanner(System.in);
                    int input1;
                    do {
                        System.out.println("Введите число процесов. Максимальное кол-во процессов - 10;");
                        try {
                            input1 = numOfRandomProc.nextInt();
                            if (input1 >= 0 && input1 <= 10) {
                                scheduler.addProcess(input1);
                                break;
                            } else {
                                System.out.println("Вы ввели число, неподходящее критериям ввода, попробуйте снова;");
                                continue;
                            }
                        } catch (final InputMismatchException exception) {
                            System.out.println("Вы ввели не число, попробуйте снова;");
                            numOfRandomProc.next();
                            continue;
                        }
                    } while (true);
                    System.out.println(scheduler);
                    break;
                case ("2"):/*
                    Scanner numberOfProcesses = new Scanner(System.in);
                    int input2;
                    do {
                        System.out.println("Введите кол-во процессов. Максимальное количество процессов - 10");
                        try {
                            input2 = numberOfProcesses.nextInt();
                            if(input2 >= 0 && input2 <= 10){
                                int number = input2;

////////////////////////////////////////////////////////////////////////////////
                                for(int i = 0; i < number; i++){
                                    Process process = new Process();
                                    Scanner scanner = new Scanner(System.in);
                                    int parameter;
                                    do{
                                        System.out.println("Введите Id");
                                        try {
                                            parameter = scanner.nextInt();
                                            if(scanner.hasNextInt()){
                                                break;
                                            }
                                            else {
                                                continue;
                                            }
                                        }catch (final InputMismatchException ex) {
                                            System.out.println("Вы ввели не число, попробуйте снова");
                                            numberOfProcesses.next();
                                            continue;
                                        }
                                    }while (true);
                                }
////////////////////////////////////////////////////////////////////
                            } else {
                                System.out.println("Вы ввели число, неподходящее критериям ввода, попробуйте снова;");
                            } continue;
                        }catch (final InputMismatchException ex){
                            System.out.println("Вы ввели не число, попробуйте снова");
                            numberOfProcesses.next();
                            continue;
                        }
                    }while (true);
                   */
                case ("3"):
                    int procNumber = Utils.getRandomInteger(1, 5);
                    System.out.println("Создано " + procNumber + " случайных процессов");
                    int delay = 0;
                    for (int i = 0; i < procNumber; i++) {
                        delay = Utils.getRandomInteger(5000, 20000);
                        scheduler.addProcessRandom(delay);
                        System.out.println("Процесс " + (i + 1) + " создастся через " + delay + " секунд");
                    }
                    break;
                case ("4"):
                case (""):
                    System.out.println(scheduler);
                    break;
                default:
                    break;
            }
        } while (true);

    }
}


/*
                        Process process = new Process();
                        do {
                            try {
                                int inputParametr = numberOfProcesses.nextInt();
                                System.out.println("Введите id");
                                if (numberOfProcesses.hasNextInt()) {
                                    process.setId(numberOfProcesses.nextInt());
                                } else
                                    continue;
                            }catch (final InputMismatchException exception){
                                System.out.println("Вы задали параметр неверно. Попробуйте снова;");
                                numberOfProcesses.next();
                                continue;
                            }
                            System.out.println("Введите приоритет");
                            if (numberOfProcesses.hasNextInt()) {
                                process.setPriority(numberOfProcesses.nextInt());
                            }
                            else
                                continue;
                            scheduler.add(process);
                        } while (true);

                         */