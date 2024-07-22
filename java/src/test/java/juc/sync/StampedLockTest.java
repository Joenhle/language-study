package juc.sync;

import java.util.Map;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        x += deltaX;
        y += deltaY;
        sl.unlock(stamp);
    }

    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            currentX = x;
            currentY = y;
            sl.unlock(stamp);
        }
        return Math.sqrt(currentX*currentX + currentY*currentY);
    }

    public static void main(String[] args) {

    }
}
