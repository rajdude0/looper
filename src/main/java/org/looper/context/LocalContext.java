package org.looper.context;

import org.looper.task.ITask;

public class LocalContext extends Context {

    private ITask task;


    public LocalContext(ITask task) {
        this.task = task;
    }

    public LocalContext(ITask task, ITask postExecution) {
        this.task = task;
        super.postExecution = postExecution;
    }

    public void setTask(ITask task) {
        this.task = task;
    }

    public void execute() throws Exception {
        this.task.work();
        hasExecuted = true;
    }


}


