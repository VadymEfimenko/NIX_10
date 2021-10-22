package ua.com.alevel;

public final class ReverseString {

    private ReverseString() {
    }

    public static String reverseString(String text) {

        byte[] stringAsByteArray = text.getBytes();

        byte[] result = new byte[stringAsByteArray.length];

        for (int i = 0; i < stringAsByteArray.length; i++)
            result[i] = stringAsByteArray[stringAsByteArray.length - i - 1];


        return new String(result);
    }





}
