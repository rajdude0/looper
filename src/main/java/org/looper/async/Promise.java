package org.looper.async;

import org.looper.context.LocalPromiseContext;
import org.looper.main.Looper;
import org.looper.task.ITaskOne;
import org.looper.task.ITaskTwo;

public class Promise {

    ITaskOne success;
    ITaskOne fail;
    LocalPromiseContext context;

    public Promise(ITaskTwo task) {
        Looper.getInstance().addTask(context = new LocalPromiseContext(task, ()->{
            if(context.isHasExecuted()){
                if(context.isHasFailed()){
                    if(fail!=null) fail.work(context.getException());
                    return;
                }
               LocalPromiseContext.PROMISE_STATUS status = context.getPromiseStatus();
                if(status == LocalPromiseContext.PROMISE_STATUS.RESOLVED) {
                    success.work(context.getPromiseValue());
                } else if(status == LocalPromiseContext.PROMISE_STATUS.REJECTED) {
                    fail.work(context.getPromiseValue());
                }

            }
        })
        );
    }

    public Promise then(ITaskOne success) {
        this.success = success;
        return this;
    }

    public Promise _catch(ITaskOne fail) {
        this.fail = fail;
        return  this;
    }
}
