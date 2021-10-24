package ua.com.alevel;

public class Main {
    public static void main(String[] args) {
        //L1_T1_UniqueNumbers l1T1UniqueNumbers = new L1_T1_UniqueNumbers();
        L1_T2_Chess l1_t2_chess = new L1_T2_Chess();
        try {
            l1_t2_chess.horseStepIsPossible();
        } catch (Exception e) {
            System.out.println("NO!!!");
        }

    }
}

