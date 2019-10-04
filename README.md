# Looper
Async programming library for Java `(in development)`.

## Node.js like syntax

### Callbacks
```java
  new Callback(()->{
            FileWriter writer = new FileWriter("./something.txt");
            writer.write("Hello world from callback");
            writer.close();
        });
```

### Promises
```java
 new Promise((reject, resolve)-> {
            FileWriter writer = new FileWriter("./somethingnew.txt");
            writer.write("Hello world from promise");
            writer.close();
            FileInputStream stream = new FileInputStream("./something.txt");
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            stream.close();
            resolve.work(new String(bytes));

        }).then((data)->{
            System.out.println(data);
        })._catch((err)->{
            System.out.println(err);
        });
```


### Working example
```java
public static void main(String args[]) {
  
  System.out.println("Foo");
  
  
  //IO operation done inside a promise
  new Promise((reject, resolve)-> {
            FileWriter writer = new FileWriter("./something.txt");
            writer.write("Hello world from promise");
            writer.close();
            FileInputStream stream = new FileInputStream("./something.txt");
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            stream.close();
            resolve.work(new String(bytes));
            
        }).then((data)->{
            System.out.println(data);
        })._catch((err)->{
            System.out.println(err);
        });
        
        
   System.out.println("Bar");     
  
}

//OUTPUT:
// Foo
// Bar
// Hello world from promise


```
