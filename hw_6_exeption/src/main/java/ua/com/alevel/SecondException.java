package ua.com.alevel;

public class SecondException extends Exception{
    private long second;
    public long getSecond(){
        return second;
    }
    public SecondException(String message, long second){
        super(message);
        this.second = second;
    }
}
