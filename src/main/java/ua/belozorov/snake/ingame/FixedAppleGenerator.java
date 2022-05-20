package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Point;

public record FixedAppleGenerator(Point point) implements AppleGenerator {

    @Override
    public Point next() {
        return point;
    }
}
