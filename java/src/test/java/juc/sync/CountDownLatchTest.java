package juc.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);
        for (int i = 0; i < numThreads; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep((finalI +1)*1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(finalI + " done!");
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }

        countDownLatch.await();
        System.out.println("all done...");
    }
}
