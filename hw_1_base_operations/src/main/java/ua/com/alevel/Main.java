package ua.com.alevel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();

        Scanner scanner = new Scanner(System.in);


        while (true) {
            try {
                System.out.println("enter the number of the task you want to run, from 1 to 3. 0 to exit");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("task 1:");
                    task1.task1Run();

                } else if (choice == 2) {
                    System.out.println("task 2:");
                    task2.task2Run();

                } else if (choice == 3) {
                    System.out.println("task 3:");
                    task3.task3Run();

                } else if (choice == 0) {
                    System.out.println("end program");
                    break;

                } else {
                    System.out.println("That's not an option!");
                    continue;
                }
            } catch (InputMismatchException input) {
                System.out.println("That's not an option!");
                continue;
            }
        }


    }
}
