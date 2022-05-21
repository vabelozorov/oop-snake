package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RandomPointGeneratorTest {

    private RandomPointGenerator gen;

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
        gen = new RandomPointGenerator();
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
    void next() {
        DefaultSnake snake = SnakeData.facesRight();
        for (int i = 0; i < 200; i++) {
            HashSet<Point> forbidden = new HashSet<>(snake.segments());
            Point generated = gen.next(snake.segments(), 10, 10);
            assertFalse(forbidden.contains(generated));
        }
    }

    private record PointToCell(int cell, Point point) {
    }
}