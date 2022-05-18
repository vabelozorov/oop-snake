package ua.belozorov.snake.util;

import java.util.concurrent.TimeUnit;

public class SleepUtil {

    public static void sleepSec(long s) throws InterruptedException {
        sleepMs(s * 1000);
    }

    public static void sleepMs(long ms) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ms);
    }
}
