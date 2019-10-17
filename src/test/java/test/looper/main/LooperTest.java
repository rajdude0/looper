package test.looper.main;


import org.junit.Rule;
import org.junit.Test;
import org.looper.async.Promise;

import java.io.FileInputStream;
import java.io.FileWriter;

import static org.junit.Assert.assertEquals;

public class LooperTest {


    @Test(timeout = 1000 * 60 * 3)
    public void shouldExecuteIOBoundMillionPromisesWithIn3Min() {
        for (int i = 0; i < 1000000; i++) {

            new Promise((reject, resolve) -> {

                FileWriter writer = new FileWriter("./somethingnew.txt");
                writer.write("Hello world from promise");
                writer.close();
                FileInputStream stream = new FileInputStream("./somethingnew.txt");
                byte[] bytes = new byte[stream.available()];
                stream.read(bytes);
                stream.close();

                resolve.work(new String(bytes));


            }).then((data) -> {
                System.out.println(data);
            })._catch((err) -> {
                System.out.println(err + " actually rejected");
            });
        }
    }
}
