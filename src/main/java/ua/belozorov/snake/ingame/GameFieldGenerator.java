package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Direction;
import ua.belozorov.snake.core.Point;

import java.util.Collections;
import java.util.List;

public interface GameFieldGenerator {
    Point next(List<Point> excludedPoint, int width, int height);

    Direction nextDirection();

    default Point next(int width, int height) {
        return next(Collections.emptyList(), width, height);
    }

    Point nextSnakeHead(int fieldWidht, int fieldHeight, int snakeLength);
}
