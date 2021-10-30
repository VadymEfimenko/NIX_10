package ua.com.alevel;

public final class ReverseString {

    private ReverseString() {
    }

    public static String reverseString(String userString) {
        byte[] stringAsByteArray = userString.getBytes();
        byte[] result = new byte[stringAsByteArray.length];

        for (int i = 0; i < stringAsByteArray.length; i++)
            result[i] = stringAsByteArray[stringAsByteArray.length - i - 1];
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
