package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.GamePhase;

import static ua.belozorov.snake.util.SleepUtil.sleepMs;

public class InGamePhase implements GamePhase {
    private final GameField gameField;
    private final long tickerIntervalMs;

    public InGamePhase(GameField gameField, long tickerIntervalMs) {
        this.gameField = gameField;
        this.tickerIntervalMs = tickerIntervalMs;
    }

    @Override
    public void run() throws InterruptedException {
        while (!isGameOverCondition()) {
            sleepMs(tickerIntervalMs);

            gameField.moveSnake();
            gameField.notifyListeners();
        }
    }

    private boolean isGameOverCondition() {
        return gameField.isWallCollision();
    }

    @Override
    public long delayAfterMs() {
        return 1500;
    }

    @Override
    public int order() {
        return 2;
    }
}
