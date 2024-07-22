package data_structure.queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class Task implements Delayed {

    private String name;
    private final long delayedTime;
    private final long expireTime;

    public Task(String name, long delayedTime) {
        this.name = name;
        this.delayedTime = delayedTime;
        this.expireTime = System.currentTimeMillis() + delayedTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = expireTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.expireTime, ((Task) o).expireTime);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", delayedTime=" + delayedTime +
                '}';
    }
}

public class DelayedQueueTest {
    public static void main(String[] args) throws InterruptedException {
        Task task1 = new Task("task1", 5000);
        Task task2 = new Task("task2", 6000);
        Task task3 = new Task("task3", 7000);

        DelayQueue<Task> delayQueue = new DelayQueue<>();
        delayQueue.put(task1);
        delayQueue.put(task2);
        delayQueue.put(task3);
        System.out.println("DelayQueue elements:");
        // 通过迭代器访问不会等待delay，直接访问到元素
        delayQueue.forEach(System.out::println);

        while (!delayQueue.isEmpty()) {
            // 通过take方法会阻塞等待
            var task = delayQueue.take();
            System.out.println(task);
        }
    }
}
