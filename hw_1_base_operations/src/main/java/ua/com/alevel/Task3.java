package ua.com.alevel;

import java.util.Scanner;

public class Task3 {
    public void task3Run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter lesson number from 1 to 10");
        int lessonNumber = scanner.nextInt();

        //Последнюю перемену не считаем, поэтому lessonNumber-1 (количество перемен)
        int sumBreakTime = (lessonNumber-1)*10 - 5*((lessonNumber-1)%2);

        int minutesOfDay = 9*60 + lessonNumber*45 + sumBreakTime;

        int hours = minutesOfDay/60;
        int minutes = minutesOfDay - hours*60;

        System.out.println(hours + " " + minutes);

    }
}
