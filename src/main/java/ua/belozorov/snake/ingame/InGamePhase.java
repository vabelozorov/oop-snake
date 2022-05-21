package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.GameEventListener;
import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.Params;

import java.util.Collection;

public class InGamePhase implements GamePhase {
    private final GameFieldFactory gameFieldFactory;
    private final Ticker ticker;
    private volatile Snake snake;
    private volatile GameField gameField;

    public InGamePhase(GameFieldFactory gameFieldFactory, Ticker ticker) {
        this.gameFieldFactory = gameFieldFactory;
        this.snake = gameFieldFactory.createSnake();
        this.gameField = gameFieldFactory.createField(snake);
        this.ticker = ticker;
    }

    @Override
    public void reInit() {
        Collection<GameEventListener<GameField>> listeners = gameField.listeners();
        this.snake = gameFieldFactory.createSnake();
        this.gameField = gameFieldFactory.createField(snake);
        listeners.forEach(gameField::addListener);
    }

    @Override
    public void run() throws InterruptedException {
        ticker.start();

        while (ticker.waitPlayerAction(snake.restInterval())) {
            boolean hasEaten = gameField.snakeTriesEatApple();

            if (hasEaten) {
                gameField.newApple();
            } else {
                boolean isValidMove = gameField.tryMoveSnake();
                if (!isValidMove) {
                    break;
                }
            }

            gameField.notifyListeners();
        }
    }

    @Override
    public void stop() {
        ticker.stop();
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

    public Snake snake() {
        return snake;
    }
}
