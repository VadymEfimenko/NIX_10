package ua.com.alevel;


import java.util.Scanner;

public class SumNumbersInString {
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

