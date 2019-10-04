package org.looper.context;

import org.looper.task.ITask;

abstract public class Context {

    protected boolean hasExecuted = false;
    protected ITask postExecution;
    protected boolean hasFailed = false;
    protected boolean hasSucceeded = false;

    public Exception getException() {
        return exception;
    }

    protected Exception exception = null;

    public abstract void execute() throws Exception;

    public void errorOccurred(Exception e){
        hasExecuted = true;
        hasFailed = true;
        hasSucceeded = false;
        exception = e;
        e.printStackTrace();
        postExecute();
    }

    private void postExecute() {
        if(postExecution!=null) {
            try {
                postExecution.work();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void success() throws Exception {
        hasFailed=false;
        hasSucceeded = true;
        postExecute();
    }


    public boolean isHasSucceeded() {
        return hasSucceeded;
    }

    public void setHasSucceeded(boolean hasSucceeded) {
        this.hasSucceeded = hasSucceeded;
    }

    public boolean isHasFailed() {
        return hasFailed;
    }

    public void setHasFailed(boolean hasFailed) {
        this.hasFailed = hasFailed;
    }

    public boolean isHasExecuted() {
        return hasExecuted;
    }

    public void setHasExecuted(boolean hasExecuted) {
        this.hasExecuted = hasExecuted;
    }
}
