package ua.belozorov.snake.ingame;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.belozorov.snake.core.Params;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GameFieldFactory {

    private static final GameFieldFactory INSTANCE = new GameFieldFactory();

    public static GameFieldFactory instance() {
        return INSTANCE;
    }

    public GameField createField(Snake snake) {
        Params params = Params.instance();

        return new DefaultGameField(snake, appleGenerator(), params.width(), params.height());
    }

    public Snake createSnake() {
        Params params = Params.instance();

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
