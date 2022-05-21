package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.NotifyingObject;
import ua.belozorov.snake.core.Point;

public class DefaultGameField extends NotifyingObject<GameField> implements GameField {

    private final Snake snake;
    private final PointGenerator appleGenerator;
    private final int width;
    private final int height;
    private volatile Point currentApple;

    public DefaultGameField(Snake snake, PointGenerator appleGenerator, int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        this.appleGenerator = appleGenerator;
        newApple();
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

    @Override
    public Point getApple() {
        return currentApple;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void newApple() {
        currentApple = appleGenerator.next(snake.segments(), width, height);
    }

    @Override
    public boolean tryMoveSnake() {
        return snake.tryMove(getWidth(), getHeight());
    }

    @Override
    public boolean snakeTriesEatApple() {
        return snake.tryEatApple(getApple());
    }
}
