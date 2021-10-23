package ua.com.alevel;

import java.util.Arrays;

public class L1_T2_Chess {
    public String horseStepIsPossible(char startPosLetter, int startPosNumber,
                                      char finPosLetter, int finPosNumber) {
        Character horse = 9822;
        char[][] chessBoard = new char[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(chessBoard[i], '*' );
        }

        chessBoard[startPosNumber-1][(int) Character.toUpperCase(startPosLetter) - 65] = horse ;

        for (int i = 0; i < 8; i++) {
            System.out.println(i + 1 + " " + Arrays.toString(chessBoard[i]));
        }
        System.out.println("   " + "A  " + "B  " + "C  " + "D  " + "E  " + "F  " + "G  " + "H  ");


        return "yes";
    }
}

