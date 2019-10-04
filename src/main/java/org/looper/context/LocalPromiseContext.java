package org.looper.context;

import org.looper.task.ITask;
import org.looper.task.ITaskOne;
import org.looper.task.ITaskTwo;

public class LocalPromiseContext extends Context {

    public PROMISE_STATUS getPromiseStatus() {
        return promiseStatus;
    }

    public Object getPromiseValue() {
        return promiseValue;
    }

    public enum PROMISE_STATUS {
        RESOLVED,
        REJECTED
    }

    private ITaskTwo task;
    private PROMISE_STATUS promiseStatus;
    private Object promiseValue;


    ITaskOne resolve = (Object e)->{
        promiseStatus = PROMISE_STATUS.RESOLVED;
        promiseValue = e;
    };
    ITaskOne reject = (Object e)-> {
        promiseStatus = PROMISE_STATUS.REJECTED;
        promiseValue = e;
    };

    public LocalPromiseContext(ITaskTwo task, ITask postExecution) {
        this.task = task;
        super.postExecution = postExecution;
    }

    @Override
    public void execute() throws Exception {
        task.work(reject, resolve);
        hasExecuted = true;
    }


}
