package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.NotifyingObject;
import ua.belozorov.snake.core.Point;

public class DefaultGameField extends NotifyingObject<GameField> implements GameField {

    private final Snake snake;
    private final AppleGenerator appleGenerator;
    private final int width;
    private final int height;
    private volatile Point currentApple;

    public DefaultGameField(Snake snake, AppleGenerator appleGenerator, int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        this.appleGenerator = appleGenerator;
        this.currentApple = appleGenerator.next();
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

    @Override
    public long snakeRestInterval() {
        return getSnake().restInterval();
    }

    @Override
    public boolean hasSnakeCrossedBoundary() {
        Point head = getSnake().head();
        return head.x() > (width - 1) ||
                head.x() < 0 ||
                head.y() > (height - 1) ||
                head.y() < 0
                ;
    }

    @Override
    public Point getApple() {
        return currentApple;
    }

}
