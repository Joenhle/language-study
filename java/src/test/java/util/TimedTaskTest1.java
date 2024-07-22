package util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class TimedTaskTest1 {

    public static void timer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("time: " + new Date());
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    public static void threadPool() {
        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5)) {
            Runnable task = () -> {
                System.out.println("time: " + new Date());
            };
            scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        timer(); // Timer


        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "2");
//        threadPool();
    }
}
