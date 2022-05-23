package ua.belozorov.snake.ingame;

import ua.belozorov.snake.conf.ConfigFactory;
import ua.belozorov.snake.conf.Params;
import ua.belozorov.snake.core.Direction;
import ua.belozorov.snake.core.Point;
import ua.belozorov.snake.util.VectorUtil;

public class GameFieldFactory {

    private static final GameFieldFactory INSTANCE = new GameFieldFactory();

    private final Params params;
    private final RandomGameFieldGenerator randomGameFieldGenerator;

    public static GameFieldFactory instance() {
        return INSTANCE;
    }

    protected GameFieldFactory() {
        randomGameFieldGenerator = new RandomGameFieldGenerator();
        params = ConfigFactory.getConfig();
    }

    public GameField createField(Snake snake) {
        return new DefaultGameField(snake, appleGenerator(), params.width(), params.height());
    }

    public Snake createSnake() {
        int snakeLength = params.initialSnakeLength();

        Point generatedHead = randomGameFieldGenerator
                .nextSnakeHead(params.width(), params.height(), snakeLength);

        Direction direction = randomGameFieldGenerator.nextDirection();

        Point tail = VectorUtil.nthPointTowards(snakeLength - 1, generatedHead, direction);

        return DefaultSnake.builder()
                .initialRestInterval(params.initialSnakeRestIntervalMs())
                .head(generatedHead)
                .tail(tail)
                .restIntervalChanges(params.snakeSpeedThresholds())
                .build();
    }

    public GameFieldGenerator appleGenerator() {
        return randomGameFieldGenerator;
    }

    public Params params() {
        return params;
    }
}
