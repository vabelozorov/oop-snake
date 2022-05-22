package ua.belozorov.snake.ingame;

import ua.belozorov.snake.conf.ConfigFactory;
import ua.belozorov.snake.conf.Params;
import ua.belozorov.snake.core.*;
import ua.belozorov.snake.gui.CanvasFactory;

public class InGameConfig implements GamePhaseConfig {

    private final GameFactory gameFactory = GameFactory.instance();
    private final GameFieldFactory gameFieldFactory = GameFieldFactory.instance();
    private final CanvasFactory canvasFactory = CanvasFactory.instance();

    private volatile InGamePhase inGamePhase;

    @Override
    public InGamePhase phase() {
        if (inGamePhase == null) {
            Params config = ConfigFactory.getConfig();
            inGamePhase = new InGamePhase(gameFieldFactory, new SysClockTicker(), config.afterInGamePhaseDelayMs());
            var gameFieldView = new GameFieldView(canvasFactory);
            inGamePhase.gameField().addListener(gameFieldView::display);
        }
        return inGamePhase;
    }

    @Override
    public GamePhaseController controller() {
        Game game = gameFactory.game();
        return new InGameController(game, phase());
    }

    @Override
    public Class<? extends GamePhase> phaseId() {
        return InGamePhase.class;
    }
}
