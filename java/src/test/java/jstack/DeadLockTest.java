package jstack;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        lock1.lockInterruptibly();

        Thread thread1 = new Thread(() -> {
            lock1.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock2.lock();
        });

        Thread thread2 = new Thread(() -> {
            lock2.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock1.lock();
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("done");

    }
}
