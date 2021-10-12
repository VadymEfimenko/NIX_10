package ua.com.alevel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task2 {
    public void task2Run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your string to count characters");
        String usersString = scanner.nextLine().toLowerCase();

        HashMap<Character, Integer> charCount = new HashMap<>();
        char[] charArrayFromString = usersString.toCharArray();
        Arrays.sort(charArrayFromString);


        for (char charFromString : charArrayFromString) {
            if (charCount.containsKey(charFromString)) {
                charCount.put(charFromString, charCount.get(charFromString) + 1);
            } else {
                charCount.put(charFromString, 1);
            }
        }

        for (Map.Entry mapEntry : charCount.entrySet()) {
            System.out.println(mapEntry.getKey() + " " + mapEntry.getValue());
        }
    }
}

