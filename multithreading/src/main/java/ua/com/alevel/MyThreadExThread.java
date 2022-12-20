package ua.com.alevel;

public class MyThreadExThread extends Thread{

    MyThreadExThread(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.printf("%s started \n", Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished \n", Thread.currentThread().getName());
    }
}
