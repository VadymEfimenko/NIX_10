package ua.com.alevel;

public class HoursException extends Exception {
    private int hour;
    public int getHour(){
        return hour;
    }
    public HoursException(String message, int hour){
        super(message);
        this.hour = hour;
    }
}
