package ua.com.alevel;

public class DayException extends Exception{
    private int day;
    public int getDay(){
        return day;
    }
    public DayException(String message, int day){
        super(message);
        this.day = day;
    }
}
