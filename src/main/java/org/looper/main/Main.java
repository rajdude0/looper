package org.looper.main;

import org.looper.async.Callback;
import org.looper.async.Promise;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String args[]) {
//
//        new Callback(()->{
//            FileWriter writer = new FileWriter("./something.txt");
//            writer.write("Hello world from callback new");
//            writer.close();
//        });

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

        System.out.println("Hello there");
        System.out.printf("Hello someone");
    }
}
