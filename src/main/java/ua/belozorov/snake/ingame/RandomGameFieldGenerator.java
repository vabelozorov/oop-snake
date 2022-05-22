package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Direction;
import ua.belozorov.snake.core.Point;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class RandomGameFieldGenerator implements GameFieldGenerator {
    private final SecureRandom random;

    public RandomGameFieldGenerator() {
        try {
            this.random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("failed to init randomizer", e);
        }
    }

    @Override
    public Point next(List<Point> excludedPoint, int width, int height) {
        int cellQty = width * height;
        int[] excludedCells = coordinatesToCell(excludedPoint, width);
        Arrays.sort(excludedCells);

        int[] cellCandidates = IntStream.range(0, cellQty)
                .filter(c -> Arrays.binarySearch(excludedCells, c) < 0)
                .toArray();

        int cellIndex = randomCellIndex(cellCandidates.length);

        return cellToCoordinates(cellCandidates[cellIndex], width);
    }

    private int[] coordinatesToCell(List<Point> snakeSegments, int width) {
        return snakeSegments.stream()
                .mapToInt(p -> coordinateToCell(width, p))
                .toArray();
    }

    int coordinateToCell(int width, Point p) {
        return p.y() * width + p.x();
    }

    private int randomCellIndex(int cellQty) {
        return random.nextInt(cellQty);
    }

    Point cellToCoordinates(int cellNumber, int width) {
        int y = cellNumber / width;
        int x = cellNumber % width;
        return new Point(x, y);
    }

    @Override
    public Direction nextDirection() {
        int i = random.nextInt(0, Direction.values().length);
        return Arrays.stream(Direction.values())
                .filter(d -> d.ordinal() == i)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Point nextSnakeHead(int fieldWidth, int fieldHeight, int snakeLength) {
        return next(
                fieldWidth - (snakeLength - 1) * 2,
                fieldHeight - (snakeLength - 1) * 2
        )
                .withPlusXY(snakeLength - 1, snakeLength - 1);
    }
}
