package test.looper.async;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.looper.async.Callback;
import org.looper.async.Promise;
import org.looper.task.ITask;
import org.looper.task.ITaskOne;
import org.looper.task.ITaskTwo;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PromiseTest {

    @Mock
    ITaskOne data;
    @Mock
    ITaskOne err;

    ITaskTwo taskSuccess = (reject, resolve) -> {
        resolve.work("success");
    };

    ITaskTwo taskFail = (reject, resolve) -> {
        reject.work("fail");
    };

    @Test
    public void testPromiseResolve() {
        new Promise(taskSuccess).then(data)._catch(err);

        try {
            Thread.sleep(500);
            verify(data, times(1)).work("success");
            verify(err, times(0)).work("fail");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPromiseReject() {
        new Promise(taskFail).then(data)._catch(err);
        try {
            Thread.sleep(500);
            verify(data, times(0)).work("success");
            verify(err, times(1)).work("fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
