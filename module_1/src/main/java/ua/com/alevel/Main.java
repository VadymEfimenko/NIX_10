package ua.com.alevel;

import java.util.Scanner;

public class Main {
    private static int selector = 1;

    public static void main(String[] args) {
        BinaryTreeRun binaryTreeRun = new BinaryTreeRun();
        UniqueNumbersRun uniqueNumbersRun = new UniqueNumbersRun();
        ChessRun chessRun = new ChessRun();
        TriangleAreaRun triangleAreaRun = new TriangleAreaRun();
        PermissibleStringRun permissibleStringRun = new PermissibleStringRun();
        Scanner scanner = new Scanner(System.in);

        while (selector != 0) {
            System.out.println("Выберите задачу от 1 до 6:\n" +
                    "ПЕРВЫЙ УРОВЕНЬ\n" +
                    "1 - Число уникальных цифр в строке\n" +
                    "2 - Ход коня\n" +
                    "3 - Площадь треугольника\n" +
                    "ВТОРОЙ УРОВЕНЬ\n" +
                    "4 - Допустимая строка\n" +
                    "5 - Бинарное дерево\n" +
                    "ТРЕТИЙ УРОВЕНЬ\n" +
                    "6 - Игра жизнь\n" +
                    "0 - ВЫХОД");

            selector = scanner.nextInt();
            switch (selector) {
                case 1 -> uniqueNumbersRun.uniqueNumbersRun();
                case 2 -> chessRun.chessRun();
                case 3 -> triangleAreaRun.triangleAreaRun();
                case 4 -> permissibleStringRun.permissibleStringRun();
                case 5 -> binaryTreeRun.binTreeRun();
            }
        }
    }
}

