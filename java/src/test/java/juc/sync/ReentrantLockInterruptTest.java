package juc.sync;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockInterruptTest {

    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() {

        try {
            System.out.println("try to get lock...");
            lock.lockInterruptibly();
            System.out.println("lock acquired!");
            Thread.sleep(2000);
            System.out.println("normal done!");
        } catch (InterruptedException e) {
            System.out.println("lock interrupted");
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInterruptTest reentrantLockInterruptTest = new ReentrantLockInterruptTest();


        Thread t1 = new Thread(reentrantLockInterruptTest::performTask);
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            reentrantLockInterruptTest.performTask();
        });


        t1.start();
        t2.start();

        Thread.sleep(1000);
        t2.interrupt();

        t1.join();
        t2.join();

    }
}
