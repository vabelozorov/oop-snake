package ua.belozorov.snake.ingame;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.belozorov.snake.core.Params;
import ua.belozorov.snake.core.Point;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameFieldFactory {
    private static final GameFieldFactory INSTANCE = new GameFieldFactory();

    public static GameFieldFactory instance() {
        return INSTANCE;
    }

    public GameField create() {
        Params params = Params.instance();
        DefaultSnake snake = new DefaultSnake(
                params.initialSnakeRestIntervalMs(), Point.xy(5, 1), Point.xy(1,1));
        return new DefaultGameField(snake);
    }
}
