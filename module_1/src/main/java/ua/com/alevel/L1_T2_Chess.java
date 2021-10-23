package ua.com.alevel;

import java.util.Arrays;

public class L1_T2_Chess {
    public String horseStepIsPossible(char startPosLetter, int startPosNumber,
                                      char finPosLetter, int finPosNumber) {
        Character horse = 9822;
        Character whiteSquare = 9632;
        Character blackSquare = 9633;
        int numberOfStartLetter = (int) Character.toUpperCase(startPosLetter) - 65;
        int numberOfFinLetter = (int) Character.toUpperCase(finPosLetter) - 65;
        char[][] chessBoard = new char[8][8];

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

        chessBoard[startPosNumber - 1][(int) Character.toUpperCase(startPosLetter) - 65] = horse;

        for (int i = 0; i < 8; i++) {
            System.out.println(i + 1 + " " + Arrays.toString(chessBoard[i]));
        }
        System.out.println("  " + " A  " + "B  " + "C  " + "D  " + "E  " + "F  " + "G  " + "H  ");


        return "yes";
    }
}

