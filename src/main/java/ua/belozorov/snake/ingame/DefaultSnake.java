package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Point;

import java.util.*;
import java.util.function.UnaryOperator;

public class DefaultSnake implements Snake {
    private static final Map<Direction, UnaryOperator<Point>> growDirectionFunctions = new EnumMap<>(
            Map.of(
                    Direction.UP, Point::withYIncrement,
                    Direction.DOWN, Point::withYDecrement,
                    Direction.RIGHT, Point::withXIncrement,
                    Direction.LEFT, Point::withXDecrement
            )
    );

    private static final Map<Direction, Set<Direction>> allowedDirectionChanges = new EnumMap<>(
            Map.of(
                    Direction.UP, Set.of(Direction.LEFT, Direction.RIGHT),
                    Direction.DOWN, Set.of(Direction.LEFT, Direction.RIGHT),
                    Direction.RIGHT, Set.of(Direction.UP, Direction.DOWN),
                    Direction.LEFT, Set.of(Direction.UP, Direction.DOWN)
            )
    );

    private final long restInterval;

    private final LinkedList<Point> segments = new LinkedList<>();

    private volatile Direction nextMoveDirection;

    public DefaultSnake(long initialRestInterval, Point head, Point tail) {
        restInterval = initialRestInterval;

        createFullBody(head, tail);
    }

    private void createFullBody(Point head, Point tail) {
        nextMoveDirection = defineBodyDirection(head, tail);

        segments.add(tail);

        do {
            growHead();
        } while (!head().equals(head));
    }

    private static Direction defineBodyDirection(Point head, Point tail) {
        if (head.equals(tail)) {
            throw new IllegalStateException("head & tail cannot be same");
        }

        if (head.y() == tail.y()) {
            return head.x() > tail.x() ? Direction.RIGHT : Direction.LEFT;
        } else if (head.x() == tail.x()) {
            return head.y() > tail.y() ? Direction.UP : Direction.DOWN;
        } else {
            throw new IllegalStateException("head & tail are not on the same line");
        }
    }

    private void growHead() {
        UnaryOperator<Point> growOperator = growDirectionFunctions.get(nextMoveDirection);
        Point newSegment = growOperator.apply(head());
        segments.addFirst(newSegment);
    }

    private void growTail() {
        UnaryOperator<Point> growOperator = growDirectionFunctions.get(nextMoveDirection.flipped());
        Point newSegment = growOperator.apply(tail());
        segments.addLast(newSegment);
    }

    @Override
    public Point head() {
        return segments.getFirst();
    }

    @Override
    public Point tail() {
        return segments.getLast();
    }

    @Override
    public List<Point> segments() {
        return new ArrayList<>(segments);
    }

    @Override
    public boolean tryMove(int fieldWidth, int fieldHeight) {
        growHead();
        removeTail();

        return isValidMove(fieldWidth, fieldHeight);
    }

    private void removeTail() {
        segments.removeLast();
    }

    private boolean isValidMove(int fieldWidth, int fieldHeight) {
        return !(hasSnakeCrossedBoundary(fieldWidth, fieldHeight) ||
                isHeadBodyCollision());
    }

    private boolean hasSnakeCrossedBoundary(int width, int height) {
        return head().x() > (width - 1) ||
                head().x() < 0 ||
                head().y() > (height - 1) ||
                head().y() < 0
                ;
    }

    private boolean isHeadBodyCollision() {
        return segments().stream()
                .filter(segm -> segm.equals(head()))
                .count() == 2;
    }

    @Override
    public void turnUp() {
        if (isDirectionChangeAllowed(Direction.UP)) {
            nextMoveDirection = Direction.UP;
        }
    }

    @Override
    public void turnDown() {
        if (isDirectionChangeAllowed(Direction.DOWN)) {
            nextMoveDirection = Direction.DOWN;
        }
    }

    @Override
    public void turnRight() {
        if (isDirectionChangeAllowed(Direction.RIGHT)) {
            nextMoveDirection = Direction.RIGHT;
        }
    }

    @Override
    public void turnLeft() {
        if (isDirectionChangeAllowed(Direction.LEFT)) {
            nextMoveDirection = Direction.LEFT;
        }
    }

    private boolean isDirectionChangeAllowed(Direction requested) {
        Direction actualDirection = defineBodyDirection(head(), segments.get(1));
        return allowedDirectionChanges.get(actualDirection).contains(requested);
    }

    @Override
    public long restInterval() {
        return restInterval;
    }

    @Override
    public boolean tryEatApple(Point apple) {
        if (isAppleEaten(apple)) {
            growTail();
            return true;
        }
        return false;
    }

    private boolean isAppleEaten(Point apple) {
        return head().equals(apple);
    }

    private enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT;

        public Direction flipped() {
            return switch (this) {
                case UP -> DOWN;
                case DOWN -> UP;
                case RIGHT -> LEFT;
                case LEFT -> RIGHT;
            };
        }
    }
}
