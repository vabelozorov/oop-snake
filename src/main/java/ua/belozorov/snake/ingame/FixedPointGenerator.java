package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Direction;
import ua.belozorov.snake.core.Point;

import java.util.List;

public record FixedPointGenerator(Point point) implements GameFieldGenerator {

    @Override
    public Point next(List<Point> excludedPoint, int width, int height) {
        return point;
    }

    @Override
    public Direction nextDirection() {
        return Direction.RIGHT;
    }

    @Override
    public Point nextSnakeHead(int fieldWidht, int fieldHeight, int snakeLength) {
        return point;
    }
}
