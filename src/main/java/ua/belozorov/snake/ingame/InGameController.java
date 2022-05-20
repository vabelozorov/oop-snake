package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Game;
import ua.belozorov.snake.core.GameControllerAdapter;

public class InGameController extends GameControllerAdapter {
    private final Game game;
    private final InGamePhase gamePhase;

    public InGameController(Game game, InGamePhase gamePhase) {
        this.game = game;
        this.gamePhase = gamePhase;
    }

    @Override
    public void handleR() {
        game.restart();
    }

    @Override
    public void handleUp() {
        gamePhase.snake().turnUp();
    }

    @Override
    public void handleDown() {
        gamePhase.snake().turnDown();
    }

    @Override
    public void handleRight() {
        gamePhase.snake().turnRight();
    }

    @Override
    public void handleLeft() {
        gamePhase.snake().turnLeft();
    }
}
