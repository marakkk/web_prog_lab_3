package lab3.mbean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class ShotCount extends NotificationBroadcasterSupport implements ShotCountMBean {
    private AtomicInteger totalShots = new AtomicInteger(0);
    private AtomicInteger hits = new AtomicInteger(0);
    private long sequenceNumber = 1;

    @Override
    public int getTotalShots() {
        return totalShots.get();
    }

    @Override
    public int getHits() {
        return hits.get();
    }

    public void incrementTotalShots() {
        int total = totalShots.incrementAndGet();
        if (total % 15 == 0) {
            Notification notification = new Notification(
                    "ShotCount.threshold.reached",
                    this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Total shots reached " + total
            );
            sendNotification(notification);
        }
    }

    public void incrementHits() {
        hits.incrementAndGet();
    }

    public void clearTable() {
        totalShots.set(0);
        hits.set(0);
    }
}
