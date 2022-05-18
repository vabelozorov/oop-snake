package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.GamePhase;

import static ua.belozorov.snake.util.SleepUtil.sleepSec;

public class InitialCountDownPhase implements GamePhase {
    private final InitialCountDown countDown;

    public InitialCountDownPhase(InitialCountDown countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() throws InterruptedException {
        while (countDown.isNotFinished()) {
            sleepSec(1);
            countDown.countDown();
            countDown.notifyListeners();
        }
    }

    @Override
    public long delayAfterMs() {
        return 1500;
    }

    @Override
    public int order() {
        return 1;
    }

}
