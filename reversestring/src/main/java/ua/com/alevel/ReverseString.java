package ua.com.alevel;

public final class ReverseString {

    private ReverseString() {
    }

    public static String reverseString(String userString) {
        char[] stringAsArray = userString.toCharArray();
        char[] result = new char[stringAsArray.length];

        for (int i = 0; i < stringAsArray.length; i++)
            result[i] = stringAsArray[stringAsArray.length - i - 1];
        return new String(result);
    }

    public static String reverseSubString(String userString, String subString) {
        if (userString.contains(subString)) {
            String reversedSubString = reverseString(subString);
            return userString.replaceAll(subString, reversedSubString);
        } else {
            return "There is no such substring, try else";
        }
    }

    public static String reverseStringIndex(String userString, int startIndex, int endIndex) {
        if (startIndex > userString.length() || endIndex > userString.length()) {
            return "Indexes out of range of string's chars. Try again";
        }
        String subString = userString.substring(startIndex, endIndex);
        String resultString = reverseSubString(userString, subString);
        return resultString;
    }
}
