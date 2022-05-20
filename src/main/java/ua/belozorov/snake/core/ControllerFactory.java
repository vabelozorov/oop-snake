package ua.belozorov.snake.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.belozorov.snake.gui.SnakeKeyListener;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerFactory {

    private static final ControllerFactory INSTANCE = new ControllerFactory();

    private SnakeKeyListener snakeKeyListener;

    public static ControllerFactory instance() {
        return INSTANCE;
    }

    public synchronized SnakeKeyListener snakeKeyListener() {
        if (snakeKeyListener == null) {
            GamePhaseManager gamePhaseManager = GamePhaseFactory.instance().gamePhases();
            snakeKeyListener = new SnakeKeyListener(gamePhaseManager);

            GamePhaseFactory.instance().gamePhaseConfigs().forEach(
                    cfg -> snakeKeyListener.registerControllerForPhase(cfg.phaseId(), cfg.controller()));
        }

        return snakeKeyListener;
    }
}
