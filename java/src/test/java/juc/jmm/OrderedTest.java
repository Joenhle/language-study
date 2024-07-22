package juc.jmm;

public class OrderedTest {

    private int x = 0;
    private boolean flag = false;

    public void writer() {
        x = 42;        // 1. 写入变量 x
        flag = true;   // 2. 写入变量 flag
        try {
            Thread.sleep(10);  // 模拟一些延迟
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void reader() {
        if (flag) {    // 3. 读取变量 flag
            try {
                Thread.sleep(10);  // 模拟一些延迟
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(x);  // 4. 读取变量 x
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OrderedTest demo = new OrderedTest();

        int counter = 0;
        for (int i = 0; i < 100000; i++) {
            // 重置状态
            demo.x = 0;
            demo.flag = false;

            // 创建写线程
            Thread writerThread = new Thread(demo::writer);

            // 创建读线程
            Thread readerThread = new Thread(demo::reader);

            writerThread.start();
            readerThread.start();

            writerThread.join();
            readerThread.join();

            if (demo.x == 0 && demo.flag) {
                counter++;
            }
        }

        System.out.println("Number of times x was 0: " + counter);
    }
}
