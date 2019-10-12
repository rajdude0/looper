package org.looper.async;

import org.looper.context.LocalPromiseContext;
import org.looper.main.Looper;
import org.looper.task.ITaskOne;
import org.looper.task.ITaskTwo;

public class Promise {

    ITaskOne success;
    ITaskOne fail;
    LocalPromiseContext context;
    private boolean hasPromiseActionExecuted;

    public Promise(ITaskTwo task) {
        Looper.getInstance().addTask(context = new LocalPromiseContext(task, () -> {
                    if (context.isHasExecuted()) {
                        if (context.isHasFailed()) {
                            if (fail != null) fail.work(context.getException());
                            return;
                        }
                        executePromiseAction();
                    }
                })
        );
    }

    public Promise then(ITaskOne success) {
        this.success = success;
        if (!hasPromiseActionExecuted) {
            try {
                executePromiseAction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Promise _catch(ITaskOne fail) {
        this.fail = fail;
        if (!hasPromiseActionExecuted) {
            try {
                executePromiseAction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    private synchronized void executePromiseAction() throws Exception {
        LocalPromiseContext.PROMISE_STATUS status = context.getPromiseStatus();
        if (status == LocalPromiseContext.PROMISE_STATUS.RESOLVED) {
            if (success != null) {
                success.work(context.getPromiseValue());
                hasPromiseActionExecuted = true;
            }
            ;
        } else if (status == LocalPromiseContext.PROMISE_STATUS.REJECTED) {
            if (fail != null) {
                fail.work(context.getPromiseValue());
                hasPromiseActionExecuted = true;
            }
            ;
        }
    }
}
