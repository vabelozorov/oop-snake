package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.NotifyingObject;
import ua.belozorov.snake.core.Params;

import java.util.concurrent.atomic.AtomicInteger;

import static ua.belozorov.snake.util.SleepUtil.sleepMs;

public class InitialCountdown extends NotifyingObject<InitialCountdown> {
    private final AtomicInteger counter;
    private final int countdownDelayMs;

    public InitialCountdown() {
        counter = new AtomicInteger(4);
        countdownDelayMs = Params.instance().countdownDelayMs();
    }

    public void countDown() {
        counter.decrementAndGet();
    }

    public int value() {
        return counter.get();
    }

    public boolean isNotFinished() {
        return value() > 0;
    }

    public void start() throws InterruptedException {
        while (isNotFinished()) {
            sleepMs(countdownDelayMs);
            countDown();
            notifyListeners();
        }
    }
}
