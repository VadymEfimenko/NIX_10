package ua.com.alevel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SumNumbersInString task1 = new SumNumbersInString();
        CountCharInString task2 = new CountCharInString();
        EndLessonsTime endLessonsTime = new EndLessonsTime();

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
                    endLessonsTime.task3Run();

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
