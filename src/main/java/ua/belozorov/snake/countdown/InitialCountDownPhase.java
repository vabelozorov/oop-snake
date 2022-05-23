package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.GameContext;
import ua.belozorov.snake.core.GameEventListener;
import ua.belozorov.snake.core.GamePhase;

import java.util.Collection;

public class InitialCountDownPhase implements GamePhase {
    private volatile InitialCountdown countdown;
    private final long afterStartPhaseDelayMs;
    private final CountdownFactory countdownFactory;

    public InitialCountDownPhase(long afterStartPhaseDelayMs, CountdownFactory countdownFactory) {
        this.afterStartPhaseDelayMs = afterStartPhaseDelayMs;
        this.countdownFactory = countdownFactory;
        this.countdown = createCountdown();
    }

    private InitialCountdown createCountdown() {
        return countdownFactory.create();
    }

    @Override
    public void reInit() {
        Collection<GameEventListener<InitialCountdown>> listeners = countdown.listeners();
        countdown = createCountdown();
        listeners.forEach(countdown::addListener);
    }

    @Override
    public void run(GameContext gameContext) throws InterruptedException {
        countdown.start();
    }

    @Override
    public long delayAfterMs() {
        return afterStartPhaseDelayMs;
    }

    @Override
    public int order() {
        return 1;
    }

    public InitialCountdown countdown() {
        return countdown;
    }
}
