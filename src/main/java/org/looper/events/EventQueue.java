package org.looper.events;

import org.looper.context.Context;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class EventQueue {

    Queue<Context> queue;

    public EventQueue(int size){
        queue = new ArrayBlockingQueue<>(size);
    }

    public  void addTask(Context task) {
        queue.add(task);
    }

    public void remove(Context task) {
        queue.remove(task);
    }

    public Context pop() {
        return queue.remove();
    }

    public Context peek() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

}
