package ua.belozorov.snake.gameover;

import ua.belozorov.snake.core.GameEventListener;
import ua.belozorov.snake.core.GamePhase;

import java.util.Collection;

public class GameOverPhase implements GamePhase {
    private volatile GameOver gameOver;
    private final long afterGameOverPhaseDelayMs;

    public GameOverPhase(long afterGameOverPhaseDelayMs) {
        this.afterGameOverPhaseDelayMs = afterGameOverPhaseDelayMs;
        this.gameOver = createGameOver();
    }

    private GameOver createGameOver() {
        return new GameOver();
    }

    @Override
    public void reInit() {
        Collection<GameEventListener<GameOver>> listeners = gameOver.listeners();
        gameOver = createGameOver();
        listeners.forEach(gameOver::addListener);
    }

    @Override
    public void run() {
        gameOver.notifyListeners();
    }

    @Override
    public long delayAfterMs() {
        return afterGameOverPhaseDelayMs;
    }

    @Override
    public int order() {
        return 3;
    }

    public GameOver gameOver() {
        return gameOver;
    }
}
