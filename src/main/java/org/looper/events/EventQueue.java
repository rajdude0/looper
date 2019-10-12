package org.looper.events;

import org.looper.context.Context;


//import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue {

    LinkedBlockingQueue<Context> queue;
    long numberOfTaskIn = 0L;
    long numberOfTaskOut = 0L;
    long t1 = System.currentTimeMillis();
    long t2 = System.currentTimeMillis();

    public EventQueue(int size) {
        queue = new LinkedBlockingQueue<>();
    }

    public void addTask(Context task) {
        queue.add(task);
        numberOfTaskIn++;
    }

    public void remove(Context task) {
        queue.remove(task);
        numberOfTaskOut++;
    }

    public Context pop() throws InterruptedException {
        return queue.take();
    }

    public Context peek() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public long getThroughPut() {
        if (numberOfTaskOut == 0) return 1;
        return numberOfTaskIn / numberOfTaskOut;
    }

}
