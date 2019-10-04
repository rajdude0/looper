package org.looper.main;

import org.looper.events.EventQueue;
import org.looper.context.Context;

public class Looper implements Runnable {

    private EventQueue queue;
    private static Looper instance;
    private Thread masterThread;
    private Looper() {
        queue= new EventQueue(10000);
    }

    public static Looper getInstance(){
        if(instance==null)
            return instance = new Looper();
        return instance;
    }

    public void run(){
        while(queue.size()!=0){
            Context context = queue.pop();
            if(context!=null)
                try {
                    context.execute();
                    context.success();
                } catch (Exception e) {
                    context.errorOccurred(e);
                }
        }
    }


    public void addTask(Context task) {
        queue.addTask(task);
        wakeUp();
    }

    private void wakeUp() {
        if(queue.size()==1){
            masterThread = new Thread(this);
            masterThread.start();
        }
    }
}
