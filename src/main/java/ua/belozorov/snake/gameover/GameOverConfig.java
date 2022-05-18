package ua.belozorov.snake.gameover;

import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.GamePhaseConfig;
import ua.belozorov.snake.gui.GuiFactory;

public class GameOverConfig implements GamePhaseConfig {
    private GameOverPhase gamePhase;

    @Override
    public GameOverPhase phase() {
        if (gamePhase == null) {
            GameOver gameOver = new GameOver();
            var gameOverView = new GameOverView(GuiFactory.instance().gameCanvas());
            gameOver.addListener(gameOverView::display);
            gamePhase = new GameOverPhase(gameOver);
        }
        return gamePhase;
    }

    @Override
    public GameOverController controller() {
        return new GameOverController();
    }

    @Override
    public Class<? extends GamePhase> phaseId() {
        return GameOverPhase.class;
    }
}
