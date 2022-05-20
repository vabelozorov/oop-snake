package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.GameEventListener;
import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.Params;

import java.util.Collection;

import static ua.belozorov.snake.util.SleepUtil.sleepMs;

public class InGamePhase implements GamePhase {
    private final GameFieldFactory gameFieldFactory;
    private volatile GameField gameField;
    private volatile boolean isRunning;

    public InGamePhase(GameFieldFactory gameFieldFactory) {
        this.gameFieldFactory = gameFieldFactory;
        this.gameField = gameFieldFactory.create();
    }

    @Override
    public void reInit() {
        Collection<GameEventListener<GameField>> listeners = gameField.listeners();
        gameField = gameFieldFactory.create();
        listeners.forEach(gameField::addListener);
    }

    @Override
    public void run() throws InterruptedException {
        isRunning = true;

        while (isRunning && !isGameOverCondition()) {
            gameField.getSnake().move();
            gameField.notifyListeners();

            sleepMs(gameField.snakeRestInterval());

            if (gameField.getSnake().isHeadBodyCollision()) {
                stop();
            }
        }
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    private boolean isGameOverCondition() {
        return gameField.isWallCollision();
    }

    @Override
    public long delayAfterMs() {
        return Params.instance().afterStartPhaseDelayMs();
    }

    @Override
    public int order() {
        return 2;
    }

    public GameField gameField() {
        return gameField;
    }
}
