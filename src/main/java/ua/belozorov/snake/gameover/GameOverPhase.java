package ua.belozorov.snake.gameover;

import ua.belozorov.snake.core.GamePhase;

public class GameOverPhase implements GamePhase {
    private final GameOver gameOver;

    public GameOverPhase(GameOver gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void run() {
        gameOver.notifyListeners();
    }

    @Override
    public long delayAfterMs() {
        return 0;
    }

    @Override
    public int order() {
        return 3;
    }
}
