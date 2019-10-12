package org.looper.main;

import org.looper.events.EventQueue;
import org.looper.context.Context;

import java.util.ArrayList;

public class Looper implements Runnable {

    private EventQueue queue;
    private static Looper instance;
    private static Thread masterThread;
    private int threadsCreated = 0;
    private final int MAX_THREADS = 10;

    private Looper() {
        queue = new EventQueue(10000);
    }

    private static ArrayList<Looper> workerLoopers = new ArrayList<>();

    public static Looper getInstance() {
        if (instance == null)
            return instance = new Looper();
        return instance;
    }

    public void run() {
        while (queue.size() != 0) {
            try {
                Context context = queue.pop();

                if (context != null)
                    try {
                        context.execute();
                        context.success();
                    } catch (Exception e) {
                        context.errorOccurred(e);
                    }
            } catch (InterruptedException interrupt) {
                interrupt.printStackTrace();
            }
        }
        decrement();
        System.out.println("Total thread as of now " + threadsCreated);
    }

    private synchronized void increment() {
        threadsCreated++;
    }

    private synchronized void decrement() {

        threadsCreated--;
    }

    private void assignToWorker(int workerIndex, Context context) {
        workerLoopers.get(workerIndex).queue.addTask(context);
        workerLoopers.get(workerIndex).wakeUp();
    }

    public void addTask(Context task) {
        if (workerLoopers.size() > 0) {
            int randWorker = (int) (Math.random() * 10) % workerLoopers.size();
            System.out.println(randWorker);
            assignToWorker(randWorker, task);
            return;
        }
        queue.addTask(task);
        wakeUp();
    }

    private void wakeUp() {
        if (queue.size() == 1) {
            increment();
            masterThread = new Thread(this);
            masterThread.start();
        }
        if (workerLoopers.size() <= MAX_THREADS && queue.size() == 2000) {
            workerLoopers.add(new Looper());
        }
    }
}
