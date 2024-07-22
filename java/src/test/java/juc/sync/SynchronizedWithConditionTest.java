package juc.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class SynchronizedWithConditionTest {

    public static int counter = 0;
    public static int maxSize = 10;
    public static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        FutureTask<Void> producerTaskFuture = new FutureTask<>(() -> {
            while (true) {
                synchronized (lock) {
                    if (counter == maxSize) {
                        lock.wait();
                    }
                    counter += 1;
                    System.out.println(counter);
                    lock.notify();
                }
            }
        });

        FutureTask<Void> consumerTaskFuture = new FutureTask<>(() -> {
            while (true) {
                synchronized (lock) {
                    if (counter == 0) {
                        lock.wait();
                    }
                    counter -= 1;
                    System.out.println(counter);
                    lock.notify();
                }
            }
        });

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread producer = new Thread(producerTaskFuture);
            producer.start();
            workers.add(producer);
            Thread consumer = new Thread(consumerTaskFuture);
            consumer.start();
            workers.add(consumer);
        }

        for (var worker : workers) {
            worker.join();
        }
    }
}
