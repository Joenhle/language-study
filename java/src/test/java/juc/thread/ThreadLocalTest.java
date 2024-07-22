package juc.thread;

public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    static class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                threadLocal.set(threadLocal.get() + 1);
                System.out.println(threadLocal.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task());
        Thread thread2 = new Thread(new Task());
        thread1.start();
        thread2.start();
    }
}
