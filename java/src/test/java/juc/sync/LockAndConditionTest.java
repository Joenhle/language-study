package juc.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndConditionTest {

    public static int counter = 0;
    public static int maxSize = 10;
    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) throws Exception {

        FutureTask<Void> producerWorkFuture = new FutureTask<>(() -> {
            while (true) {
                reentrantLock.lock();
                if (counter == maxSize) {
                    condition.await();
                }
                counter += 1;
                condition.signal();
                System.out.println(counter);
                reentrantLock.unlock();
            }
        });

        FutureTask<Void> consumerWorkerFuture = new FutureTask<>(() -> {
           while (true) {
               reentrantLock.lock();
               if (counter == 0) {
                   condition.await();
               }
               counter -= 1;
               condition.signal();
               System.out.println(counter);
               reentrantLock.unlock();
           }
        });

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread producer = new Thread(producerWorkFuture);
            producer.start();
            workers.add(producer);
            Thread consumer = new Thread(consumerWorkerFuture);
            consumer.start();
            workers.add(consumer);
        }

        for (var worker : workers) {
            worker.join();
        }


    }
}
