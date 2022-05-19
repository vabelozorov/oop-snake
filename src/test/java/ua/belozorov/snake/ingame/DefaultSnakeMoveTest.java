package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.List;

public class DefaultSnakeMoveTest {

    @Test
    void right() {
        SnakeData.assertSnakeMoved(
                List.of(
                        Point.xy(6, 1),
                        Point.xy(5, 1),
                        Point.xy(4, 1),
                        Point.xy(3, 1),
                        Point.xy(2, 1)
                ),
                SnakeData.facesRight()
        );
    }

    @Test
    void left() {
        SnakeData.assertSnakeMoved(
                List.of(
                        Point.xy(0, 1),
                        Point.xy(1, 1),
                        Point.xy(2, 1),
                        Point.xy(3, 1),
                        Point.xy(4, 1)
                ),
                SnakeData.facesLeft()
        );
    }

    @Test
    void up() {
        SnakeData.assertSnakeMoved(
                List.of(
                        Point.xy(1, 6),
                        Point.xy(1, 5),
                        Point.xy(1, 4),
                        Point.xy(1, 3),
                        Point.xy(1, 2)
                ),
                SnakeData.facesUp()
        );
    }

    @Test
    void down() {
        SnakeData.assertSnakeMoved(
                List.of(
                        Point.xy(1, 0),
                        Point.xy(1, 1),
                        Point.xy(1, 2),
                        Point.xy(1, 3),
                        Point.xy(1, 4)
                ),
                SnakeData.facesDown()
        );
    }
}
