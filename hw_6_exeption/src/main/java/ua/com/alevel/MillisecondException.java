package ua.com.alevel;

public class MillisecondException extends Exception{
    private int millisecond;
    public int getMillisecond(){
        return millisecond;
    }
    public MillisecondException(String message, long millisecond){
        super(message);
        this.millisecond = (int) millisecond;
    }
}
