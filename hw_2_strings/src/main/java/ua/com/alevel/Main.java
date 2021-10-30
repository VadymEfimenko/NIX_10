package ua.com.alevel;

import java.util.Scanner;

public class Main {
    public static int selector = 1;
    public static int innerSelector = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (selector != 0) {
            System.out.println("Если хотите развернуть строку введите 1\n" +
                    "Если хотите развернуть подстроку в строке введите 2\n" +
                    "Если хотите развернуть подстроку по индексу введите 3\n" +
                    "Чтобы выйти введите 0");
            int choice = scanner.nextInt();
            if (choice == 1) {
                innerSelector = 1;
                while (innerSelector != 0) {
                    System.out.println("Введите строку которую хотите развернуть");
                    String userString = scanner.next();
                    System.out.println(ReverseString.reverseString(userString));

                    System.out.println("Решить задачу еще раз \n" +
                            "1 - ДА\n" +
                            "0 - Выход в меню");
                    innerSelector = scanner.nextInt();
                }
            }
            if (choice == 2) {
                innerSelector = 1;
                while (innerSelector != 0) {
                    System.out.println("Введите строку и после подстроку которую хотите развернуть ");
                    String userString = scanner.next();
                    String subString = scanner.next();
                    System.out.println(ReverseString.reverseSubString(userString, subString));

                    System.out.println("Решить задачу еще раз \n" +
                            "1 - ДА\n" +
                            "0 - Выход в меню");
                    innerSelector = scanner.nextInt();
                }
            }
            if (choice == 3) {
                innerSelector = 1;
                while (innerSelector != 0) {
                    System.out.println("Введите строку и после начальный и конечный индексы соответственно\n" +
                            " подстроки которую хотите развернуть ");
                    String userString = scanner.next();
                    int startIndex = scanner.nextInt();
                    int endIndex = scanner.nextInt();
                    System.out.println(ReverseString.reverseStringIndex(userString, startIndex, endIndex));

                    System.out.println("Решить задачу еще раз \n" +
                            "1 - ДА\n" +
                            "0 - Выход в меню");
                    innerSelector = scanner.nextInt();
                }
            }
            if (choice == 0) {
                selector = 0;
            }
        }
    }
}
