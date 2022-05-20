package ua.belozorov.snake.ingame;

import static ua.belozorov.snake.util.SleepUtil.sleepMs;

public class SysClockTicker implements Ticker {
    private volatile boolean isRunning;

    @Override
    public void start() {
        isRunning = true;
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public boolean waitPlayerAction(long ms) throws InterruptedException {
        sleepMs(ms);
        return isRunning;
    }
}
