package ua.com.alevel;

public class MonthException extends Exception{
    private int month;
    public int getMonth(){
        return month;
    }
    public MonthException(String message, int month){
        super(message);
        this.month = month;
    }
}
