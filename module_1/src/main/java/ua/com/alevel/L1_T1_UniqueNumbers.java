package ua.com.alevel;

import java.util.ArrayList;

public class L1_T1_UniqueNumbers {
    public int countUniqueNumbersInString(String userString) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        for (int indexAtString = 0; indexAtString < userString.length(); indexAtString++) {

            char numberAtIndex = userString.charAt(indexAtString);

            if (Character.isDigit(numberAtIndex) && !uniqueNumbers.contains((int) numberAtIndex) ) {
                uniqueNumbers.add((int) numberAtIndex);
            }
        }
        return uniqueNumbers.size();
    }
}
