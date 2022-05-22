package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RandomGameFieldGeneratorTest {

    private RandomGameFieldGenerator gen;

    private final PointToCell[] pointsToCells = new PointToCell[]{
            new PointToCell(0, Point.xy(0, 0)),
            new PointToCell(5, Point.xy(5, 0)),
            new PointToCell(9, Point.xy(9, 0)),
            new PointToCell(10, Point.xy(0, 1)),
            new PointToCell(55, Point.xy(5, 5)),
            new PointToCell(90, Point.xy(0, 9)),
            new PointToCell(99, Point.xy(9, 9))
    };

    @BeforeEach
    void setUp() {
        gen = new RandomGameFieldGenerator();
    }

    @Test
    void pointsToCellConversion() {
        for (PointToCell pointToCell : pointsToCells) {
            int actual = gen.coordinateToCell(10, pointToCell.point());
            assertEquals(pointToCell.cell(), actual);
        }
    }

    @Test
    void cellToPointConversion() {
        for (PointToCell pointToCell : pointsToCells) {
            Point actual = gen.cellToCoordinates(pointToCell.cell(), 10);
            assertEquals(pointToCell.point(), actual);
        }
    }

    @Test
    void nextPoint() {
        DefaultSnake snake = SnakeData.facesRight();
        for (int i = 0; i < 500; i++) {
            HashSet<Point> forbidden = new HashSet<>(snake.segments());
            Point generated = gen.next(snake.segments(), 10, 10);
            assertFalse(forbidden.contains(generated));
        }
    }

    @Test
    void nextSnakeHead() {
        Stream.generate(() -> gen.nextSnakeHead(6, 6, 3))
                .limit(100)
                .forEach(point -> assertTrue(
                        point.x() > 1 && point.x() < 4 &&
                        point.y() > 1 && point.y() < 4,
                        "point generated outside allowed boundaries: " + point
                ));
    }

    @Test
    void nextDirection() {
        assertDoesNotThrow(
                () -> Stream.generate(() -> gen.nextDirection())
                .limit(100)
                .forEach(d -> {
                })
        );
    }

    private record PointToCell(int cell, Point point) {
    }
}