package test.looper.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.looper.async.Callback;
import org.looper.async.Promise;
import org.looper.task.ITask;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CallbackTest {
    @Mock
    ITask task;

    @Test
    public void testCallback() {
        new Callback(task);
        try {
            verify(task, times(1)).work();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
