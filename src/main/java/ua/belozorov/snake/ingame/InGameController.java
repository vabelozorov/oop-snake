package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.GameController;

public class InGameController implements GameController {
    private final GameField gameField;

    public InGameController(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void handleR() {

    }

    @Override
    public void handleSpace() {

    }

    @Override
    public void handleUp() {
        gameField.getSnake().turnUp();
    }

    @Override
    public void handleDown() {
        gameField.getSnake().turnDown();
    }

    @Override
    public void handleRight() {
        gameField.getSnake().turnRight();
    }

    @Override
    public void handleLeft() {
        gameField.getSnake().turnLeft();
    }
}
