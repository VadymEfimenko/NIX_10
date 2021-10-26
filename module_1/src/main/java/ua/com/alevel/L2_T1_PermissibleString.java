package ua.com.alevel;

import java.util.ArrayList;
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
