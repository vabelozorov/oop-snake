package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.belozorov.snake.ingame.SnakeData.assertSnakeTurned;

public class DefaultSnakeTurnTest {

    /*
            TURNS FACING RIGHT
     */

    @Test
    void rightToUp() {
        assertSnakeTurned(
                List.of(
                        Point.xy(5, 2),
                        Point.xy(5, 1),
                        Point.xy(4, 1),
                        Point.xy(3, 1),
                        Point.xy(2, 1)
                ),
                SnakeData.facesRight(),
                Snake::turnUp
        );
    }

    @Test
    void turnInwardIsPrevented() {
        DefaultSnake snake = SnakeData.facesRight();
        snake.turnUp();
        snake.turnLeft();
        snake.tryMove(20,20 );

        assertEquals(Point.xy(5, 2), snake.head());
    }

    @Test
    void rightToDown() {
        assertSnakeTurned(
                List.of(
                        Point.xy(5, 0),
                        Point.xy(5, 1),
                        Point.xy(4, 1),
                        Point.xy(3, 1),
                        Point.xy(2, 1)
                ),
                SnakeData.facesRight(),
                Snake::turnDown
        );
    }

    @Test
    void rightToRightOrLeft_nothing() {
        List<Point> expected = List.of(
                Point.xy(6, 1),
                Point.xy(5, 1),
                Point.xy(4, 1),
                Point.xy(3, 1),
                Point.xy(2, 1)
        );

        assertSnakeTurned(expected, SnakeData.facesRight(), Snake::turnRight);
        assertSnakeTurned(expected, SnakeData.facesRight(), Snake::turnLeft);
    }

    /*
            TURNS FACING LEFT
     */

    @Test
    void leftToUp() {
        assertSnakeTurned(
                List.of(
                        Point.xy(1, 2),
                        Point.xy(1, 1),
                        Point.xy(2, 1),
                        Point.xy(3, 1),
                        Point.xy(4, 1)
                ),
                SnakeData.facesLeft(),
                Snake::turnUp
        );
    }

    @Test
    void leftToDown() {
        assertSnakeTurned(
                List.of(
                        Point.xy(1, 0),
                        Point.xy(1, 1),
                        Point.xy(2, 1),
                        Point.xy(3, 1),
                        Point.xy(4, 1)
                ),
                SnakeData.facesLeft(),
                Snake::turnDown
        );
    }

    @Test
    void leftToRightOrLeft_nothing() {
        List<Point> expected = List.of(
                Point.xy(0, 1),
                Point.xy(1, 1),
                Point.xy(2, 1),
                Point.xy(3, 1),
                Point.xy(4, 1)
        );

        assertSnakeTurned(expected, SnakeData.facesLeft(), Snake::turnRight);
        assertSnakeTurned(expected, SnakeData.facesLeft(), Snake::turnLeft);
    }

    /*
            TURNS FACING UP
     */

    @Test
    void upToRight() {
        assertSnakeTurned(
                List.of(
                        Point.xy(2, 5),
                        Point.xy(1, 5),
                        Point.xy(1, 4),
                        Point.xy(1, 3),
                        Point.xy(1, 2)
                ),
                SnakeData.facesUp(),
                Snake::turnRight
        );
    }

    @Test
    void upToLeft() {
        assertSnakeTurned(
                List.of(
                        Point.xy(0, 5),
                        Point.xy(1, 5),
                        Point.xy(1, 4),
                        Point.xy(1, 3),
                        Point.xy(1, 2)
                ),
                SnakeData.facesUp(),
                Snake::turnLeft
        );
    }

    @Test
    void upToDownOrUp_nothing() {
        List<Point> expected = List.of(
                Point.xy(1, 6),
                Point.xy(1, 5),
                Point.xy(1, 4),
                Point.xy(1, 3),
                Point.xy(1, 2)
        );

        assertSnakeTurned(expected, SnakeData.facesUp(), Snake::turnUp);
        assertSnakeTurned(expected, SnakeData.facesUp(), Snake::turnDown);
    }

    /*
            TURNS FACING DOWN
     */

    @Test
    void downToRight() {
        assertSnakeTurned(
                List.of(
                        Point.xy(2, 1),
                        Point.xy(1, 1),
                        Point.xy(1, 2),
                        Point.xy(1, 3),
                        Point.xy(1, 4)
                ),
                SnakeData.facesDown(),
                Snake::turnRight
        );
    }

    @Test
    void downToLeft() {
        assertSnakeTurned(
                List.of(
                        Point.xy(0, 1),
                        Point.xy(1, 1),
                        Point.xy(1, 2),
                        Point.xy(1, 3),
                        Point.xy(1, 4)
                ),
                SnakeData.facesDown(),
                Snake::turnLeft
        );
    }

    @Test
    void downToDownOrUp_nothing() {
        List<Point> expected = List.of(
                Point.xy(1, 0),
                Point.xy(1, 1),
                Point.xy(1, 2),
                Point.xy(1, 3),
                Point.xy(1, 4)
        );

        assertSnakeTurned(expected, SnakeData.facesDown(), Snake::turnUp);
        assertSnakeTurned(expected, SnakeData.facesDown(), Snake::turnDown);
    }
}
