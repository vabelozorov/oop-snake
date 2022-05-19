package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Game;
import ua.belozorov.snake.core.GameController;
import ua.belozorov.snake.core.GameControllerAdapter;

public class InGameController extends GameControllerAdapter {
    private final Game game;
    private final Snake snake;

    public InGameController(Game game, Snake snake) {
        this.game = game;
        this.snake = snake;
    }

    @Override
    public void handleR() {
        game.restart();
    }

    @Override
    public void handleUp() {
        snake.turnUp();
    }

    @Override
    public void handleDown() {
        snake.turnDown();
    }

    @Override
    public void handleRight() {
        snake.turnRight();
    }

    @Override
    public void handleLeft() {
        snake.turnLeft();
    }
}
