package ua.com.alevel;

public class YearException extends Exception{
    private int year;
    public int getYear(){
        return year;
    }
    public YearException(String message, int year){
        super(message);
        this.year = year;
    }
}
