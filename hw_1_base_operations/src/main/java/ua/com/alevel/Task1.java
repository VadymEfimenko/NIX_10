package ua.com.alevel;
/*

реализуйте задачу, которая принимает строку с консоли и вычленяет все числа
и находит их сумму

Пример:
Входные данные: 1w4tt!7
Выходные данные: 12

 */

import java.util.Scanner;

public class Task1 {
    public void task1Run () {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your string to count the numbers in it");
        String usersString = scanner.nextLine();

        int sumNumbersInString = 0;
        for (int i = 0; i < usersString.length() ; i++) {
            char tempChar = usersString.charAt(i);
            if (Character.isDigit(tempChar)){
                int tempDigit = Integer.parseInt(String.valueOf(tempChar));

                sumNumbersInString = sumNumbersInString + tempDigit;
            }
        }
        System.out.println(sumNumbersInString);
    }
}

