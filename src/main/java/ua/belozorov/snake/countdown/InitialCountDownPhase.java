package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.GameEventListener;
import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.Params;

import java.util.Collection;

public class InitialCountDownPhase implements GamePhase {
    private volatile InitialCountdown countdown;

    public InitialCountDownPhase() {
        this.countdown = createCountdown();
    }

    private InitialCountdown createCountdown() {
        return new InitialCountdown();
    }

    @Override
    public void reInit() {
        Collection<GameEventListener<InitialCountdown>> listeners = countdown.listeners();
        countdown = createCountdown();
        listeners.forEach(countdown::addListener);
    }

    @Override
    public void run() throws InterruptedException {
        countdown.start();
    }

    @Override
    public long delayAfterMs() {
        return Params.instance().afterStartPhaseDelayMs();
    }

    @Override
    public int order() {
        return 1;
    }

    public InitialCountdown countdown() {
        return countdown;
    }
}
