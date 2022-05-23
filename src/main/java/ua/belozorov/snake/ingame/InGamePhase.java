package ua.belozorov.snake.ingame;

import ua.belozorov.snake.conf.Params;
import ua.belozorov.snake.core.GameContext;
import ua.belozorov.snake.core.GameEventListener;
import ua.belozorov.snake.core.GamePhase;

import java.util.Collection;

public class InGamePhase implements GamePhase {
    private final GameFieldFactory gameFieldFactory;
    private final Ticker ticker;
    private final int delayAfterMs;
    private volatile Snake snake;
    private volatile GameField gameField;

    public InGamePhase(GameFieldFactory gameFieldFactory, Ticker ticker, int delayAfterMs) {
        this.gameFieldFactory = gameFieldFactory;
        this.snake = gameFieldFactory.createSnake();
        this.gameField = gameFieldFactory.createField(snake);
        this.ticker = ticker;
        this.delayAfterMs = delayAfterMs;
    }

    @Override
    public void reInit() {
        Collection<GameEventListener<GameField>> listeners = gameField.listeners();
        this.snake = gameFieldFactory.createSnake();
        this.gameField = gameFieldFactory.createField(snake);
        listeners.forEach(gameField::addListener);
    }

    @Override
    public void run(GameContext gameContext) throws InterruptedException {
        ticker.start();

        while (ticker.waitPlayerAction(snake.restInterval())) {
            boolean hasEaten = gameField.snakeTriesEatApple();

            if (hasEaten) {
                gameField.newApple();
            } else {
                boolean isValidMove = gameField.tryMoveSnake();
                if (!isValidMove) {
                    Params params = gameFieldFactory.params();
                    gameContext.setScore(snake.length() - params.initialSnakeLength());
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
        return delayAfterMs;
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
