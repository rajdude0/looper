package org.looper.async;

import org.looper.context.LocalContext;
import org.looper.main.Looper;
import org.looper.task.ITask;

public class Callback {

    public Callback(ITask task) {
        Looper.getInstance().addTask(new LocalContext(task));
    }
}
