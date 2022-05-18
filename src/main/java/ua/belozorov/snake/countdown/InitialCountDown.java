package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.NotifyingObject;

import java.util.concurrent.atomic.AtomicInteger;

public class InitialCountDown extends NotifyingObject<InitialCountDown> {
    private final AtomicInteger counter =  new AtomicInteger(4);

    public void countDown() {
        counter.decrementAndGet();
    }

    public int value() {
        return counter.get();
    }

    public boolean isNotFinished() {
        return value() > 0;
    }

}
