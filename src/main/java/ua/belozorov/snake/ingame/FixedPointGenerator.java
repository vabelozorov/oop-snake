package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Point;

import java.util.List;

public record FixedPointGenerator(Point point) implements PointGenerator {

    @Override
    public Point next(List<Point> excludedPoint, int width, int height) {
        return point;
    }
}
