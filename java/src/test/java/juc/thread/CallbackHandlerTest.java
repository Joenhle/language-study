package juc.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CallbackHandlerTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 100;
        });

        // 添加回调函数
        future.thenAccept(result -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("callback:" + result);
        });

        int result = future.get();
        System.out.println("res=" + result);
        Thread.sleep(10000);
    }
}
