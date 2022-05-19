package ua.belozorov.snake.gameover;

import ua.belozorov.snake.core.Game;
import ua.belozorov.snake.core.GameControllerAdapter;

public class GameOverController extends GameControllerAdapter {

    private final Game game;

    public GameOverController(Game game) {
        this.game = game;
    }

    @Override
    public void handleR() {
        game.restart();
    }

    @Override
    public void handleSpace() {
        System.exit(0);
    }
}
