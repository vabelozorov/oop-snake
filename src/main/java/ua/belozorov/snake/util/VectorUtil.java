package ua.belozorov.snake.util;

import ua.belozorov.snake.core.Direction;
import ua.belozorov.snake.core.Point;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class VectorUtil {

    private static final Map<Direction, UnaryOperator<Point>> growDirectionFunctions = new EnumMap<>(
            Map.of(
                    Direction.UP, Point::withYIncrement,
                    Direction.DOWN, Point::withYDecrement,
                    Direction.RIGHT, Point::withXIncrement,
                    Direction.LEFT, Point::withXDecrement
            )
    );

    public static Direction defineBodyDirection(Point start, Point end) {
        if (start.equals(end)) {
            throw new IllegalStateException("points cannot be same");
        }

        if (start.y() == end.y()) {
            return start.x() > end.x() ? Direction.RIGHT : Direction.LEFT;
        } else if (start.x() == end.x()) {
            return start.y() > end.y() ? Direction.UP : Direction.DOWN;
        } else {
            throw new IllegalStateException("points are not on the same line");
        }
    }

    public static Point nextPointTowards(Point from, Direction direction) {
        return growDirectionFunctions.get(direction).apply(from);
    }

    public static Point nthPointTowards(int n, Point from, Direction direction) {
        Point tail = from;
        for (int i = 0; i < n; i++) {
            tail = VectorUtil.nextPointTowards(tail, direction);
        }
        return tail;
    }
}
