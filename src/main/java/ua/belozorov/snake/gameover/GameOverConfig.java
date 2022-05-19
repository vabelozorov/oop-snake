package ua.belozorov.snake.gameover;

import ua.belozorov.snake.core.Game;
import ua.belozorov.snake.core.GameFactory;
import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.GamePhaseConfig;
import ua.belozorov.snake.gui.CanvasFactory;

public class GameOverConfig implements GamePhaseConfig {
    private GameOverPhase gamePhase;

    @Override
    public GameOverPhase phase() {
        if (gamePhase == null) {
            var gameOverView = new GameOverView(CanvasFactory.instance());
            gamePhase = new GameOverPhase();
            gamePhase.gameOver().addListener(gameOverView::display);
        }
        return gamePhase;
    }

    @Override
    public GameOverController controller() {
        Game game = GameFactory.instance().game();
        return new GameOverController(game);
    }

    @Override
    public Class<? extends GamePhase> phaseId() {
        return GameOverPhase.class;
    }
}
