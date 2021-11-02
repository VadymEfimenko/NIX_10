package ua.com.alevel;

import java.util.ArrayList;
import java.util.Scanner;

public class L1_T1_UniqueNumbers {
    public int countUniqueNumbersInString(String userString) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        for (int indexAtString = 0; indexAtString < userString.length(); indexAtString++) {

            char numberAtIndex = userString.charAt(indexAtString);

            if (Character.isDigit(numberAtIndex) && !uniqueNumbers.contains((int) numberAtIndex)) {
                uniqueNumbers.add((int) numberAtIndex);
            }
        }

        return uniqueNumbers.size();
    }
}

class UniqueNumbersRun {
    L1_T1_UniqueNumbers uniqueNumbers = new L1_T1_UniqueNumbers();
    String userString;
    Scanner scanner = new Scanner(System.in);

    public void uniqueNumbersRun() {
        try {
            int selector = 1;
            while (selector != 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите вашу строку:");
                userString = scanner.nextLine();
                System.out.println("Количество уникальных цифр: " + uniqueNumbers.countUniqueNumbersInString(userString));
                System.out.println("Решить задачу еще раз?\n" +
                        "1 - ДА\n" +
                        "0 - Вернуться в меню");
                selector = scanner.nextInt();
            }
        } catch (Exception e) {
            int selector = 1;
            System.out.println("Введены некоректные данные\n" +
                    "нажмите 1 чтобы вернуться в меню и запустите задачу снова");
            selector = scanner.nextInt();
        }
    }
}
