package juc.thread;


import org.junit.Test;

import java.util.concurrent.*;


public class CreateThreadTest {

    @Test
    public void test0() throws InterruptedException {
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println("thread is running...");
            }
        }

        MyThread myThread = new MyThread();
        myThread.start();
    }

    @Test
    public void test1() throws InterruptedException {
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                System.out.println("thread is running...");
            }
        }

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }

    @Test
    public void test2() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        thread.start();
    }

    @Test
    public void test3() {
        Thread thread = new Thread(() -> {
            System.out.println("hello");
        });
        thread.start();
    }

    @Test
    public void test4() {
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            MyRunnable myRunnable = new MyRunnable();
            executorService.execute(myRunnable);
        }
    }

    @Test
    public void test5() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> 100;
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("do something else...");
        while (!futureTask.isDone()) {
            System.out.println("not done");
        }
        Integer res = futureTask.get();
        System.out.println(res);

    }

    @Test
    public void test6() throws InterruptedException, ExecutionException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Future<Integer> future = executorService.submit(() -> 100);
            Thread.sleep(1000);
            Integer res = future.get();
            System.out.println(res);
        }

    }
}
