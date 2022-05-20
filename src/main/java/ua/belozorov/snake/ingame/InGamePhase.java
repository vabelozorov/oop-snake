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

        while (isRunning) {
            gameField.notifyListeners();
            sleepMs(gameField.snakeRestInterval());

            gameField.getSnake().move();

            if (gameField.getSnake().isHeadBodyCollision() ||
                    gameField.hasSnakeCrossedBoundary()) {
                stop();
                break;
            }
            if (gameField.isAppleEaten()) {
                gameField.getSnake().growTail();
            }
        }
    }

    @Override
    public void stop() {
        isRunning = false;
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
