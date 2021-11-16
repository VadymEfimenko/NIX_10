package ua.com.alevel;

public class MinuteException extends Exception{
    private int minute;
    public int getMinute(){
        return minute;
    }
    public MinuteException(String message, int minute){
        super(message);
        this.minute = minute;
    }
}
