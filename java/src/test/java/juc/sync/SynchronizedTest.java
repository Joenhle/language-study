package juc.sync;

public class SynchronizedTest {

    private int counter = 0;
    private static int counter2 = 0;
    private Object lock = new Object();

    // 临界区加在实例上，相当于synchronized (this)
    private synchronized void add() {
        counter += 1;
    }

    // 临界区加在自定义对象锁上
    private void add2() {
        synchronized (lock) {
            counter += 1;
        }
    }

    // 临界区加在static方法上相当于加在Class上
    private synchronized static void add3() {
        counter2 += 1;
    }

    private void testSelfDefineInstance() throws InterruptedException {
        Runnable task2 = () -> {
            for (int i = 0; i < 10; i++) {
                add();
            }
        };
        Thread thread3 = new Thread(task2, "task3");
        Thread thread4= new Thread(task2, "task4");
        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        System.out.println(counter);
    }

    private void testSelfInstance() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                add();
            }
        };

        Thread thread1 = new Thread(task, "task1");
        Thread thread2 = new Thread(task, "task2");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);

    }

    private static void testStatic() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                add3();
            }
        };

        Thread thread1 = new Thread(task, "task1");
        Thread thread2 = new Thread(task, "task2");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter2);
    }

    public static void main(String[] args) throws InterruptedException {
//        SynchronizedTest synchronizedTest = new SynchronizedTest();
//        synchronizedTest.testSelfDefineInstance();
        testStatic();
    }
}
