package ua.com.alevel;

import ua.com.alevel.level3.RunGameOfLife;


import java.util.Scanner;

public class Main {

    private static int selector;

    public static void main(String[] args) {
        BinaryTreeRun binaryTreeRun = new BinaryTreeRun();
        UniqueNumbersRun uniqueNumbersRun = new UniqueNumbersRun();
        ChessRun chessRun = new ChessRun();
        TriangleAreaRun triangleAreaRun = new TriangleAreaRun();
        PermissibleStringRun permissibleStringRun = new PermissibleStringRun();
        RunGameOfLife run = new RunGameOfLife();
        Scanner scanner = new Scanner(System.in);

        while (true) {
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

            try {
                selector = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Введены неправильные данные\n" +
                        "Ведите номер задачи от 1 до 6");
                 scanner = new Scanner(System.in);
                selector = scanner.nextInt();
                continue;
            }
            switch (selector) {
                case 1 -> uniqueNumbersRun.uniqueNumbersRun();
                case 2 -> chessRun.chessRun();
                case 3 -> triangleAreaRun.triangleAreaRun();
                case 4 -> permissibleStringRun.permissibleStringRun();
                case 5 -> binaryTreeRun.binTreeRun();
                case 6 -> run.start();
            }
            if (selector == 0){
                break;
            }
        }
    }
}

