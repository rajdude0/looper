package org.looper.main;

import org.looper.async.Callback;
import org.looper.async.Promise;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String args[]){

        new Callback(()->{
            FileWriter writer = new FileWriter("./something.txt");
            writer.write("Hello world from callback new");
            writer.close();
        });

        new Promise((reject, resolve)-> {
            FileWriter writer = new FileWriter("./somethingnew.txt");
            writer.write("Hello world from promise");
            writer.close();
            FileInputStream stream = new FileInputStream("./something.txt");
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            resolve.work(new String(bytes));

        }).then((data)->{
            System.out.println(data);
        })._catch((err)->{
            System.out.println(err + " actually rejected");
        });

        System.out.println("Hello there");
        System.out.printf("Hello someone");
    }
}
