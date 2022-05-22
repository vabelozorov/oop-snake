package ua.belozorov.snake.ingame;

import ua.belozorov.snake.conf.ConfigFactory;
import ua.belozorov.snake.conf.Params;

public class GameFieldFactory {

    private static final GameFieldFactory INSTANCE = new GameFieldFactory();

    private final Params params;

    public static GameFieldFactory instance() {
        return INSTANCE;
    }

    protected GameFieldFactory() {
        params = ConfigFactory.getConfig();
    }

    public GameField createField(Snake snake) {
        return new DefaultGameField(snake, appleGenerator(), params.width(), params.height());
    }

    public Snake createSnake() {
        return DefaultSnake.builder()
                .initialRestInterval(params.initialSnakeRestIntervalMs())
                .head(params.initialSnakeHeadPosition())
                .tail(params.initialSnakeTailPosition())
                .restIntervalChanges(params.snakeSpeedThresholds())
                .build();
    }

    public PointGenerator appleGenerator() {
        return new RandomPointGenerator();
    }
}
