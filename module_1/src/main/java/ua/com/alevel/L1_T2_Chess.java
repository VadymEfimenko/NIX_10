package ua.com.alevel;

import java.util.Arrays;
import java.util.Scanner;

//mismatch ex
public class L1_T2_Chess {
    public void horseStepIsPossible() {

        char startPosLetter;
        int startPosNumber;
        char finPosLetter;
        int finPosNumber;

        //кодировка в юникоде
        char horse = 9822;
        char whiteSquare = 9644;
        char blackSquare = 9645;

        char[][] chessBoard = new char[8][8];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите начальную и желаемую позицию коня: ");

        System.out.println("Начальная позиция, буква A - H: ");
        startPosLetter = scanner.next().charAt(0);

        System.out.println("Начальная позиция, цифра 1 - 8: ");
        startPosNumber = scanner.nextInt();

        System.out.println("Желаемая позиция, буква A - H: ");
        finPosLetter = scanner.next().charAt(0);

        System.out.println("Желаемая позиция, цифра 1 - 8: ");
        finPosNumber = scanner.nextInt();

        // приведение char к int, выражение букв в цифрах от 1 до 8 для заполнения массива
        int numberOfStartLetter = (int) Character.toUpperCase(startPosLetter) - 65;
        int numberOfFinLetter = (int) Character.toUpperCase(finPosLetter) - 65;

        //величина шага хода коня
        int horseStepLetter = numberOfFinLetter - numberOfStartLetter;
        int horseStepNumber = finPosNumber - startPosNumber;

        //отрисовка доски в консоли
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (i % 2 == 0 & j % 2 == 1) {
                    chessBoard[i][j] = blackSquare;
                } else if (i % 2 == 1 & j % 2 == 0) {
                    chessBoard[i][j] = blackSquare;
                } else if (i % 2 == 0 & j % 2 == 0) {
                    chessBoard[i][j] = whiteSquare;
                } else if (i % 2 == 1 & j % 2 == 1) {
                    chessBoard[i][j] = whiteSquare;
                }
            }
        }

        //Логика
        try {
            chessBoard[startPosNumber - 1][numberOfStartLetter] = horse;
            chessBoard[finPosNumber - 1][numberOfFinLetter] = horse;

            if (Math.abs(horseStepNumber) == 1 && Math.abs(horseStepLetter) == 2 ||
                    Math.abs(horseStepNumber) == 2 && Math.abs(horseStepLetter) == 1) {
                System.out.println("Ход возможен");
            } else {
                System.out.println("Ход невозможен");
            }

        } catch (Exception e) {
            System.out.println("Введите корректные значения! \n" +
                    "букву латиницей от A до H \n" +
                    "цифру от 1 до 8");
        }

        for (int i = 0; i < 8; i++) {
            System.out.println(i + 1 + " " + Arrays.toString(chessBoard[i]));
        }
        System.out.println("  " + " A  " + " B  " + " C  " + "D  " + " E  " + " F  " + "G  " + " H");


    }
}

