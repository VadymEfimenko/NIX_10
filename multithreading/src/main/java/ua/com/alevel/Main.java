package ua.com.alevel;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main thread started");
        MyThreadExThread thread = new MyThreadExThread("MyThread");
        Thread threadImplRunnable = new Thread(new MyThreadImplRunnable() ,"MyThreadRunnable");
        Runnable runnable = ()->{
            System.out.printf("%s started  \n", Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("thread has been interrupted");
            }
            System.out.printf("%s finished \n", Thread.currentThread().getName());
        };
        Thread lambdaThread = new Thread(runnable, "LambdaThread");
        lambdaThread.start();
        thread.start();
        threadImplRunnable.start();
        try {
            thread.join();
            threadImplRunnable.join();
            lambdaThread.join();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted  ", thread.getName());
        }
        System.out.println("Main thread finished");
    }
}