package ua.com.alevel;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class L2_T1_PermissibleString {
    public boolean isValid(String userString) {
        String s = onlyBrackets(userString);
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(')
                stack.push(')');
            else if (ch == '{')
                stack.push('}');
            else if (ch == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != ch)
                return false;
        }
        return stack.isEmpty();
    }

    private String onlyBrackets(String userString) {
        ArrayList<Character> brackets = new ArrayList<>();
        for (int indexChar = 0; indexChar < userString.length(); indexChar++) {

            if (userString.charAt(indexChar) == '(' | userString.charAt(indexChar) == ')' |
                    userString.charAt(indexChar) == '{' | userString.charAt(indexChar) == '}' |
                    userString.charAt(indexChar) == '[' | userString.charAt(indexChar) == ']') {
                brackets.add(userString.charAt(indexChar));
            }
        }
        char[] brack = new char[brackets.size()];
        for (int i = 0; i < brackets.size(); i++) {
            brack[i] = (char) brackets.get(i);
        }
        String result = new String(brack);
        return result;
    }
}

class PermissibleStringRun {
    L2_T1_PermissibleString permissibleString = new L2_T1_PermissibleString();


    public void permissibleStringRun() {
        Scanner scanner = new Scanner(System.in);
        try {
            String userString;
            boolean result;
            int selector = 1;

            while (selector != 0) {

                System.out.println("Введите вашу строку:");
                userString = scanner.nextLine();

                result = permissibleString.isValid(userString);
                if (result) {
                    System.out.println("Строка допустима!");
                } else {
                    System.out.println("Строка недопустима!");
                }

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
