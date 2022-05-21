package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultSnakeCreateTest {

    @Test
    void snakeSegmentCreated_faceRight() {
        SnakeData.assertSnakeSegments(
                List.of(
                        Point.xy(5, 1),
                        Point.xy(4, 1),
                        Point.xy(3, 1),
                        Point.xy(2, 1),
                        Point.xy(1, 1)
                ),
                SnakeData.facesRight()
        );
    }

    @Test
    void snakeSegmentCreated_faceLeft() {
        SnakeData.assertSnakeSegments(
                List.of(
                        Point.xy(1, 1),
                        Point.xy(2, 1),
                        Point.xy(3, 1),
                        Point.xy(4, 1),
                        Point.xy(5, 1)
                ),
                SnakeData.facesLeft()
        );
    }

    @Test
    void snakeSegmentCreated_faceUp() {
        SnakeData.assertSnakeSegments(
                List.of(
                        Point.xy(1, 5),
                        Point.xy(1, 4),
                        Point.xy(1, 3),
                        Point.xy(1, 2),
                        Point.xy(1, 1)
                ),
                SnakeData.facesUp()
        );
    }
    
    @Test
    void snakeSegmentCreated_faceDown() {
        SnakeData.assertSnakeSegments(
                List.of(
                        Point.xy(1, 1),
                        Point.xy(1, 2),
                        Point.xy(1, 3),
                        Point.xy(1, 4),
                        Point.xy(1, 5)
                ),
                SnakeData.facesDown()
        );
    }

    @Test
    void headTailNotOnSameLine_ex() {
        assertThrows(IllegalStateException.class,
                () -> SnakeData.atCoordinates(Point.xy(1, 1), Point.xy(2, 2)));
    }

    @Test
    void headAndTailMatch_ex() {
        assertThrows(IllegalStateException.class,
                () -> SnakeData.atCoordinates(Point.xy(1, 1), Point.xy(1, 1)));
    }
}