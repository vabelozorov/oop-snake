package ua.belozorov.snake.ingame;

import lombok.Builder;
import ua.belozorov.snake.core.Direction;
import ua.belozorov.snake.core.Point;
import ua.belozorov.snake.util.VectorUtil;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static ua.belozorov.snake.util.VectorUtil.defineBodyDirection;

public class DefaultSnake implements Snake {

    private static final Map<Direction, Set<Direction>> allowedDirectionChanges = new EnumMap<>(
            Map.of(
                    Direction.UP, Set.of(Direction.LEFT, Direction.RIGHT),
                    Direction.DOWN, Set.of(Direction.LEFT, Direction.RIGHT),
                    Direction.RIGHT, Set.of(Direction.UP, Direction.DOWN),
                    Direction.LEFT, Set.of(Direction.UP, Direction.DOWN)
            )
    );

    private long restInterval;

    private final LinkedList<Point> segments = new LinkedList<>();
    private final Map<Integer, Integer> restIntervalChanges = new HashMap<>();

    private volatile Direction nextMoveDirection;

    @Builder
    public DefaultSnake(
            long initialRestInterval,
            Point head,
            Point tail,
            Map<Integer, Integer> restIntervalChanges
    ) {
        this.restIntervalChanges.putAll(requireNonNull(restIntervalChanges));
        restInterval = initialRestInterval;
        nextMoveDirection = VectorUtil.defineBodyDirection(head, tail);

        createFullBody(head, tail);
    }

    private void createFullBody(Point expectedHead, Point tail) {
        segments.add(tail);

        do {
            growHead();
        } while (!head().equals(expectedHead));
    }

    private void growHead() {
        segments.addFirst(nextSegment(nextMoveDirection));
    }

    private Point nextSegment(Direction direction) {
        return VectorUtil.nextPointTowards(head(), direction);
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
            speedUpIfThresholdReached();
            growHead();
            return true;
        }
        return false;
    }

    private boolean isAppleEaten(Point apple) {
        return nextSegment(nextMoveDirection).equals(apple);
    }

    private void speedUpIfThresholdReached() {
        Integer newSpeed = restIntervalChanges.get(segments.size() + 1);
        if (newSpeed != null) {
            restInterval = newSpeed;
        }
    }

}
